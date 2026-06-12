public class SlackStrategy implements NotificationStrategy {

    @Override
    public void send(String message) {
        System.out.println("[SLACK] Enviando: " + message);
    }
}
