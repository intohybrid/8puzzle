package cl.udla.ia.puzzle;

import java.util.ArrayList;

public interface Estado
{
	boolean isObjetivo();

	// generate successors to the current state
	ArrayList<Estado> generadorNuevosEstados();

	// determine cost from initial state to THIS state
	double getCosto();

	// print the current state
	public void ImprimeEstado();

	// compare the actual state data
	public boolean equals(Estado s);
}
