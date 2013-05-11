package cl.udla.ia.puzzle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BusquedaPorAnchura extends Busqueda
{

	public static void busqueda(int[] matriz)
	{
		Nodo root = new Nodo(new EstadoImpl(matriz));
		Queue<Nodo> queue = new LinkedList<Nodo>();
		queue.add(root);
		
		realizarBusqueda(queue);
	}


	public static void realizarBusqueda(Queue<Nodo> q)
	{
		int contadorIteraciones = 1; // cuenta las veces que se a entrado al metodo

		while (!q.isEmpty()) // revisa si la cola no esta vacia
		{
			Nodo tempNodo = (Nodo) q.poll();

			if (!tempNodo.getEstadoActual().isObjetivo()) // si no es nodo buscado, pedimos generar sus hijos.
			{
				ArrayList<Estado> tempHijos = tempNodo.getEstadoActual()
						.generadorNuevosEstados(); 
											
				//recorro los estados hijos para convertirlos en nodos.
				for (int i = 0; i < tempHijos.size(); i++)
				{
					// paso los costos para ir sumandolos
					
					Nodo newNodo = new Nodo(tempNodo,
							tempHijos.get(i), tempNodo.getCosto()
									+ tempHijos.get(i).getCosto());
					
					//busco si de los nuevos sucesores tengo repetidos
					//para agregarlos a la cola
					if (!revisaRepetidos(newNodo))
					{
						q.add(newNodo);
						newNodo.getEstadoActual().ImprimeEstado();
					}
				}
				//cuento cuantas veces he buscado
				contadorIteraciones++;
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
