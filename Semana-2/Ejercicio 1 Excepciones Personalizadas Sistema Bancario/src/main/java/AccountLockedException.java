public class AccountLockedException extends Exception {
    String mensaje;

    public AccountLockedException(String mensaje) {
    this.mensaje= mensaje;
    }
}
