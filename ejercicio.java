/**
 *
 * @author Camilo Montt Fierro
 */
import java.util.*;

public class Ejercicio {

     // Función para encontrar la ruta más corta a todos los nodos utilizando el algoritmo de Dijkstra
    public static void encontrarRutaCorta(int[][] matrizDistancia, int nodoInicial) {
       
        int n = matrizDistancia.length; // Número de nodos en la matriz de distancia
        boolean[] visitado = new boolean[n]; // Arreglo para marcar los nodos visitados
        int[] distancia = new int[n]; // Arreglo para almacenar las distancias más cortas
        int[] rutaAnterior = new int[n]; // Arreglo para almacenar los nodos anteriores en la ruta más corta
       
        // Inicializar los arreglos de distancia y rutaAnterior
        Arrays.fill(distancia, Integer.MAX_VALUE);
        Arrays.fill(rutaAnterior, -1);
        distancia[nodoInicial] = 0; // La distancia al nodo inicial es 0
       
        for (int i = 0; i < n - 1; i++) {
            int nodoActual = encontrarNodoConDistanciaMinima(distancia, visitado); // Encontrar el nodo con la distancia más corta
           
            visitado[nodoActual] = true; // Marcar el nodo como visitado
           
            // Actualizar las distancias de los vecinos del nodo actual
            for (int j = 0; j < n; j++) {
                if (matrizDistancia[nodoActual][j] != 0 && !visitado[j]) {
                    int distanciaNueva = distancia[nodoActual] + matrizDistancia[nodoActual][j];
                    if (distanciaNueva < distancia[j]) {
                        distancia[j] = distanciaNueva;
                        rutaAnterior[j] = nodoActual;
                    }
                }
            }
        }
       
        // Mostrar la ruta más corta a todos los nodos
        for (int i = 0; i < n; i++) {
            if (i == nodoInicial) {
                continue; // Saltar el nodo inicial
            }
            System.out.print("Nodo " + (i + 1) + ": ");
            mostrarRutaEnOrden(rutaAnterior, i);
            System.out.println("Distancia: " + distancia[i]);
        }
    }
   
    // Función para encontrar el nodo con la distancia más corta que no ha sido visitado
    private static int encontrarNodoConDistanciaMinima(int[] distancia, boolean[] visitado) {
        int minDistancia = Integer.MAX_VALUE;
        int nodoMinimo = -1;
        for (int i = 0; i < distancia.length; i++) {
            if (!visitado[i] && distancia[i] < minDistancia) {
                minDistancia = distancia[i];
                nodoMinimo = i;
            }
        }
        return nodoMinimo;
    }
   
    // Función para mostrar la ruta en orden a partir del arreglo de nodos anteriores
    private static void mostrarRutaEnOrden(int[] rutaAnterior, int nodo) {
        if (rutaAnterior[nodo] == -1) {
            System.out.print((nodo + 1) + " ");
        } else {
            mostrarRutaEnOrden(rutaAnterior, rutaAnterior[nodo]);
            System.out.print((nodo + 1) + " ");
        }
    }
   
    // Ejemplo de uso
    public static void main(String[] args) {
        /*int[][] matrizDistancia = {{0, 2, 0, 0, 0, 0, 6, 0},
                                   {2, 0, 7, 0, 2, 0, 0, 0},
                                   {0, 7, 0, 3, 0, 3, 0, 0},
                                   {0, 0, 3, 0, 0, 0, 0, 2},
                                   {0, 2, 0, 0, 0, 2, 1, 0},
                                   {0, 0, 3, 0, 2, 0, 0, 2},
                                   {6, 0, 0, 0, 1, 0, 0, 4},
                                   {0, 0, 0, 2, 0, 2, 4, 0}};*/
        //inicializar nodoInicial y cantidad de nodos
        int nodoInicial = 0;
        int n;
       
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la cantidad de nodos: ");
        n = sc.nextInt();
       
        //inicializar matriz que contiene las distancias a cada nodo, si es 0 no tienen conexión
        int[][] grafo = new int[n][n];
               
        for(int i=0;i<n;i++){
            System.out.println("Ingrese la fila " + i);
            for(int j=0;j<n;j++){
                int g = sc.nextInt();
                grafo[i][j] = g;
            }
        }
       
        encontrarRutaCorta(grafo, nodoInicial);
    }
   
}
