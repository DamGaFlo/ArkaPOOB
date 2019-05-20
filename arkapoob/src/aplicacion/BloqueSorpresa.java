package aplicacion;

import java.io.Serializable;
import math.Vector2D;

public class BloqueSorpresa extends Bloque implements Serializable{
	
    private Sorpresa sorpresa;
    
    public BloqueSorpresa (Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB,int resistencia, int puntaje){
        super(posicion,width,height,estado,arkaPOOB,resistencia,puntaje); 
        sorpresa = Sorpresa.randomSorpresa(getCentro(),this);
    }
    /**
     * 
     * @param posicion
     * @param width
     * @param height
     * @param estado
     * @param arkaPOOB
     * @param sorpresa
     */
    public BloqueSorpresa(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB,Sorpresa sorpresa){
            super(posicion,width,height,estado,arkaPOOB,RES_BLOQUE,PUNTOS_BLOQUE_SORP);
            this.sorpresa = sorpresa;

    }
    /**
     * 
     * @param posicion
     * @param width
     * @param height
     * @param estado
     * @param arkaPOOB
     */
    public BloqueSorpresa(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB){
            super(posicion,width,height,estado,arkaPOOB,RES_BLOQUE,PUNTOS_BLOQUE_SORP);
            sorpresa = Sorpresa.randomSorpresa(getCentro(),this);
    }
    /**
     * 
     * @param posicion
     * @param arkaPOOB
     */
    public BloqueSorpresa(Vector2D posicion,ArkaPOOB arkaPOOB){
            this(posicion,WIDTH_DEFAULT,HEIGHT_DEFAULT,0,arkaPOOB);
    }

    /**
     * 
     * @return puntos obtenidos
     */
    @Override
    public int colicion(Proyectil p){
            super.colicion(p);
            if(getResistencia()<=0){
                    habilidad(p);
                    return PUNTAJE;
            }
            return 0;
    }
    /**
     * Define la habilidad que tiene el bloque
     * @param p
     */
    public void habilidad(Proyectil p){
        getArkaPOOB().getSorpresas().add(sorpresa);
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