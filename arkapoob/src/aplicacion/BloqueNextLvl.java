/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.io.Serializable;
import math.Vector2D;


public class BloqueNextLvl extends Bloque implements Serializable{
    /**
     *
     * @param posicion
     * @param width
     * @param height
     * @param estado
     * @param arkaPOOB
     * @param resistencia
     * @param puntaje
     */
    public BloqueNextLvl (Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB,int resistencia, int puntaje){
        super(posicion,width,height,estado,arkaPOOB,resistencia, puntaje);
    }
    /**
     * 
     * @param posicion - Vector de informacion sobre la posicion
     * @param width
     * @param height
     * @param estado
     * @param arkaPOOB - Juego sobre el cual se posiciona
     */
    public BloqueNextLvl (Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB){
        super(posicion,width,height,estado,arkaPOOB,RES_BLOQUE,PUNTOS_BLOQUE_REPE);
    }
    public BloqueNextLvl (Vector2D posicion,ArkaPOOB arkaPOOB){
        this(posicion,WIDTH_DEFAULT,HEIGHT_DEFAULT,0,arkaPOOB);
    }
    /**
     * @param p - Proyectil con el que ha chocado
     * @return - puntos obtenidos por el golpe
     */
    @Override
    public int colicion(Proyectil p){		
        super.colicion(p);
        if(getResistencia()<=0){
            return PUNTAJE;
        }
        return 0;
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
