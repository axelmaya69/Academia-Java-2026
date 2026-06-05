public class ManipuladorStrings {

    public static String invertir(String s) {
        StringBuilder manipular = new StringBuilder();

        for (int i=s.length()-1; i>=0; i--) {
            manipular.append(s.charAt(i));
        }
        return manipular.toString();
    }

    public static boolean esPalindromo(String s) {
        s = s.toLowerCase();
        s = s.replaceAll("\\s+", "");
        boolean palindromo=false;

        int j = s.length()-1;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == s.charAt(j)) {
                j--;
                 palindromo = true;
            } else  {
                 palindromo = false;
                break;
            }
        }
        return palindromo;
    }

    public static int contarVocales(String s) {
        int count = 0;
        String vocales = "aeiouAEIOU";

        for(int i=0; i<s.length(); i++) {
            for(int j=0; j<vocales.length(); j++) {
            if(s.charAt(i)==vocales.charAt(j)) {
                count++;
            }
            }
        }
        return count;
    }

    public static String construirPiramide(int niveles) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= niveles; i++) {
            for (int j = 0; j < niveles - i; j++) {
                sb.append(" ");
            }
                for (int j = 0; j < 2 * i - 1; j++) {
                    sb.append("*");
                }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Invertir 'Hola Mundo': "
                + invertir("Hola Mundo"));
        System.out.println("'Anita lava la tina' es palindromo: "
                + esPalindromo("Anita lava la tina"));
        System.out.println("Vocales en 'Murcielago': "
                + contarVocales("Murcielago"));
        System.out.println("Piramide de 5 niveles:");
        System.out.println(construirPiramide(5));
    }
}
