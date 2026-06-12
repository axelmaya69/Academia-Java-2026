public class Ticket {
    private final int id;
    private final String description;
    private final Priority priority;
    private TicketStatus status;

    public Ticket(int id, String description, Priority priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.status = TicketStatus.OPEN;
    }

    public void transitionTo(TicketStatus newStatus) {
        if (this.status.canTransitionTo(newStatus)) {
            TicketStatus estadoAnterior = this.status;
            this.status = newStatus;
            System.out.printf("Ticket %d: %s -> %s%n", id, estadoAnterior, newStatus);
        } else {
            System.out.printf("Error, No se puede transicionar de %s a %s%n", this.status, newStatus);
        }
    }

    // TODO: getters
    public int getId() { return id; }
    public Priority getPriority() { return priority; }
    public TicketStatus getStatus() { return status; }

    @Override
    public String toString() {
        return String.format("Ticket{id=%d, desc='%s', priority=%s, status=%s}",
                id, description, priority.getLabel(), status);
    }
}
