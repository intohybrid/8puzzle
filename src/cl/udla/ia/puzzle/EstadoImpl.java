package cl.udla.ia.puzzle;

import java.util.ArrayList;
import java.util.Arrays;

public class EstadoImpl implements Estado {

	private final int TOTALPOSICIONES = 9;

	private final int[] OBJETIVO = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 0 };
	private int[] matrizActual;

	public EstadoImpl(int[] matriz) {
		matrizActual = matriz;
	}

	// Costo que se le asocia por llegar a este Estado
	@Override
	public double getCosto() {
		return 1;
	}

	/*
	 * cuenta los valores fuera de lugar
	 */

	private int getVacio() {
		int vacioIndex = -1;

		for (int i = 0; i < TOTALPOSICIONES; i++) {
			if (matrizActual[i] == 0)
				vacioIndex = i;
		}
		return vacioIndex;
	}


	/*
	 * devuelve una copia de la matriz que se le pase como parametro
	 */
	private int[] copiaMatriz(int[] state) {
		int[] ret = new int[TOTALPOSICIONES];
		for (int i = 0; i < TOTALPOSICIONES; i++) {
			ret[i] = state[i];
		}
		return ret;
	}

	@Override
	public ArrayList<Estado> generadorNuevosEstados() {
		ArrayList<Estado> hijos = new ArrayList<Estado>();
		int vacio = getVacio();

		// mover izquierda el vacio
		if (vacio != 0 && vacio != 3 && vacio != 6) {
			intercambiaGuarda(vacio - 1, vacio, hijos);
		}

		// bajar vacio
		if (vacio != 6 && vacio != 7 && vacio != 8) {
			intercambiaGuarda(vacio + 3, vacio, hijos);
		}

		// subir vacio
		if (vacio != 0 && vacio != 1 && vacio != 2) {
			intercambiaGuarda(vacio - 3, vacio, hijos);
		}
		// cambiar vacio a derecha
		if (vacio != 2 && vacio != 5 && vacio != 8) {
			intercambiaGuarda(vacio + 1, vacio, hijos);
		}

		return hijos;
	}

	// copia la matriz y luego en la copia cambia los
	// valores. Despues guarda el nuevo estado
	private void intercambiaGuarda(int d1, int d2, ArrayList<Estado> hijos) {
		int[] copia = copiaMatriz(matrizActual);
		int temp = copia[d1];
		copia[d1] = matrizActual[d2];
		copia[d2] = temp;
		hijos.add((new EstadoImpl(copia)));
	}

	// compara el estado actual con el objetivo
	@Override
	public boolean isObjetivo() {
		if (Arrays.equals(matrizActual, OBJETIVO)) {
			return true;
		}
		return false;
	}

	@Override
	public void ImprimeEstado() {
		
		System.out.println(matrizActual[0] + " | " + matrizActual[1] + " | "
				+ matrizActual[2]);
		System.out.println("---------");
		System.out.println(matrizActual[3] + " | " + matrizActual[4] + " | "
				+ matrizActual[5]);
		System.out.println("---------");
		System.out.println(matrizActual[6] + " | " + matrizActual[7] + " | "
				+ matrizActual[8]);
		System.out.println(" ");
		System.out.println(" ");
		
	}

	@Override
	public boolean equals(Estado s) {
		if (Arrays.equals(matrizActual, ((EstadoImpl) s).getMatrizActual())) {
			return true;
		} else
			return false;

	}

	public int[] getMatrizActual() {
		return matrizActual;
	}

}
