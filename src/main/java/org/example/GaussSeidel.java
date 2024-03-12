package org.example;

public class GaussSeidel {

    public static void main(String[] args) {
        // Definir el sistema de ecuaciones
        double[][] coeficientes = {
                {4, -1, 0, 3},
                {1, 5, 1, 7},
                {2, 0, 3, 4}
        };

        // Definir las aproximaciones iniciales
        double[] aproximacionesIniciales = {0, 0, 0};

        // Definir el número máximo de iteraciones
        int iteracionesMaximas = 100;

        // Definir el criterio de convergencia (puedes ajustar este valor según sea necesario)
        double tolerancia = 0.0001;

        // Llamar al método para resolver el sistema
        double[] solucion = gaussSeidel(coeficientes, aproximacionesIniciales, iteracionesMaximas, tolerancia);

        // Imprimir la solución
        System.out.println("Solución:");
        for (int i = 0; i < solucion.length; i++) {
            System.out.println("x" + (i + 1) + " = " + solucion[i]);
        }
    }

    public static double[] gaussSeidel(double[][] coeficientes, double[] aproximacionesIniciales, int iteracionesMaximas, double tolerancia) {
        int n = coeficientes.length;
        double[] solucion = new double[n];
        double[] solucionAnterior = new double[n];

        for (int iteracion = 0; iteracion < iteracionesMaximas; iteracion++) {
            for (int i = 0; i < n; i++) {
                solucionAnterior[i] = solucion[i];

                double suma = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        suma += coeficientes[i][j] * solucion[j];
                    }
                }

                solucion[i] = (coeficientes[i][n] - suma) / coeficientes[i][i];
            }

            // Verificar la convergencia
            if (converge(solucion, solucionAnterior, tolerancia)) {
                System.out.println("Convergencia alcanzada en la iteración " + (iteracion + 1));
                break;
            }
        }

        return solucion;
    }

    private static boolean converge(double[] solucion, double[] solucionAnterior, double tolerancia) {
        for (int i = 0; i < solucion.length; i++) {
            if (Math.abs(solucion[i] - solucionAnterior[i]) > tolerancia) {
                return false;
            }
        }
        return true;
    }
}

