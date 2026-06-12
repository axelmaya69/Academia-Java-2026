import java.util.List;
import java.util.function.Supplier;

public class ProductCatalog {
    public static void main(String[] args) {
        // TODO: Supplier para crear datos de prueba
        Supplier<List<Product>> catalogSupplier = () -> List.of(
                new Product("Laptop", 999.99, "Electronica", true),
                new Product("Mouse", 29.99, "Electronica", true),
                new Product("Teclado", 79.99, "Electronica", false),
                new Product("Camisa", 39.99, "Ropa", true),
                new Product("Java Book", 49.99, "Libros", true),
                new Product("Monitor", 349.99, "Electronica", true)
        );

        List<Product> catalogo = catalogSupplier.get();

        System.out.println("=== Catalogo Completo ===");
        // TODO: usar method reference System.out::println
        catalogo.stream()
                .map(Product::toDisplayString)
                .forEach(System.out::println);

        System.out.println("\n=== Pipeline: Electronica en stock, precio > $50 ===");
        // TODO: crear pipeline con 3 condiciones encadenadas
        new ProductPipeline()
                .where(Product::isAvailable)          // method reference
                .where(p -> p.category().equals("Electronica"))
                .where(p -> p.price() > 50)
                .forEach(catalogo, System.out::println);              // TODO: method reference

        System.out.println("\n=== Pipeline: Disponibles, precio < $100 ===");
        ProductPipeline pipeline = new ProductPipeline()
                .where(Product::isAvailable)
                .where(p -> p.price() < 100)
                .transform(p -> "  * " + p.name().toUpperCase() + " - $" + p.price());
        pipeline.forEach(catalogo, System.out::println);          // TODO: method reference

        System.out.println("\nTotal disponibles: "
                + new ProductPipeline()
                .where(Product::isAvailable)
                .count(catalogo));
    }
}
