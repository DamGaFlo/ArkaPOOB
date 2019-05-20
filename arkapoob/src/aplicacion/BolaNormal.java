package aplicacion;

import java.io.Serializable;
import math.Vector2D;
import java.util.*;
public class BolaNormal extends Proyectil implements Serializable{
	
    private boolean destruida = false;
    /**
     * 
     * @param posicion
     * @param width
     * @param height
     * @param estado
     * @param arkaPOOB
     * @param ultimoGolpeador
     * @param velocidad
     */
    public BolaNormal(Vector2D posicion,int width,int height,int estado, ArkaPOOB arkaPOOB,Player ultimoGolpeador, Vector2D velocidad){
        super(posicion,width,height,estado, arkaPOOB, ultimoGolpeador,velocidad,Proyectil.DAMAGE_NORMAL);

    }
    /**
     * 
     * @param posicion
     * @param arkaPOOB
     * @param ultimoGolpeador
     * @param velocidad
     */
    public BolaNormal(Vector2D posicion, ArkaPOOB arkaPOOB,Player ultimoGolpeador, Vector2D velocidad){
        this(posicion,22,22,0, arkaPOOB, ultimoGolpeador,velocidad);

    }

    /**
     * 
     * @param posicion
     * @param arkaPOOB
     * @param ultimoGolpeador
     */
    public BolaNormal(Vector2D posicion, ArkaPOOB arkaPOOB,Player ultimoGolpeador){
        super(posicion,22,22,0, arkaPOOB, ultimoGolpeador,Proyectil.DAMAGE_NORMAL);
    }

    /**
     * 
     * @param posicion - Vector de informacion sobre la posicion
     * @param width
     * @param arkaPOOB - Juego sobre el cual se posiciona
     * @param estado
     * @param height
     * @param ultimoGolpeador - Reconoce el jugador que golpeo por ultima vez
     * @param velocidad - Velocidad con la cual viaja la pelota
     * @param damage - daño que genera la bola al golpear
     */
    public BolaNormal(Vector2D posicion,int width,int height,int estado, ArkaPOOB arkaPOOB,Player ultimoGolpeador, Vector2D velocidad,int damage){
        super(posicion,width,height,estado, arkaPOOB, ultimoGolpeador,velocidad,damage);
    }


    /**
    *update actualiza la logica del funcionamiento de la bola
    */
    @Override
    public void update(){
        if(getEnAire()){
            Player ultimoGolpeador = getUltimoGolpe();                
            int colicion = colicion();
            if(ultimoGolpeador != null){ 
                ultimoGolpeador.addScore(colicion); 
            }
            corrigeAngle();
            mov();
        }

    }
    
    /**
     * Define direccion del rebote en X
     */
    public void reboteX(){
            setVelocidad(new Vector2D(getVelocidad().getX()*-1,getVelocidad().getY()));
    }
    /**
     * Define direccion del rebote en Y
     */
    public void reboteY(){
            setVelocidad(new Vector2D(getVelocidad().getX(),getVelocidad().getY()*-1));
    }
    /**
     * Reconoce los bordes por los cuales viaja la bola para tomar una decision segun su lugar
     */
    private void bordes(){
        if((getCentro().getX()+getWidth()/2 >= getMaxWidth()  ||  getCentro().getX()-getWidth()/2<=0)
                && !(getPosAnterior(1).getX()+getWidth()/2 >= getMaxWidth() || getPosAnterior(1).getX()-getWidth()/2<=0)) reboteX();
        if((getCentro().getY()-getHeight()/2<=0)
                && !(getPosAnterior(1).getY()-getHeight()/2<=0)) reboteY();

        if(getCentro().getY()+getHeight()/2 >= getMaxHight()){
                destroy();
        }
    }

    public Vector2D getPosAnterior(int times) {
        return getCentro().add(getVelocidad().escalar(-times));


    }

    /**
     * Responde a un impacto de la bola en el juego
     */
    private int colicion(){
        int puntaje = 0;
        bordes();
        if(!destruida){
            puntaje = bloquesColicion();
            colicionPlayers();
        }
        return puntaje;
    }
    /**
     * Responde a un impacto con el jugador
     */
    private void colicionPlayers(){
        ArrayList<Player> players = getArkaPoob().getPlayers();
        Vector2D miCentro = getCentro();
        for(Player p: players){
            Vector2D posBase = p.getBase().getCentro();
            int baseWidth = p.getBase().getWidth(),baseHeight = p.getBase().getHeight();
            if(colicion(p.getBase(),0) && !colicion(p.getBase(),1)){
                p.getBase().colicion(this);
                if(miCentro.getY() >= posBase.getY() && miCentro.getY() <= posBase.getY()+baseHeight) reboteX();
                else reboteY();
                break;
            }
        }
    }

    /**
     * Responde a un impacto con un bloque
     */
    private int bloquesColicion(){
        int puntaje = 0;
        Vector2D miCentro = getCentro();
        ArrayList<Bloque> bloques = getArkaPoob().getBloques();
        for(Bloque b: bloques){
            int bWidth = b.getWidth(),bHeight = b.getHeight();
            if(colicion(b,0) && !colicion(b,1)){
                puntaje = b.colicion(this);
                Vector2D posB = b.getPosicion();
                if(miCentro.getX() >= posB.getX() && miCentro.getX() <= posB.getX()+bWidth) reboteY();
                else if(miCentro.getY() >= posB.getY() && miCentro.getY() <= posB.getY()+bHeight) reboteX();
                else{
                    reboteX();reboteY();

                }
                break;
            }
        }
        return puntaje;
    }

    /**
     * revisa si coliciono en una pocision presentr o anterior
     * @param objeto
     * @return
     */
    public boolean colicion(GameObject objeto,int time) {
        Vector2D miCentro = getPosAnterior(time);
        Vector2D centro = objeto.getCentro();
        int objetoWidth = objeto.getWidth(),objetoHeight = objeto.getHeight();
        if(Math.abs(centro.getX() - miCentro.getX()) <= (getWidth()+objetoWidth)/2  && Math.abs(centro.getY()-miCentro.getY()) <= (getHeight()+objetoHeight)/2) return true;
        return false;
    }

    /**
     * cambia el movimiento de la bola
     */
    @Override
    public void mov(){
        setPosicion(getPosicion().add(getVelocidad()));
    }
    /**
     * Cambia el movimiento a partir de un vector
     * @param movimiento - Vector de movimiento
     */
    @Override
    public void mov(Vector2D movimiento){
        setPosicion(getPosicion().add(movimiento));
    }
    public void destroy(){
        getArkaPoob().getBolasEnJuego().remove(this);
        destruida = true;
    }
    /**
     * Mueve la bola sobre la base del jugador
     */
    @Override
    public void ajustarSobreBase(){
        setPosicion(new Vector2D(getPosicion().getX()-getWidth()/2,getPosicion().getY()-getHeight()-5));
    }
    /**
     * Corrige el angulo de direccion de la bola
     */
    private void corrigeAngle(){
        double angle = Math.toDegrees(getVelocidad().getAngle());
        if((angle>85 && angle<95) || (angle>175 && angle<185) || (angle>265 && angle<275) || (angle>=0 && angle<5) || (angle>355 && angle<=360)) setVelocidad(getVelocidad().addAngle(Math.PI/4));
    }
    @Override
    public  Representacion representacion() {
        return new Representacion(getNombre(),(int)getPosicion().getX(),(int)getPosicion().getY(),getEstado());
    }

    @Override
    public String getNombre() {
        return this.getClass().getSimpleName();
    }
	
}