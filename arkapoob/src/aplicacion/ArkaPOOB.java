package aplicacion;


import java.io.Serializable;
import math.Vector2D;
import java.util.*;

public class ArkaPOOB implements Serializable{
    private HashMap<String,Boolean> ordenesEnEjecucion;
    private HashMap<String,Boolean> ordenes;
    public final double MIN_ANGLE_INIT = 200,MAX_ANGLE_INIT = 340;
    protected int numBloques;
    protected ArrayList<Proyectil> bolasEnJuego;
    protected ArrayList<Player> players;
    protected ArrayList<Bloque> bloques;
    protected ArrayList<Bloque> bloquesFueraDeJuego;
    protected boolean pause = false;
    protected int lvl =1;
    protected ArrayList<Player> datosJugadores;
    protected ArrayList<Sorpresa> sorpresas;

    public static int WIDTH = 800 ,HEIGHT = 600;

    /**
     * Constructor
     */
    public ArkaPOOB(){
        ordenes = new HashMap<>();
        ordenesEnEjecucion = new HashMap<>();
        bolasEnJuego = new ArrayList<>();
        players = new ArrayList<>();
        bloques = new ArrayList<>();
        sorpresas = new ArrayList<>();
        bloquesFueraDeJuego = new ArrayList<>();

    }

    /**
     * Actualiza el juego segun cada movimiento
     */
    public void update(){
        if(!pause) {
            updateOrdenesPlayer1();
            if(bolasEnJuego.isEmpty()){
                reInicio();
                sorpresas.clear();
                for(Player p:players) p.cambioVida(-1);
            }
            ArrayList<Proyectil> bolas = new ArrayList<>(bolasEnJuego);
            for(Proyectil p: bolas) p.update();
            for(Player p:players) p.update();
            ArrayList<Bloque> iterador =  new ArrayList<Bloque>(bloques);
            for(Bloque b: iterador) b.update();
            ArrayList<Sorpresa> iteraSorpresas = new ArrayList<Sorpresa>(sorpresas);
            for(Sorpresa s:iteraSorpresas) s.update();

        }
    }

    /**
     * dibuja el juego y cada uno de sus elementos 
     * @return 
     */
    public  ArrayList<Representacion> enEscenario(){
        ArrayList<Representacion> datos =  new ArrayList<>();
        for(Proyectil p: bolasEnJuego){
            datos.add(p.representacion());
        }
        for(Player p: players){
            datos.add(p.representacion());
        }
        for(Bloque b: bloques){
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
     * Agrega un bloque a una lista de bloques que se han destruido
     * @param b 
     */
    public void agregarBloquesFueraDeJuego(Bloque b){
        bloquesFueraDeJuego.add(b);
    }

    public ArrayList<Bloque> getBloquesFueraDeJuego(){
        return bloquesFueraDeJuego;
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
    /**
     * Genera el nivel de juego 
     */
    protected void preparaNivel() {	
        
        //Fila 1
            bloques.add(new BloqueRepelente(new Vector2D(80,100),this));
            bloques.add(new BloqueDitto(new Vector2D(202,100),this));
            bloques.add(new BloqueNextLvl(new Vector2D(324,100),this));
            bloques.add(new BloqueSorpresa(new Vector2D(446,100),this));
            bloques.add(new Bloque(new Vector2D(568,100),this));
        //Fila 1
            bloques.add(new BloqueSorpresa(new Vector2D(80,138),this));
            bloques.add(new BloqueDuro(new Vector2D(202,138),this));
            bloques.add(new Bloque(new Vector2D(324,138),this));
            bloques.add(new BloqueSorpresa(new Vector2D(446,138),this));
            bloques.add(new Bloque(new Vector2D(568,138),this));
        //Fila 1
            bloques.add(new Bloque(new Vector2D(80,176),this));
            bloques.add(new BloqueTeletransportador(new Vector2D(202,176),this));
            bloques.add(new BloqueDuro(new Vector2D(324,176),this));
            bloques.add(new BloqueDitto(new Vector2D(446,176),this));
            bloques.add(new BloqueDitto(new Vector2D(568,176),this));
        //Fila 1
            bloques.add(new BloqueSorpresa(new Vector2D(80,214),this));
            bloques.add(new BloqueIndestructible(new Vector2D(202,214),this));
            bloques.add(new BloqueDuro(new Vector2D(324,214),this));
            bloques.add(new BloqueVida(new Vector2D(446,214),this));
            bloques.add(new BloqueSorpresa(new Vector2D(588,214),this));
        
    }
    /**
     * genera los jugadores de la partida
     */
    protected void preparaPlayer() {
        players.add(new Player(3,Base.NORMAL,new Vector2D(WIDTH/2,HEIGHT-150),this,"izq1","der1","fire1"));
    }
    /**
     * Se anticipa a las acciones que puede recibir al jugar
     */
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
            players.get(0).getBase().restaura();
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
        Random r= new Random();
        double angle = Math.toRadians(MIN_ANGLE_INIT)+Math.toRadians(MAX_ANGLE_INIT - MIN_ANGLE_INIT)*r.nextDouble();
        bolasEnJuego.add(new BolaNormal(new Vector2D(base.getCentro().getX(),base.getPosicion().getY()),this,jugador,Vector2D.getVector(angle,magnitudVel)));
        bolasEnJuego.get(bolasEnJuego.size()-1).ajustarSobreBase();
        base.setResidente(bolasEnJuego.get(bolasEnJuego.size()-1));
    }

    /**
     * Define el estado de pausa del juego
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
    /**
     * Define la accion que debe hacer el juego
     * @param s
     * @return 
     */
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
    /**
     * 
     * @param b 
     */
    public void derPlayer2(boolean b) {
        ordenesEnEjecucion.put("der2", b);
    }
    /**
     * 
     * @param b 
     */
    public void firePlayer2(boolean b) {
        ordenesEnEjecucion.put("fire2", b);
    }

    public void agregarProyectil(Proyectil p){
        bolasEnJuego.add(p);

    }
    public int getScorePlayer1(){
        return players.get(0).getPuntos();
    }
    public int getVidasPlayer1(){
        return players.get(0).getVida();
    }
}       


/**
        //Fila 1
            bloques.add(new BloqueTeletransportador(new Vector2D(100,100),this));
            bloques.add(new Bloque(new Vector2D(205,100),this));
            bloques.add(new BloqueTeletransportador(new Vector2D(310,100),this));
            bloques.add(new BloqueSorpresa(new Vector2D(415,100),this));
            bloques.add(new BloqueTeletransportador(new Vector2D(520,100),this));
            bloques.add(new BloqueTeletransportador(new Vector2D(625,100),this));
        //Fila 2
            bloques.add(new Bloque(new Vector2D(100,155),this));
            bloques.add(new BloqueIndestructible(new Vector2D(205,155),this));
            bloques.add(new BloqueDuro(new Vector2D(310,155),this));
            bloques.add(new Bloque(new Vector2D(415,155),this));
            bloques.add(new BloqueTeletransportador(new Vector2D(520,155),this));
            bloques.add(new Bloque(new Vector2D(625,155),this));
        //Fila 3
            bloques.add(new Bloque(new Vector2D(100,210),this));
            bloques.add(new BloqueSorpresa(new Vector2D(205,210),this));
            bloques.add(new BloqueTeletransportador(new Vector2D(310,210),this));
            bloques.add(new Bloque(new Vector2D(415,210),this));
            bloques.add(new BloqueIndestructible(new Vector2D(520,210),this));
            bloques.add(new BloqueDuro(new Vector2D(625,210),this));
        //Fila 4
            bloques.add(new BloqueSorpresa(new Vector2D(100,265),this));
            bloques.add(new BloqueDuro(new Vector2D(205,265),this));
            bloques.add(new BloqueVida(new Vector2D(310,265),this));
            bloques.add(new BloqueVida(new Vector2D(415,265),this));
            bloques.add(new BloqueDuro(new Vector2D(520,265),this));
            bloques.add(new BloqueVida(new Vector2D(625,265),this));
                
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
 */
