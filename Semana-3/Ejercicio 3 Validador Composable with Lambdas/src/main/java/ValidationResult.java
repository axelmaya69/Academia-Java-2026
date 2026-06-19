import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public record ValidationResult(boolean isValid, List<String> errors) {

    static ValidationResult valid() {
        return new ValidationResult(true, List.of());
    }

    static ValidationResult invalid(String... errors) {
        return new ValidationResult(false, List.of(errors));
    }

    ValidationResult merge(ValidationResult other) {
        if (this.isValid && other.isValid) return valid();
        List<String> allErrors = new ArrayList<>(this.errors);
        allErrors.addAll(other.errors);
        return new ValidationResult(false, allErrors);
    }
}

