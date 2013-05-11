package cl.udla.ia.puzzle;

import java.util.ArrayList;
import java.util.Stack;

public class BusquedaPorProfundidad extends Busqueda {
	
	public static void busqueda(int[] matriz)
	{
		Nodo root = new Nodo(new EstadoImpl(matriz));
		Stack<Nodo> stack = new Stack<Nodo>();
		stack.add(root);

		realizaBusqueda(stack);
	}

	
	public static void realizaBusqueda(Stack<Nodo> s)
	{
		int contadorIteraciones = 1; // cuenta las veces que se a entrado al metodo

		while (!s.isEmpty()) // revisa si la cola no esta vacia
		{
			Nodo tempNodo = (Nodo) s.pop();

			// El nodo no es el objetivo
			if (!tempNodo.getEstadoActual().isObjetivo())
			{
				// se obtienen los sucesores
				ArrayList<Estado> tempHijos = tempNodo.getEstadoActual()
						.generadorNuevosEstados();

				//recorro los estados hijos para convertirlos en nodos.
				for (int i = 0; i < tempHijos.size(); i++)
				{
					// paso los costos para ir sumandolos
					Nodo newNodo = new Nodo(tempNodo,
							tempHijos.get(i), tempNodo.getCosto()
									+ tempHijos.get(i).getCosto());

					if (!revisaRepetidos(newNodo))
					{
						s.add(newNodo);
						newNodo.getEstadoActual().ImprimeEstado();
						//cuento cuantas veces he buscado
						contadorIteraciones++;
					}
				}
				
			}
			else
				// si encontramos el objetivo
			{
				// se usa la estructura stack para guardar desde 
				// el estado  inicial al estado objetivo.
				Stack<Nodo> rutaSolucion = new Stack<Nodo>();
				rutaSolucion.push(tempNodo);
				tempNodo = tempNodo.getPadre();

				while (tempNodo.getPadre() != null)
				{
					rutaSolucion.push(tempNodo);
					tempNodo = tempNodo.getPadre();
				}
				//aca se hace push del primer nodo
				rutaSolucion.push(tempNodo);

				int tamanoRuta = rutaSolucion.size();

				for (int i = 0; i < tamanoRuta; i++)
				{
					tempNodo = rutaSolucion.pop();
					tempNodo.getEstadoActual().ImprimeEstado();
					System.out.println();
					System.out.println();
				}
				
				//System.out.println("Es costo total es de: " + tempNodo.getCosto());
				System.out.println("el numero de nodos examinados es: "
							+ contadorIteraciones);

				System.exit(0);
			}
		}
		System.out.println("Sin Solucion!!!!!");
	}
	
	
	
	
}
