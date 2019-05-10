package aplicacion;


import math.Vector2D;


public class Player{
	private int puntos;
	private int vidas;
	private Base base;
	private Chronometer time;
	private ArkaPOOB arkaPOOB;
        /**
         * 
         * @param vidas - vidas que posee el jugador
         * @param plataforma - numero de la plataforma empleada
         * @param posicion - posicion sobre la cual se ubica el jugador
         * @param arkaPOOB - Juego sobre el cual actua el jugador
         */
	public Player(int vidas,int plataforma,Vector2D posicion,ArkaPOOB arkaPOOB,String izq,String der,String fire){
		this.setPuntos(0);
		this.vidas = vidas;
		this.arkaPOOB = arkaPOOB;
		time = new Chronometer();
		base = Base.getBase(plataforma,posicion,this,izq,der,fire);
	}
	/**
	*update actualiza la logica del funcionamiento de la bola
	*/
	public void update(){
		base.update();
		time.update();
	}
	public  Representacion representacion() {
		return base.representacion();
	}
        /**
         * 
         * @return base del jugador
         */
	public Base getBase(){
		return base;
	}
        /**
         * suma los puntos ganados al contador del jugador
         * @param puntosObtenidos - cantidad de puntos que ha ganado
         */
	public void addScore(int puntosObtenidos){
		setPuntos(getPuntos() + puntosObtenidos);
	}
        /**
         * Tiempo para correr
         */
	public void tiempoDeEspera(){
		time.run(300);
	}
        
        /**
         * Define si el juego esta en espera
         * @return 
         */
	public boolean enEspera(){
		return time.isRunning();
	}
        /**
         * Cambia el numero de vidas del jugador
         * @param delta - cantidad de vidas de cambio del jugador
         */
	public void cambioVida(int delta){
		vidas+=delta;
	}
        /**
         * 
         * @return Cantidad de vidas que posee el jugador
         */
    public int getVida(){
         return vidas;
    }
        /**
         * 
         * @return Juego sobre el cual actua el jugador
         */
	public ArkaPOOB getArkaPOOB(){
		return arkaPOOB;
	}
    public String getNombre() {
    	return this.getClass().getSimpleName();
    }
    public void sumaPuntos(int delta) {
    	setPuntos(getPuntos() + delta);
    }
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
    
}