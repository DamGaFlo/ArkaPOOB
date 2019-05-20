package aplicacion;

import math.Vector2D;

public class PlayerMimo extends Player{
	private Player rival;
	private double posRival;
	public PlayerMimo(int vidas, int plataforma, Vector2D posicion, ArkaPOOB arkaPOOB, String izq, String der,String fire) {
		super(vidas, plataforma, posicion, arkaPOOB, izq, der, fire);
		for(Player p: getArkaPOOB().getPlayers()) {
			if(p!=this) {
				posRival = p.getBase().getCentro().getX();
				rival = p;
				break;
			}
		}
	}
	
	public PlayerMimo(int vidas, int plataforma, Vector2D posicion, ArkaPOOB arkaPOOB) {
		super(vidas, plataforma, posicion, arkaPOOB, "", "", "");
	}
	/**
	 * actualizza el estado del jugadore
	 */
	public void update() {
		getBase().fire();
		mimetiza();
		posRival = rival.getBase().getCentro().getX();
		
		
	}
	private void mimetiza() {
		double movimiento = rival.getBase().getCentro().getX()-posRival;
		if(movimiento!=0) {
			boolean direccion = movimiento>0?true:false; //true a la derecha false a la izquierda
			direccion = getBase().inverso()?!direccion:direccion;//si la base es especial invierta el movimiento
			if(direccion)
				getBase().movDer();
			else
				getBase().movIzq();

		}
	}
	
	

}
