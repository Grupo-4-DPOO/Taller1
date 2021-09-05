package uniandes.dpoo.modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto {
		private ProductoMenu base;
		private ArrayList<Ingrediente> agregados;
		private ArrayList<Ingrediente> eliminados;
		public ProductoAjustado(ProductoMenu basep)
		{
			this.base = basep;
			this.agregados = new  ArrayList<Ingrediente>();
			this.eliminados = new  ArrayList<Ingrediente>();
		}
		public String getNombre(){
			return base.getNombre();
		}
		public int getPrecio(){
			int preciob = base.getPrecio();
			for(Ingrediente elemento: agregados) {
				preciob += elemento.getCostoAdicional();
			}
			return preciob;
		}
		public String generarTextoFactura(){
			String informacion = "Item: " + base.getNombre() + " precio: " + base.getPrecio();
			for (Ingrediente elemento: agregados) {
				informacion += "\nItem: " + elemento.getNombre() + " precio: " + elemento.getCostoAdicional();
			}
			informacion += "\n Precio Total: " + getPrecio();
			return informacion;
		}
		

}
