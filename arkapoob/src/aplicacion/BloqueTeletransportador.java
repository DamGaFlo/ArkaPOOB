/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import math.Vector2D;


public class BloqueTeletransportador extends Bloque implements Serializable {
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
    public BloqueTeletransportador (Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB,int resistencia, int puntaje){
        super(posicion,width,height,estado,arkaPOOB,resistencia,puntaje);
    }
    /**
     * 
     * @param posicion - Vector de informacion sobre la posicion
     * @param width
     * @param height
     * @param estado
     * @param arkaPOOB - Juego sobre el cual se posiciona
     */
    public BloqueTeletransportador (Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB){
        super(posicion,width,height,estado,arkaPOOB,RES_BLOQUE_DURO,PUNTOS_BLOQUE_DURO);
    }
    public BloqueTeletransportador (Vector2D posicion,ArkaPOOB arkaPOOB){
        this(posicion,WIDTH_DEFAULT,HEIGHT_DEFAULT,0,arkaPOOB);
    }
    /**
     * @param p - Proyectil con el que ha chocado
     * @return - puntos obtenidos por el golpe
     */
    @Override
    public int colicion(Proyectil p){		
            super.resistencia -=p.getDamage();
            if (getResistencia()> 0)teletransportar(p);
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
    /**
     * teletransporta un Proyectil y el bloque a una ubicación libre
     * @param p - proyectil a teletransportar
     */
    public void teletransportar(Proyectil p){
        ArrayList<Bloque> bloquesFueraDeJuego = getArkaPOOB().getBloquesFueraDeJuego();
        if(bloquesFueraDeJuego.size()>0){
            Random random = new Random(); 
            int i = random.nextInt(bloquesFueraDeJuego.size());
            Bloque bloque = bloquesFueraDeJuego.get(i);
            Vector2D nuevaPosicion = bloque.getPosicion();
            this.setPosicion(nuevaPosicion);
            p.setPosicion(nuevaPosicion);                
            bloquesFueraDeJuego.remove(i);
        }


    }
}
