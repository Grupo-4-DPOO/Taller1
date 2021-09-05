package uniandes.dpoo.modelo;

public class ProductoMenu implements Producto{
	private String nombre;
	private int precioBase;
	
	public ProductoMenu(String nombreP, int precioP)
	{
		this.nombre = nombreP;
		this.precioBase = precioP;
	}
	public String getNombre(){
		return nombre;
	}
	public int getPrecio(){
		return precioBase;
	}
	public String generarTextoFactura(){
		return "Item: " + nombre + " precio: " + precioBase;
	}
	
}
