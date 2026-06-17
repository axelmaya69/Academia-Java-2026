import java.util.Comparator;

public class Principal {
    public static void main(String[] args) {
        ContactManager mgr = new ContactManager();

        System.out.println("=== Agregando Contactos ===");
        System.out.println("Ana: " + mgr.addContact(
                new Contact("Ana Garcia", "ana@mail.com", "555-1111")));
        System.out.println("Luis: " + mgr.addContact(
                new Contact("Luis Lopez", "luis@mail.com", "555-2222")));
        System.out.println("Maria: " + mgr.addContact(
                new Contact("Maria Torres", "maria@mail.com", "555-3333")));
        System.out.println("Ana duplicada: " + mgr.addContact(
                new Contact("Ana Garcia", "ana@mail.com", "555-9999")));
        System.out.println("Carlos: " + mgr.addContact(
                new Contact("Carlos Ruiz", "carlos@mail.com", "555-4444")));
        System.out.println("Total contactos: " + mgr.size());

        System.out.println("\n=== Orden Natural (por nombre) ===");
        mgr.getAllSortedBy(Comparator.naturalOrder())
                .forEach(System.out::println);

        System.out.println("\n=== Ordenados por Email ===");
        mgr.getAllSortedBy(Comparator.comparing(Contact::getEmail))
                .forEach(System.out::println);

        System.out.println("\n=== Buscar por Email ===");
        mgr.findByEmail("maria@mail.com")
                .ifPresentOrElse(
                        c -> System.out.println("Encontrado: " + c),
                        () -> System.out.println("No encontrado"));
        mgr.findByEmail("noexiste@mail.com")
                .ifPresentOrElse(
                        c -> System.out.println("Encontrado: " + c),
                        () -> System.out.println("No encontrado"));

        System.out.println("\n=== Buscar por Prefijo 'Ma' ===");
        mgr.findByNamePrefix("Ma").forEach(System.out::println);
    }
}
