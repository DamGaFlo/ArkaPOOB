package aplicacion;

import math.Vector2D;

public class PlayerCurioso extends PlayerDestructor{

	public PlayerCurioso(int vidas, int plataforma, Vector2D posicion, ArkaPOOB arkaPOOB, String izq, String der,String fire) {
		super(vidas, plataforma, posicion, arkaPOOB, izq, der, fire);
	}
	public PlayerCurioso(int vidas, int plataforma, Vector2D posicion, ArkaPOOB arkaPOOB) {
		this(vidas, plataforma, posicion, arkaPOOB, "", "", "");
	}
	
	public void update() {
		getBase().fire();
		if(getArkaPOOB().getSorpresas().isEmpty()) 
			super.update();
		else
			sigueSorpresa();
	}
	private void sigueSorpresa() {
		double logCaida = -50;
		double posX = 0;
		for(Sorpresa p:getArkaPOOB().getSorpresas()) {
			if(p.getCentro().getY()>logCaida) {
				logCaida = p.getCentro().getY();
				posX = p.getCentro().getX();
			}
		}
		if(logCaida==-50) return;
		int mov = getBase().inverso()?-getBase().getMoxEnX():getBase().getMoxEnX();
		double diferencia = Math.abs(posX-getBase().getCentro().getX());
		double der = Math.abs(posX-(getBase().getCentro().getX()+mov));
		double izq = Math.abs(posX-(getBase().getCentro().getX()-mov));
		if(der<diferencia && der<=izq)
			getBase().movDer();
		else if(izq<diferencia && izq<=der)
			getBase().movIzq();
	}

}
