package cl.udla.ia.puzzle;

public class Main {

	public static void main(String[] args) {

		//int[] estadoInicial = {2 , 8 , 3, 1 , 6 , 4, 7 , 0 , 5};
		//int[] estadoInicial = {4 , 8 , 1 , 3 , 0 , 2 , 7 , 6 , 5};
		int[] estadoInicial = {5 , 2 , 3 , 0 , 4 , 8 , 7 , 6 , 1 };

		// int[] estadoInicial = { 8, 1, 6, 2, 0, 5, 3, 7, 4 };
		// int[] estadoInicial = {0 , 1 , 3 , 4 , 2 , 5 , 7 , 8 , 6};

		
        if(tieneSolucion(estadoInicial)) {
        	// Se debe descomentar l el tipo de busqueda que se desea
    		// BusquedaPorProfundidad.busqueda(estadoInicial);
    		BusquedaPorAnchura.busqueda(estadoInicial);
        } else { 
        	System.out.println("El estado inicial insertado no tiene solucion, el numero de sus inversiones es impar");
        }
		
		System.exit(-1);
	}

	public static boolean tieneSolucion(int[] estado) {

		int numeroDeValores = estado.length; // 9
		int dim = (int) Math.sqrt(numeroDeValores); // 3
		int inversiones = 0;

		for (int i = 0; i < numeroDeValores; ++i) {
			int iValor = estado[i];
			if (iValor != 0) {
				for (int j = i + 1; j < numeroDeValores; ++j) {
					int jValor = estado[j];
					if (jValor != 0 && jValor < iValor) {
						++inversiones;
					}
				}
			} else {
				if (!esImpar(dim)) {
					inversiones += (1 + i / dim);
				}
			}
		}

		if (esImpar(inversiones))
			return false;
		return true;
	}

	public static boolean esImpar(int iNumero) {
		if (iNumero % 2 != 0)
			return true;
		else
			return false;
	}

}
