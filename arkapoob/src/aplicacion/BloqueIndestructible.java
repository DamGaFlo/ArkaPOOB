package aplicacion;

import math.Vector2D;

public class BloqueIndestructible extends Bloque{
		/**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene el bloque
         * @param arkaPOOB - Juego sobre el cual se posiciona
         * @param resistencia - Resistencia del bloque a los golpes
         */
	public BloqueIndestructible(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB,int resistencia){
		super(posicion,width,height,estado,arkaPOOB,resistencia);
	}
        /**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene el bloque
         * @param arkaPOOB - Juego sobre el cual se posiciona
         */
	public BloqueIndestructible(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB){
		super(posicion,width,height,estado,arkaPOOB,RES_BLOQUE_DURO);
		
		
	}
	public BloqueIndestructible(Vector2D posicion,ArkaPOOB arkaPOOB){
		this(posicion,WIDTH_DEFAULT,HEIGHT_DEFAULT,0,arkaPOOB);
	}
        /**
         * 
         * @param delta - cantidad de cambio sobre el bloque
         * @return nulo, pues no gana puntos en el golpe
         */
        @Override
	public int colicion(Proyectil P){
		//   :v
		return 0;
	}
    public  Representacion representacion() {
    	return new Representacion(getNombre(),(int)getPosicion().getX(),(int)getPosicion().getY(),getEstado());
    }
    public String getNombre() {
    	return this.getClass().getSimpleName();
    }
	

}