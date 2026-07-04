package com.academia.batch.config;

import com.academia.batch.model.Estudiante;
import com.academia.batch.model.EstudianteReporte;
import com.academia.batch.processor.EstudianteProcessor;
import com.academia.batch.processor.ReporteEstudianteProcessor;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    @Bean
    public FlatFileItemReader<Estudiante> estudianteReader() {
        return new FlatFileItemReaderBuilder<Estudiante>()
                .name("estudianteReader")
                .resource(new ClassPathResource("estudiantes.csv"))
                .linesToSkip(1)
                .delimited()
                .names("nombre", "grupo", "nota1", "nota2", "nota3")
                .targetType(Estudiante.class)
                .build();
    }

    @Bean
    public ItemProcessor<Estudiante, Estudiante> estudianteProcessor() {
        return new EstudianteProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Estudiante> estudianteWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Estudiante>()
                .dataSource(dataSource)
                .sql("""
                        INSERT INTO estudiantes_procesados
                        (nombre, grupo, nota1, nota2, nota3, promedio)
                        VALUES (:nombre, :grupo, :nota1, :nota2, :nota3, :promedio)
                        """)
                .beanMapped()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      FlatFileItemReader<Estudiante> estudianteReader,
                      ItemProcessor<Estudiante, Estudiante> estudianteProcessor,
                      JdbcBatchItemWriter<Estudiante> estudianteWriter) {
        return new StepBuilder("step1", jobRepository)
                .<Estudiante, Estudiante>chunk(3, transactionManager)
                .reader(estudianteReader)
                .processor(estudianteProcessor)
                .writer(estudianteWriter)
                .build();
    }

    @Bean
    public JdbcCursorItemReader<Estudiante> estudianteProcesadoReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Estudiante>()
                .name("estudianteProcesadoReader")
                .dataSource(dataSource)
                .sql("SELECT nombre, grupo, promedio FROM estudiantes_procesados")
                .rowMapper((resultSet, rowNum) -> {
                    Estudiante estudiante = new Estudiante();
                    estudiante.setNombre(resultSet.getString("nombre"));
                    estudiante.setGrupo(resultSet.getString("grupo"));
                    estudiante.setPromedio(resultSet.getDouble("promedio"));
                    return estudiante;
                })
                .build();
    }

    @Bean
    public ItemProcessor<Estudiante, EstudianteReporte> reporteEstudianteProcessor() {
        return new ReporteEstudianteProcessor();
    }

    @Bean
    public MongoItemWriter<EstudianteReporte> reporteWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<EstudianteReporte>()
                .template(mongoTemplate)
                .collection("reportes_estudiantes")
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      JdbcCursorItemReader<Estudiante> estudianteProcesadoReader,
                      ItemProcessor<Estudiante, EstudianteReporte> reporteEstudianteProcessor,
                      MongoItemWriter<EstudianteReporte> reporteWriter) {
        return new StepBuilder("step2", jobRepository)
                .<Estudiante, EstudianteReporte>chunk(3, transactionManager)
                .reader(estudianteProcesadoReader)
                .processor(reporteEstudianteProcessor)
                .writer(reporteWriter)
                .build();
    }

    @Bean
    public Job procesarCalificacionesJob(JobRepository jobRepository, Step step1, Step step2) {
        return new JobBuilder("procesarCalificacionesJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .next(step2)
                .build();
    }
}
