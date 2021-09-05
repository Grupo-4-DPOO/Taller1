package uniandes.dpoo.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class Restaurante {
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<ProductoMenu> menuBase;
	private Pedido pedidoEnCurso;
	private ArrayList<Pedido> pedidos;
	private ArrayList<Combo> combos;
	private int id;
	
	public Restaurante()
	{
		this.ingredientes = new ArrayList<Ingrediente>();
		this.menuBase = new ArrayList<ProductoMenu>();
		this.pedidoEnCurso = null;
		this.pedidos = 	new ArrayList<Pedido>();	
		this.combos = 	new ArrayList<Combo>();
		this.id = 0;
	}

	/**
	 * Agrega el nombre de un productoMenu a un combo. Metodo simplificado.
	 * @param nombreProducto
	 */

	public void comenzarPedido( String nombreC, String direccionC)
	{
		pedidoEnCurso = new Pedido(id, id, nombreC, direccionC);
		id++;
	}
	
	public String obtenerFactura() {
		return pedidoEnCurso.factura();
	}
	
	
	public void cerrarYGuardarPedido()
	{
		pedidos.add(pedidoEnCurso);
		pedidoEnCurso = null;
	}
	public Pedido getpedidoactual()
	{
		return pedidoEnCurso;
	}
	public void agregarProductoAPedido(int numero)
	{
		Producto temporal = menuBase.get(numero-1);
		pedidoEnCurso.agregarProducto(temporal);		
	}
	public void agregarCombo(int numero)
	{
		Producto temporal = combos.get(numero-1);
		pedidoEnCurso.agregarProducto(temporal);
	}

	public ArrayList<ProductoMenu> getMenubase()
	{
		return menuBase;
	}
	public ArrayList<Combo> getCombos()
	{
		return combos;
	}
	public ArrayList<Ingrediente> getIngredientes()
	{
		return ingredientes;
	}
	public void cargarInformacion(File archivoCombos, File archivomenu, File archivoIngredientes) throws IOException
	{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivomenu);
		cargarCombos(archivoCombos);
	}
	public void cargarIngredientes(File archivoIngredientes) throws IOException
	{
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		// "Abrir" el archivo de Ingredientes y leerlo linea por linea usando un BufferedReader
		BufferedReader br = new BufferedReader(new FileReader (archivoIngredientes.getPath()));
		String linea = br.readLine();   // Leer la linea con el primer ProductoMenu en el archivo   
		// Un ProductoMenu tiene la forma: nombre;precioBase
		while (linea != null) // Cuando se llegue al final del archivo, linea tendra el valor null
		{
			// Separar los valores que estan en la linea por el caracter ';'
			String[] partes = linea.split(";");
			
			Ingrediente nuevoIngrediente = new Ingrediente(partes[0], Integer.parseInt(partes[1]));   // Creacion de un objeto ingredientesu con su nombre y precio base
			ingredientes.add( nuevoIngrediente );

			linea = br.readLine(); // leer la proxima linea en el archivo con un nuevo ProductoMenu 
		}

		br.close();
	}
	public void cargarCombos(File archivoCombos) throws IOException
	{
		ArrayList<Combo> combos = new ArrayList<Combo>();
		// "Abrir" el archivo de Combos y leerlo linea por linea usando un BufferedReader
		BufferedReader br = new BufferedReader(new FileReader(archivoCombos.getPath()));
		String linea = br.readLine();   // Leer la linea con el primer combo en el archivo   
		// Un combo tiene la forma: nombre;porcentaje;productoMenu1;productoMenu2;productoMenu3;...
		while (linea != null) // Cuando se llegue al final del archivo, linea tendra el valor null
		{
			// Separar los valores que estan en la linea por el caracter ';'
			String[] partes = linea.split(";");
			String[] porcentaje = partes[1].split("%");
			int descuento = Integer.parseInt(porcentaje[0]);

			Combo nuevoCombo = new Combo(partes[0], descuento);   // creacion de un objeto Combo con su nombre y porcentaje de descuento
			for (ProductoMenu elemento: menuBase) {
				if(elemento.getNombre().equals(partes[2]) || elemento.getNombre().equals(partes[3]) || elemento.getNombre().equals(partes[4])){
					nuevoCombo.agregarItemACombo(elemento);
				}
			}


			this.combos.add( nuevoCombo );

			linea = br.readLine(); // leer la proxima linea en el archivo con un nuevo Combo 
		}
		System.out.println(combos);
		br.close();
	}
	public void cargarMenu(File archivomenu) throws IOException
	{
		ArrayList<ProductoMenu> productosMenu = new ArrayList<ProductoMenu>();
		// "Abrir" el archivo de ProductoMenu y leerlo linea por linea usando un BufferedReader
		BufferedReader br = new BufferedReader(new FileReader (archivomenu.getPath()));
		String linea = br.readLine();   // Leer la linea con el primer ProductoMenu en el archivo   
		// Un ProductoMenu tiene la forma: nombre;precioBase
		while (linea != null) // Cuando se llegue al final del archivo, linea tendra el valor null
		{
			// Separar los valores que estan en la linea por el caracter ';'
			String[] partes = linea.split(";");
			
			ProductoMenu nuevoProducto = new ProductoMenu(partes[0], Integer.parseInt(partes[1]));   // Creacion de un objeto ProductoMenu con su nombre y precio base
			menuBase.add( nuevoProducto );

			linea = br.readLine(); // leer la proxima linea en el archivo con un nuevo ProductoMenu 
		}
		System.out.println(menuBase);
		br.close();
	}
	
}



