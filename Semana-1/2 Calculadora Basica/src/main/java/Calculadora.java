public class Calculadora {

    //Declaracion de metodos sobrecargados
    public static int sumar(int a, int b) {
        return a + b;
    }
    //Metodo sobrecargado para sumar doubles
    public static double sumar(double a, double b) {
        return a + b;
    }
    //Metodo sobrecargado para sumar 3 valores
    public static int sumar(int a, int b, int c) {
        return a + b + c;
    }
    //Metodo sobrecargado para sumar un arreglo de numeros enteros
    public static int sumar(int[] numeros){
        int total = 0;
        for(int i = 0; i < numeros.length; i++){
            total += numeros[i];
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("2 + 3 = " + sumar(2, 3));
        System.out.println("2.5 + 3.7 = " + sumar(2.5, 3.7));
        System.out.println("1 + 2 + 3 = " + sumar(1, 2, 3));

        int[] nums = {10, 20, 30, 40};
        System.out.println("Array suma = " + sumar(nums));
    }
}
