import java.util.Arrays;
import java.util.Random;

public class SopaDeLetras {
    public static void main(String[] args) {
        String[][] sopa = null;
        String[] palabras = null;
        for (String parametro: args) {
            parametro = parametro.toUpperCase();
            String[] partesDelParametro = parametro.split("=");
            String nombreParametro = partesDelParametro.length > 0 ? partesDelParametro[0] : "";
            String contenidoParametro = partesDelParametro.length > 1 ? partesDelParametro[1] : "";
            if (nombreParametro.equals("SOPA")) {
                sopa = transformarAMatriz(contenidoParametro);

                continue;
            }
            if (nombreParametro.equals("P")) {
                palabras = transformarAPalabras(contenidoParametro);
            }
        }
        mostrarMatriz(sopa);
        System.out.println(Arrays.toString(palabras));
        buscarPalabras(sopa, palabras);
    }

    private static String[][] transformarAMatriz (String sopa) {
        String[] filas = sopa.split("#");
        int cantidadFilas = filas.length;
        int cantidadColumnas = 0;

        for (String fila: filas){
            cantidadColumnas = Math.max(fila.length(), cantidadColumnas);
        }

        String[][] respuesta = new String[cantidadFilas][cantidadColumnas];

        for (int i = 0; i < cantidadFilas; i++) {
            String filaActual = filas[i];
            for (int j = 0; j < cantidadColumnas; j++ ) {
                String contenido = filaActual.length() > j ? filaActual.charAt(j) + "" : getCaracterAleatorio();
                respuesta[i][j] = contenido;
            }
        }
        return respuesta;
    }

    private static String[] transformarAPalabras (String contenido){
        return contenido.split(",");
    }

    private static void mostrarMatriz(String[][] matriz){
        for(String[] fila: matriz ) {
            System.out.println(Arrays.toString(fila));
        }
    }

    private static String getCaracterAleatorio() {
        Random generador = new Random();
        int numeroAleatorio = generador.nextInt(0,26);
//        int numeroAleatorio = (int) ( Math.random() * 26 );
        char caracter = (char) ('A' + numeroAleatorio);
        return String.valueOf(caracter);
    }

    private static void buscarPalabras(String[][] sopa, String[] palabras) {
        for (String palabra: palabras) {
            boolean existe = buscarPalabra(sopa, palabra);
            System.out.println("palabra: " + palabra + "= " + existe);
        }
    }

    private static boolean buscarPalabra(String[][] sopa, String palabra) {
        boolean existe = false;
        for(int fila = 0 ; fila < sopa.length; fila++){
            for(int columna = 0; columna < sopa[fila].length; columna++) {

                if (columna + palabra.length() < sopa[fila].length) {
                    String palabraActual = "";
                    for (int indice = columna; indice< columna + palabra.length(); indice++){
                        palabraActual += sopa[fila][indice];
                    }
                    existe = existe || palabra.equals(palabraActual);
                }
                if(fila + palabra.length() < sopa.length) {
                    // para ir abajo
                }
                if(columna - palabra.length() + 1 >= 0) {
                    String palabraActual = "";
                    for (int indice = columna; indice >= columna - palabra.length() + 1; indice--){
                        palabraActual += sopa[fila][indice];
                    }
                    existe = existe || palabra.equals(palabraActual);
                }
                if(fila - palabra.length() >= 0) {
                    // para ir arriba
                }
                if(columna + palabra.length() < sopa[fila].length && fila - palabra.length() +1 >= 0){
                    // diagonal derecha arriba
                    String palabraActual = "";
                    int indiceColumna = columna;
                    int indiceFila = fila;
                    int contador = 0;
                    while (contador< palabra.length()) {
                        palabraActual += sopa[indiceFila][indiceColumna];
                        indiceFila--;
                        indiceColumna++;
                        contador++;
                    }
                    existe = existe || palabra.equals(palabraActual);
                }
            }
        }
        return existe;
    }
}
