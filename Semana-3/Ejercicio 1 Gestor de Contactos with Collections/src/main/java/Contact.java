import java.util.Objects;

public class Contact implements Comparable<Contact> {

    private String name;
    private String email;
    private String phone;

    public Contact(String name, String email, String phone) {
        this.name = name; this.email = email; this.phone = phone;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact c)) return false;
        return Objects.equals(email, c.email);
    }

    @Override
    public int hashCode() {
        // TODO: hash basado en email
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return String.format("Contact{name='%s', email='%s', phone='%s'}",
                name, email, phone);
    }
}
