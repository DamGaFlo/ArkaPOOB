package aplicacion;

public class Representacion {
	private String nameClass;
	private int x;
	private int y;
	private int estado;
	
	public Representacion(String nameClass,int x,int y,int estado) {
		this.nameClass = nameClass;
		this.x = x;
		this.y = y;
		this.estado = estado;
	}
	
	public String getNameClass() {
		return nameClass;
	}
	public int getEstado() {
		return estado;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
