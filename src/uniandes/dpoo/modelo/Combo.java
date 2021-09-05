package uniandes.dpoo.modelo;

import java.util.ArrayList;

public class Combo implements Producto {
	private String nombreCombo;
	private int descuento;
	private ArrayList<ProductoMenu> itemsCombo;    // SIMPLIFICACION: Lista de nombres de los productos del menu que componen el combo. Revisar el diagrama de clases
	
	/**
	 * Constructor de la clase
	 */
	public Combo(String nombreP, int descuentoP)
	{
		this.nombreCombo = nombreP;
		this.descuento = descuentoP;
		this.itemsCombo = new ArrayList<ProductoMenu>();    
	}

	public void agregarItemACombo(Producto itemCombo)
	{
		this.itemsCombo.add((ProductoMenu) itemCombo);
	}
	public String getNombre()
	{
		return nombreCombo;
	}
	
	public int getPrecio()
	{
		int precio = 0;
		for (ProductoMenu elemento: itemsCombo) {
			precio+= elemento.getPrecio();
		}
		double multiplicacion = ((int) (descuento))/100;
		System.out.println(multiplicacion);
		return precio;
	}
	
	public String generarTextoFactura(){
		String informacion = "";
		for (ProductoMenu elemento: itemsCombo) {
			informacion += "\nItem: " + elemento.getNombre() + " precio: " + elemento.getPrecio();
		}
		informacion += "\n Precio Total: " + getPrecio();
		return informacion;
	}
}
