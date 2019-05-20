package aplicacion;

import math.Vector2D;

public class PlayerDestructor extends Player{
	/**
	 * constructor
	 * @param vidas
	 * @param plataforma
	 * @param posicion
	 * @param arkaPOOB
	 * @param izq
	 * @param der
	 * @param fire
	 */
	public PlayerDestructor(int vidas, int plataforma, Vector2D posicion, ArkaPOOB arkaPOOB, String izq, String der,String fire) {
		super(vidas, plataforma, posicion, arkaPOOB, izq, der, fire);
	}
	public PlayerDestructor(int vidas, int plataforma, Vector2D posicion, ArkaPOOB arkaPOOB) {
		this(vidas, plataforma, posicion, arkaPOOB, "", "", "");
	}
	/**
	 * actualiza los datos necesarios para el jugador
	 */
	public void update() {
		getBase().fire();
		seguir();
	}
	/**
	 * sigue la pocision de la bola mas sercana al suelo en el juego
	 */
	private void seguir() {
		double logCaida = -50;
		double posX = 0;
		for(Proyectil p:getArkaPOOB().getBolasEnJuego()) {
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
