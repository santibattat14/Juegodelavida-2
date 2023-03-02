/*Copyright [2023] [Pablo de la Cruz Rodríguez y Santiago Battat Bevilacqua] Licensed under the Apache License, Version 2.0 (the "License")
; you may not use this file except in compliance with the License.
 You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, 
 software distributed under the License is distributed on an 
"AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language 
governing permissions and limitations under the License.
*/

package dominio;

import java.io.*;
import java.util.*;

// Especificamos la dimension de nuestra matriz

public class Tablero {
    private static final int DIMENSION = 30;
    private int[][] estadoActual;
    private int[][] estadoSiguiente;


    public Tablero() {
        this.estadoActual = new int[DIMENSION][DIMENSION];
        this.estadoSiguiente = new int[DIMENSION][DIMENSION];
    }

// Este metodo lee el contenido actual del fichero matriz.txt, para luego calcular el estado siguiente

     public void leerEstadoActual() {
        try (BufferedReader br = new BufferedReader(new FileReader("matriz.txt"))) {
            String linea;
            int fila = 0;
            while ((linea = br.readLine()) != null) {
                for (int col = 0; col < linea.length(); col++) {
                    estadoActual[fila][col] = Character.getNumericValue(linea.charAt(col));
                }
                fila++;
            }
            calcularEstadoSiguiente();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo matriz.txt: " + e.getMessage());
        }
    }

        
// El metodo montecarlo  

    public void generarEstadoActualPorMontecarlo() {
        Random random = new Random();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                estadoActual[i][j] = random.nextDouble() < 0.5 ? 1 : 0;
            }
        }

        calcularEstadoSiguiente();
    }

    public void transitarAlEstadoSiguiente() {
        int[][] temp = estadoActual;
        estadoActual = estadoSiguiente;
        estadoSiguiente = temp;

        calcularEstadoSiguiente();
    }

    // metodo privado para calcular el estado siguiente del tablero

    private void calcularEstadoSiguiente() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                int vecinasVivas = contarVecinasVivas(i, j);
                if (estadoActual[i][j] == 1) {
                    estadoSiguiente[i][j] = (vecinasVivas == 2 || vecinasVivas == 3) ? 1 : 0;
                } else {
                    estadoSiguiente[i][j] = (vecinasVivas == 3) ? 1 : 0;
                }
            }
        }
    }

    // El metodo cuentas las vecinas vivas que quedan en el tablero

    private int contarVecinasVivas(int fila, int columna) {
        int vecinasVivas = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int filaVecina = (fila + i + DIMENSION) % DIMENSION;
                int columnaVecina = (columna + j + DIMENSION) % DIMENSION;
                vecinasVivas += estadoActual[filaVecina][columnaVecina];
            }
        }
        return vecinasVivas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                sb.append(estadoActual[i][j] == 1 ? "*" : " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
