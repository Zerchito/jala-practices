import java.util.ArrayList;
import java.util.Scanner;

public class PalabrasDificiles {
    public static void main(String[] args) {
        ArrayList<String> palabras = new ArrayList<>();
        leerPalabras(palabras);
        for (String palabra: palabras) {
            boolean esDificil = palabraEsDificil(palabra);
            System.out.println("La palabra " + palabra + (esDificil ? " es dificil": " no es dificil"));
        }
    }
    private static void leerPalabras(ArrayList<String> lista) {
        Scanner lector = new Scanner(System.in);
        System.out.print("Ingrese la cantidad de palabras: ");
        int numeroPalabrasAIngresar = lector.nextInt();
        // Para leer el "Enter" despues de leer el numero.
        lector.nextLine();
        for (int contadorDePalabras = 0; contadorDePalabras< numeroPalabrasAIngresar; contadorDePalabras++) {
            System.out.print("Ingrese una palabra: ");
            String palabra = lector.nextLine();
            lista.add(palabra);
        }
        lector.close();
    }

    private static boolean palabraEsDificil (String palabra) {
        String palabraMinuscula = palabra.toLowerCase();
        int contador = 0;
        for (int indice = 0; indice< palabraMinuscula.length() && contador < 4; indice++) {
            char caracterActual = palabraMinuscula.charAt(indice);
            if(!esVocal(caracterActual)){
                contador++;
            } else {
                contador = 0;
            }
        }
        return contador == 4;
    }

    private static boolean esVocal(char caracter) {
        return caracter == 'a' || caracter == 'e' || caracter == 'i' || caracter == 'o' || caracter == 'u';
    }
}
