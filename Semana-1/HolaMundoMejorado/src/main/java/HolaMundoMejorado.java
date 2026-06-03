public class HolaMundoMejorado {
    public static void main(String[] args) {

        String nombre = "Axel Daniel";
        int edad = 25;
        double altura = 1.67;
        boolean esActivo = true;

        String mensaje1 = "Me llamo "+ nombre + ", tengo "+ edad+" años, mido "+
                altura+" m y estoy " + (esActivo ? "activo" : "inactivo") +".";
        System.out.println(mensaje1);

        String mensaje2 = String.format("Me llamo %s, tengo %d años, mido %.2f m y estoy %s.",
                nombre, edad, altura, esActivo ? "activo" : "inactivo");
        System.out.println(mensaje2);
    }
}
