import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ProductPipeline {
    private Predicate<Product> filter = p -> true;
    private Function<Product, String> transform = Product::toDisplayString;

    public ProductPipeline where(Predicate<Product> predicate) {
        // TODO: encadenar con .and()
        this.filter = this.filter.and(predicate);
        return this;
    }

    public ProductPipeline transform(Function<Product, String> fn) {
        this.transform = fn;
        return this;
    }

    public void forEach(List<Product> products, Consumer<String> action) {
        for (Product p : products) {
            if (filter.test(p)) {
                action.accept(transform.apply(p));
            }
        }
    }

    public long count(List<Product> products) {
        long total = 0;
        for (Product p : products) {
            if (filter.test(p)){
                total++;
            }
        }
        return total;
    }
}
