package uniandes.dpoo.modelo;

public class Ingrediente {
	private String nombre;
	private int costoAdicional;
	
	public Ingrediente(String nombrei, int costo) {
		this.nombre = nombrei;
		this.costoAdicional = costo;
	}
	public String getNombre() {
		return nombre;
	}
	public int getCostoAdicional() {
		return costoAdicional;
	}
}
