public enum TicketStatus {
    OPEN, IN_PROGRESS, RESOLVED, CLOSED;

    public boolean canTransitionTo(TicketStatus target) {
        // TODO: definir transiciones validas
        // OPEN -> IN_PROGRESS
        // IN_PROGRESS -> RESOLVED o OPEN (reabrir)
        // RESOLVED -> CLOSED o IN_PROGRESS (reabrir)
        // CLOSED -> ninguno
        return switch (this) {
            case OPEN -> target == IN_PROGRESS;
            case IN_PROGRESS -> target == OPEN ||  target == RESOLVED ;
            case RESOLVED ->  target == CLOSED || target == IN_PROGRESS;
            case CLOSED -> false;
        };
    }

}

