package cl.udla.ia.puzzle;


public class Nodo
{

	private Estado estadoActual;
	private Nodo padre;
	private double costo; // este es el costo para llegar a este estado

	public Nodo(Estado s)
	{
		estadoActual = s;
		padre = null;
		costo = 0;
	}

	public Nodo(Nodo prev, Estado s, double c)
	{
		padre = prev;
		estadoActual = s;
		costo = c;
	}

	public Estado getEstadoActual() {
		return estadoActual;
	}

	public Nodo getPadre() {
		return padre;
	}

	public double getCosto() {
		return costo;
	}

}
