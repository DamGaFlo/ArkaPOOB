/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import math.Vector2D;

/**
 *
 * @author IJuanKhoxD
 */
public class BloqueDitto extends Bloque implements Serializable{
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
    public BloqueDitto(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB,int resistencia, int puntaje){
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
    public BloqueDitto(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB){
        super(posicion,width,height,estado,arkaPOOB,RES_BLOQUE, Bloque.PUNTOS_BLOQUE_NIGG);
    }
    public BloqueDitto(Vector2D posicion,ArkaPOOB arkaPOOB){
        this(posicion,WIDTH_DEFAULT,HEIGHT_DEFAULT,0,arkaPOOB);
    }
    /**
     * @param p - Proyectil con el que ha chocado
     * @return - puntos obtenidos por el golpe
     */
    @Override
    public int colicion(Proyectil p){		
        super.colicion(p); 
        if(getArkaPOOB().getBloquesFueraDeJuego().size()>0){
            try {
                Bloque nuevoBloque = getArkaPOOB().getBloquesFueraDeJuego().get(getArkaPOOB().getBloquesFueraDeJuego().size()-1);
                getArkaPOOB().getBloquesFueraDeJuego().remove(nuevoBloque);
                String nombreClase = "aplicacion."+nuevoBloque.getNombre();
                Class<?> cls = Class.forName(nombreClase);
                Constructor<?> cons = cls.getDeclaredConstructor(new Class[] {Vector2D.class,int.class,int.class,int.class,ArkaPOOB.class,int.class,int.class});
                Object newInstance = cons.newInstance(getPosicion(),WIDTH_DEFAULT,HEIGHT_DEFAULT,0,getArkaPOOB(),RES_BLOQUE,PUNTOS_BLOQUE_NIGG);
                getArkaPOOB().getBloques().add((Bloque)newInstance);   
                destroy();
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(BloqueDitto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(resistencia<=0){
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
    * Destruye el bloque
    */
    @Override
    public void destroy(){
        getArkaPOOB().getBloques().remove(this);


    }
}
