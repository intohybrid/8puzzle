package cl.udla.ia.puzzle;

public abstract class Busqueda {

	public static boolean revisaRepetidos(Nodo n)
	{
		boolean retValue = false;
		Nodo checkNode = n;

		while (n.getPadre() != null && !retValue)
		{
			
			if (n.getPadre().getEstadoActual().equals(checkNode.getEstadoActual()))
			{
				retValue = true;
			}
			n = n.getPadre();
		}

		return retValue;
	}
}