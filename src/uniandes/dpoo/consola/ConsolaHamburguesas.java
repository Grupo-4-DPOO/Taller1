package uniandes.dpoo.consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import uniandes.dpoo.modelo.Combo;
import uniandes.dpoo.modelo.Ingrediente;
import uniandes.dpoo.modelo.ProductoMenu;
import uniandes.dpoo.modelo.Restaurante;


public class ConsolaHamburguesas {
	private Restaurante restaurante;

	public ConsolaHamburguesas() throws IOException 
	{
		restaurante = new Restaurante();
		ejecutarOpcion();
	}

	/**
	 * Leer el archivo de Productos Menu y obtener su informacion en una lista de objetos ProductoMenu
	 * @param rutaArchivo
	 * @return Lista de objetos ProductoMenu
	 * @throws IOException 
	 */
	public ArrayList<ProductoMenu> ejecutarCargarProductosMenu(String rutaArchivo) throws IOException
	{
		restaurante.cargarMenu(new File(rutaArchivo));
		return restaurante.getMenubase();
	}

	/**
	 * Leer el archivo de combos y obtener su informacion en una lista de objetos Combo
	 * @param rutaArchivo
	 * @param productosMenu Lista de productos menu donde aparecen todos los posibles productos de un combo
	 * @return Lista de objetos Combo
	 * @throws IOException 
	 */
	public ArrayList<Combo> ejecutarCargarCombos(String rutaArchivo) throws IOException
	{
		restaurante.cargarCombos(new File(rutaArchivo));
		return restaurante.getCombos();
	}
	public ArrayList<Ingrediente> ejecutarCargarIngredientes(String rutaArchivo) throws IOException
	{
		restaurante.cargarIngredientes(new File(rutaArchivo));
		return restaurante.getIngredientes();
	}
	
	public static void main(String[] args) throws IOException 
	{
		ConsolaHamburguesas  consola = new ConsolaHamburguesas();
		System.out.println("Inicio de ejecución de la aplicación");
	}
	
	public void ejecutarOpcion() throws IOException 
	{
		System.out.println("Bienvenido al menu de Mc. Donals :D ");
		Scanner reader = new Scanner(System.in);

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				
				System.out.println("Por favor, elija una opción");
				int opcion_seleccionada = reader.nextInt();
				reader.nextLine();
				if (opcion_seleccionada == 1)
				{
					System.out.println("ingrese ruta ingredientes: ");
					String ruta1 = reader.nextLine();
					System.out.println("ingrese ruta menu: ");
					String ruta2 = reader.nextLine();
					System.out.println("ingrese ruta Combos: ");
					String ruta3 = reader.nextLine();
					cargar(ruta1, ruta2, ruta3);
				}
				else if (opcion_seleccionada == 2)
				{
					mostrarMenu1();
				}
				else if (opcion_seleccionada == 3)
				{
					mostrarCombos();
				}
				else if (opcion_seleccionada == 4)
				{
					mostrarMenu1();
					System.out.println("Por favor, elija una opción");
					int opcion = reader.nextInt();
					agregarMenuAPedido(opcion);
				}
				else if (opcion_seleccionada == 5)
				{
					mostrarCombos();
					System.out.println("Por favor, elija una opción");
					int opcion = reader.nextInt();
					agregarComboAPedido(opcion);
				}
				
				else if (opcion_seleccionada == 6)
				{
					System.out.println("ingrese nombre cliente: ");
					String ruta1 = reader.nextLine();
					System.out.println("ingrese direccion: ");
					String ruta2 = reader.nextLine();
					restaurante.comenzarPedido(ruta1, ruta2);
				}
				
				else if (opcion_seleccionada == 7)
				{
					terminarPedido();
				}
				else
				{
					System.out.println("Por favor seleccione una opciÃ³n vÃ¡lida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los nÃºmeros de las opciones.");
			}
		}
	}


	public void mostrarMenu()
	{	
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Cargar");
		System.out.println("2. Mostrar Menu");
		System.out.println("3. Mostrar Combos");
		System.out.println("4. Agregar Menu a pedido");
		System.out.println("5. Agregar Combo a pedido");
		System.out.println("6. Iniciar pedido");
		System.out.println("7. Terminar pedido");
	}
	
	public void cargar(String rutaingredientes, String rutamenu, String rutacombos) throws IOException 
	{
		ejecutarCargarIngredientes(rutaingredientes);
		ejecutarCargarProductosMenu(rutamenu);
		ejecutarCargarCombos(rutacombos);
		
	}
	
	public void mostrarMenu1() 
	{
		int opcion = 1;
		for(ProductoMenu elemento: restaurante.getMenubase()){
			System.out.println(opcion + "  " + elemento.getNombre());
		opcion++;
		}
	}
	
	public void mostrarCombos() 
	{
		int opcion = 1;
		for (Combo elemento: restaurante.getCombos()) {
			System.out.println(opcion + "  " + elemento.getNombre());
		opcion++;
		}
	}
	
	public void agregarMenuAPedido(int numero) 
	{
		restaurante.agregarProductoAPedido(numero);
	}
	
	public void agregarComboAPedido(int numero) 
	{
		restaurante.agregarCombo(numero);

	}
	
	public void terminarPedido() 
	{
		System.out.println(restaurante.obtenerFactura());
		restaurante.cerrarYGuardarPedido();
	}

}
