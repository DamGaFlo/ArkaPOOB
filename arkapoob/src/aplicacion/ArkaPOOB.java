package aplicacion;


import math.Vector2D;
import java.util.*;

public class ArkaPOOB{
	private HashMap<String,Boolean> ordenesEnEjecucion;
	private HashMap<String,Boolean> ordenes;
	public final int MIN_ANGLE_INIT = 225,MAX_ANGLE_INIT = 315;
	protected int numBloques;
	protected ArrayList<Proyectil> bolasEnJuego;
	protected ArrayList<Player> players;
	protected ArrayList<Bloque> bloques;
	protected boolean pause = false;
	protected int lvl =1;
	protected ArrayList<Player> datosJugadores;
	protected ArrayList<Sorpresa> sorpresas;
	
	public static int WIDTH = 800 ,HEIGHT = 800;
	
	/**
         * Constructor
         * @param width - Ancho del juego
         * @param hight - Alto del Juego
         */
	public ArkaPOOB(){
		ordenes = new HashMap<>();
		ordenesEnEjecucion = new HashMap<>();
		bolasEnJuego = new ArrayList<Proyectil>();
		players = new ArrayList<Player>();
		bloques = new ArrayList<Bloque>();
		sorpresas = new ArrayList<Sorpresa>();
		
		
		
	}

        /**
         * Actualiza el juego segun cada movimiento
         */
	public void update(){
		if(!pause) {
			updateOrdenesPlayer1();
			if(bolasEnJuego.isEmpty()){
				reInicio();
				for(Player p:players) p.cambioVida(-1);
			}
			ArrayList<Proyectil> bolas = new ArrayList<Proyectil>(bolasEnJuego);
			for(Proyectil p: bolas) p.update();
			for(Player p:players) p.update();
			ArrayList<Bloque> iterador =  new ArrayList<Bloque>(bloques);
			for(Bloque b: iterador) b.update();
			if(!sorpresas.isEmpty()){
				ArrayList<Sorpresa> iteraSorpresas = new ArrayList<Sorpresa>(sorpresas);
				for(Sorpresa s:iteraSorpresas) s.update();
			}
		}
	}
		
	/**
         * dibuja el juego y cada uno de sus elementos
         * @param g - Graficos del juego 
         */
	public  ArrayList<Representacion> enEscenario(){
		ArrayList<Representacion> datos =  new ArrayList<>();
		for(Proyectil p: bolasEnJuego){
			datos.add(p.representacion());
		}
		for(Player p: players){
			datos.add(p.representacion());
		}
		for(Bloque b: bloques ){
			datos.add(b.representacion());
		}
		for(Sorpresa s: sorpresas){
			datos.add(s.representacion());
		}
		
		return datos;
	}
	
        /**
         * 
         * @return Ancho del juego
         */
	public int getWidth(){
		return WIDTH;
	}
        
        /**
         * 
         * @return Alto del juego
         */
	public int getHight(){
		return HEIGHT;
	}
        
        /**
         * 
         * @return lista de los jugadores
         */
	public ArrayList<Player> getPlayers(){
		return players;
	}
        
        /**
         * 
         * @return Lista de Bloques
         */
	public ArrayList<Bloque> getBloques(){
		return bloques;
	}
        
        /**
         * 
         * @return Lista de bolas en la partida
         */
	public ArrayList<Proyectil> getBolasEnJuego(){
		return bolasEnJuego;
	}
        
        /**
         * Da inicio al juego construyendo los primeros mapas
         */
	public void inicio(){
		preparaPlayer();
		datosJugadores = new ArrayList<Player>(players);
		
		reInicio();
		preparaAcciones();
		preparaNivel();
	}
	
	protected void preparaNivel() {
		bloques.add(new BloqueSorpresa(new Vector2D(100,100),this));
		bloques.add(new BloqueSorpresa(new Vector2D(205,100),this));
		bloques.add(new BloqueDuro(new Vector2D(310,100),this));
		bloques.add(new BloqueSorpresa(new Vector2D(415,100),this));
		bloques.add(new Bloque(new Vector2D(520,100),this));
		bloques.add(new BloqueDuro(new Vector2D(100,160),this));
		bloques.add(new BloqueSorpresa(new Vector2D(205,160),this));
		bloques.add(new BloqueDuro(new Vector2D(310,160),this));
		bloques.add(new BloqueIndestructible(new Vector2D(415,160),this));
		bloques.add(new Bloque(new Vector2D(520,160),this));
		bloques.add(new BloqueSorpresa(new Vector2D(100,220),this));
		bloques.add(new BloqueIndestructible(new Vector2D(205,220),this));
		bloques.add(new BloqueDuro(new Vector2D(310,220),this));
		bloques.add(new BloqueSorpresa(new Vector2D(415,220),this));
		bloques.add(new BloqueIndestructible(new Vector2D(520,220),this));
	}
		/**
		 * genera los jugadores de la partida
		 */
	protected void preparaPlayer() {
		players.add(new Player(3,1,new Vector2D(WIDTH/2,HEIGHT-150),this,"izq1","der1","fire1"));
	}
		
	protected void preparaAcciones() {
		ordenes.put("izq1", false);
		ordenes.put("der1", false);
		ordenes.put("izq2", false);
		ordenes.put("der2", false);
		ordenes.put("fire1", false);
		ordenes.put("fire2", false);
		
		ordenesEnEjecucion.put("izq1", false);
		ordenesEnEjecucion.put("der1", false);
		ordenesEnEjecucion.put("izq2", false);
		ordenesEnEjecucion.put("der2", false);
		ordenesEnEjecucion.put("fire1", false);
		ordenesEnEjecucion.put("fire2", false);
	}
        
        /**
         * Reinicia el juego por cada vez que se pierde
         */
	protected void reInicio(){
		if(!players.isEmpty()){
			players.get(0).getBase().setPosicion(new Vector2D(WIDTH/2-players.get(0).getBase().getWidth()/2,HEIGHT-players.get(0).getBase().getHeight()-50));
		}
		generaBolas();
		sorpresas.clear();
	}
        
        /**
         * genera las bolas necesarias para el juego
         */
	protected void generaBolas(){
		double magnitudVel = 6.5;
		Player jugador = players.get(0);
		Base base = jugador.getBase();
		bolasEnJuego.add(new BolaNormal(new Vector2D(base.getCentro().getX(),base.getPosicion().getY()),this,jugador,Vector2D.getVector(Math.random()*(MAX_ANGLE_INIT-MIN_ANGLE_INIT)+(MIN_ANGLE_INIT),magnitudVel)));
		bolasEnJuego.get(bolasEnJuego.size()-1).ajustarSobreBase();
		base.setResidente(bolasEnJuego.get(bolasEnJuego.size()-1));
	}
        
        /**
         * Define el estado de pausa del juego
         * @param o - Define si el juego esta en pausa
         */
	public void pause(){
		pause = !pause;
	}
	public boolean getPause() {
		return pause;
	}
        
        /**
         * aumenta un nivel por juego
         */
	public void nextLvl(){
		lvl+=1;
	}
        
        /**
         * 
         * @return  Lista de informacion sobre los jugadores
         */
	public ArrayList<Player> getJugadores(){
		return datosJugadores;
	}
        
        /**
         * 
         * @return Lista de informacion sobre las sorpresas en el juego
         */
	public ArrayList<Sorpresa> getSorpresas(){
		return sorpresas;
	}
        
        /**
         * 
         * @return Nivel actual del juego
         */
	public int getLvl(){
		return lvl;
	}
		/**
		 * movimiento de la plataforma
		 * @param b
		 */
	public void izqPlayer1(boolean b) {
		ordenesEnEjecucion.put("izq1", b);
	}
	public void derPlayer1(boolean b) {
		ordenesEnEjecucion.put("der1", b);
	}
	public void firePlayer1(boolean b) {
		ordenesEnEjecucion.put("fire1", b);
	}
		/**
		 * actualiza las ordenes en el diccionario para que cada que actualiza no interfiera manejar hilos distintos
		 */
	
	protected void updateOrdenesPlayer1() {
		ordenes.put("izq1", ordenesEnEjecucion.get("izq1"));
		ordenes.put("der1", ordenesEnEjecucion.get("der1"));
		ordenes.put("fire1", ordenesEnEjecucion.get("fire1"));
	}
		/**
		 * actualiza las ordenes en el diccionario para que cada que actualiza no interfiera manejar hilos distintos
		 */
	protected void updateOrdenesPlayer2() {
		ordenes.put("izq2", ordenesEnEjecucion.get("izq2"));
		ordenes.put("der2", ordenesEnEjecucion.get("der2"));
		ordenes.put("fire2", ordenesEnEjecucion.get("fire2"));
	}
	
	public boolean orden(String s) {
		return ordenes.get(s);
	}
	
	
		/**
		 * movimientos plataforma player 2
		 * @param b
		 */
	public void izqPlayer2(boolean b) {
		ordenesEnEjecucion.put("izq2", b);
	}
	public void derPlayer2(boolean b) {
		ordenesEnEjecucion.put("der2", b);
	}
	public void firePlayer2(boolean b) {
		ordenesEnEjecucion.put("fire2", b);
	}
}


		

		
//javac -d bin src/presentacion/*.java src/aplicacion/*.java src/math/*.java src/gameObjects/*.java src/graficos/*.java src/input/*.java
//java -cp bin presentacion.PantallaInicio