public class EventFramework {
    public static void main(String[] args) {
        EventBus bus = new EventBus();

        // Suscribir handlers con lambdas
        bus.subscribe(EventType.ORDER_PLACED,
                data -> System.out.println("  [LOG] " + data));
        bus.subscribe(EventType.ORDER_SHIPPED,
                data -> System.out.println("  [LOG] " + data));
        bus.subscribe(EventType.ORDER_CANCELLED,
                data -> System.out.println("  [ALERTA] " + data));

        // Crear servicio con Email strategy
        System.out.println("=== Ordenes con Email ===");
        OrderService service = new OrderService(bus, new EmailStrategy());
        try {
            service.placeOrder("ORD-001", 299.99);
            service.shipOrder("ORD-001");
        } catch (OrderProcessingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Orden invalida
        System.out.println("\n=== Orden Invalida ===");
        try {
            service.placeOrder("ORD-002", -50.00);
        } catch (OrderProcessingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Cambiar estrategia a SMS
        System.out.println("\n=== Cambiar a SMS Strategy ===");
        OrderService serviceSms = new OrderService(bus, new SmsStrategy());
        try {
            serviceSms.placeOrder("ORD-003", 150.00);
            serviceSms.cancelOrder("ORD-003");
        } catch (OrderProcessingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
