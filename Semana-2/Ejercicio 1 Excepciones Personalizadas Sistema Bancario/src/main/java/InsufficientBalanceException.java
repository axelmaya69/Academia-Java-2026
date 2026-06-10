public class InsufficientBalanceException  extends Exception {
    private final double deficit;

    public InsufficientBalanceException(String mensaje,double deficit) {
        super(mensaje);
        this.deficit = deficit;
    }
    public double getDeficit() {
        return deficit;
    }
}
