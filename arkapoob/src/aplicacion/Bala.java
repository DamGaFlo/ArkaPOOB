/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import math.Vector2D;

/**
 *
 * @author IJuanKhoxD
 */
public class Bala extends Proyectil implements Serializable{

    private boolean destruida = false;
    private int puntaje;
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
    public Bala(Vector2D posicion,int width,int height,int estado, ArkaPOOB arkaPOOB,Player ultimoGolpeador, Vector2D velocidad){
        super(posicion,width,height,estado, arkaPOOB, ultimoGolpeador,velocidad,Proyectil.DAMAGE_NORMAL);

    }
    /**
     * 
     * @param posicion
     * @param arkaPOOB
     * @param ultimoGolpeador
     * @param velocidad
     */
    public Bala(Vector2D posicion, ArkaPOOB arkaPOOB,Player ultimoGolpeador, Vector2D velocidad){
        this(posicion,22,22,0, arkaPOOB, ultimoGolpeador,velocidad);

    }

    /**
     * 
     * @param posicion
     * @param arkaPOOB
     * @param ultimoGolpeador
     */
    public Bala(Vector2D posicion, ArkaPOOB arkaPOOB,Player ultimoGolpeador){
        super(posicion,22,22,0, arkaPOOB, ultimoGolpeador,Proyectil.DAMAGE_NORMAL);
    }

    /**
     * 
     * @param posicion - Vector de informacion sobre la posicion
     * @param width
     * @param height
     * @param estado
     * @param arkaPOOB - Juego sobre el cual se posiciona
     * @param ultimoGolpeador - Reconoce el jugador que golpeo por ultima vez
     * @param velocidad - Velocidad con la cual viaja la pelota
     * @param damage - daño que genera la bola al golpear
     */
    public Bala(Vector2D posicion,int width,int height,int estado, ArkaPOOB arkaPOOB,Player ultimoGolpeador, Vector2D velocidad,int damage){
        super(posicion,width,height,estado, arkaPOOB, ultimoGolpeador,velocidad,damage);
    }




    /**
    *update actualiza la logica del funcionamiento de la bola
    */
    @Override
    public void update(){		
        if(getEnAire()){               
            puntaje = colicion();
            mov();                        
        }

    }
    /**
    *draw genera la textura sobre graphis en el buffer
    * @param g
    */



    /**
     * Reconoce los bordes por los cuales viaja la bola para tomar una decision segun su lugar
     */
    private void bordes(){
        if((getCentro().getY()-getHeight()/2<=0)
                && !(getPosAnterior(1).getY()-getHeight()/2<=0)) destroy();

    }

    public Vector2D getPosAnterior(int times) {
        return getCentro().add(getVelocidad().escalar(-times));


    }

    /**
     * Responde a un impacto de la bola en el juego
     */
    private int colicion(){
        int puntos = 0;
        bordes();
        if(!destruida){
            puntos = bloquesColicion();
        }
        return puntos;
    }


    /**
     * Responde a un impacto con un bloque
     */
    private int bloquesColicion(){
        int puntos = 0;
        ArrayList<Bloque> bloques = getArkaPoob().getBloques();
        for(Bloque b: bloques){
            if(colicion(b,0) && !colicion(b,1)){
                puntos = b.colicion(this);
                destroy();
                break;
            }
        }
        return puntos;
    }

    /**
     * revisa si coliciono en una pocision presentr o anterior
     * @param objeto
     * @param time
     * @return
     */
    public boolean colicion(GameObject objeto,int time) {
        Vector2D miCentro = getPosAnterior(time);
        Vector2D centro = objeto.getCentro();
        int objetoWidth = objeto.getWidth(),objetoHeight = objeto.getHeight();
        return Math.abs(centro.getX() - miCentro.getX()) <= (getWidth()+objetoWidth)/2  && Math.abs(centro.getY()-miCentro.getY()) <= (getHeight()+objetoHeight)/2;
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
    public void ajustarSobreBase(Base base){
        setPosicion(new Vector2D(base.getPosicion().getX()+base.getWidth()/2,base.getPosicion().getY()-base.getHeight()-5));
    }
    public  Representacion representacion() {
        return new Representacion(getNombre(),(int)getPosicion().getX(),(int)getPosicion().getY(),getEstado());
    }	
    public String getNombre() {
        return this.getClass().getSimpleName();
    }
    public boolean fueDestruida(){
        return destruida;
    }
    public void reconstruir(){
        destruida = false;
    }
    public int getPuntaje(){
        return puntaje;
    }
    public void setPuntaje(int puntos){
        puntaje = puntos;
    }
}
