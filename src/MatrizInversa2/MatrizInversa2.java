/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MatrizInversa2;

import java.util.Scanner;

/**
 *
 * @author luisk
 */
public class MatrizInversa2 {
    // Metodo para ver si la matriz es cuadrada 
    public static boolean esMatrizCuadrada(double[][] matriz) {
        return matriz.length == matriz[0].length;
    }

    // Método para imprimir una matriz
    private static void imprimirMatriz(double[][] matriz) {
        for (double[] fila : matriz) {
            for (double elemento : fila) {
                System.out.printf("%.2f" , elemento );
            }
            System.out.println();
        }
    }
    // Método para calcular la matriz inversa utilizando el método de Gaus
    private static double[][] calcularMatrizInversa(double[][] matriz) {
        int n = matriz.length;
        double[][] maAumentada = new double[n][2 * n];
        
        // Crear la matriz identidad
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            maAumentada[i][j] = matriz[i][j];
            maAumentada[i][j + n] = (i == j) ? 1 : 0;
        }
    }
    // Aplicar Gauss-Jordan para obtener la matriz inversa
    for (int i = 0; i < n; i++) {
        // Escalonar la fila i
        double pivote = maAumentada[i][i];
        if (pivote == 0) {
            return null; // La matriz no tiene inversa
        }

        for (int j = 0; j < 2 * n; j++) {
            maAumentada[i][j] /= pivote;
        }
        // Eliminación hacia abajo y hacia arriba del pivote
        for (int k = 0; k < n; k++) {
            if (k != i) {
                double factor = maAumentada[k][i];
                for (int j = 0; j < 2 * n; j++) {
                    maAumentada[k][j] -= factor * maAumentada[i][j];
                }
            }
        }
    }
    // Extraer la matriz inversa de la matriz aumentada
    double[][] matrizInversa = new double[n][n];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            matrizInversa[i][j] = maAumentada[i][j + n];
        }
    }

    return matrizInversa;
}

// Método para calcular e imprimir la matriz inversa
    private static void calcularEImprimirInversa(double[][] matriz) {
        if (esMatrizCuadrada(matriz)) {
            double[][] matrizInversa = calcularMatrizInversa(matriz);

            if (matrizInversa != null) {
                System.out.println("La matriz inversa es:");
                imprimirMatriz(matrizInversa);
            } else {
                System.out.println("La matriz no tiene inversa.");
            }
        } else {
            System.out.println("La matriz ingresada no es cuadrada asi que no se puede calcular la inversa.");
        }
    }
    public static double[][]ingresarValores (int filas, int columnas, char nombre){
     Scanner scanner = new Scanner(System.in);
     int vuelta;
     double [][] matriz = new double [filas][columnas];
     for (vuelta = 0; vuelta <filas; vuelta++) {
         System.out.println("Ingresa los valores de la matriz "+nombre+" en la fila: "+ vuelta);
                for (int a = 0; a <columnas; a++) {
                    matriz[vuelta][a] = scanner.nextInt();
             
         }
        }
     return matriz;
     }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese el número de filas de la matriz:");
        int filas = scanner.nextInt();

        System.out.println("Ingrese el número de columnas de la matriz:");
        int columnas = scanner.nextInt();
        double[][] matriz = new double[filas][columnas];
        if (matriz[0].length == matriz.length){
        // Obtener los valores de la matriz ingresados por el usuario
        matriz = ingresarValores(filas,columnas, 'A');

        calcularEImprimirInversa(matriz);
        }else {
            System.out.println("\"Accion no posible \n la matriz no tiene el mismo numero de filas que de columnas\n es decir no es Cuadrada");
        }
        
}
}
