package uniandes.dpoo.modelo;

import java.util.ArrayList;

public class Pedido {
		private int numPedidos;
		private int idPedido;
		private String nombreClient;
		private String direccionClient;
		private ArrayList<Producto> itemspedido;
		/**
		 * Constructor de la clase
		 */
		public Pedido(int idped, int numped, String nombreC, String direccionC)
		{
			this.numPedidos = numped;
			this.idPedido = idped;
			this.nombreClient = nombreC;
			this.direccionClient = direccionC;
			this.itemspedido = new ArrayList<Producto>();
		}		
		
		public int getIdpedido()
		{
			return idPedido;
		}
		public void agregarProducto(Producto nuevoproducto)
		{
			itemspedido.add(nuevoproducto);
		}

		private int getPrecioNeto()
		{
			int costo = 0;
			for (Producto elemento: itemspedido) {
				costo+=elemento.getPrecio();
			}
			return costo;
		}
		private int getPrecioTotal()
		{
			double valor = (getPrecioNeto() * 1.19);
			return (int) (valor);
		}
		private int getPrecioIVA()
		{
			 double valor = (getPrecioNeto() * 0.19);
			return (int) (valor);
		}
		public String factura()
		{
			String informacion = "";
			for (Producto elemento: itemspedido) {
				informacion += elemento.generarTextoFactura();
			}
			informacion += "\nSubtotal: " + getPrecioNeto() + "\n IVA : " + getPrecioIVA() + "\n El precio total con IVA es: " + getPrecioTotal();
			return informacion;
		}
		public void guardarFactura()
		{
			
		}
		
	}


