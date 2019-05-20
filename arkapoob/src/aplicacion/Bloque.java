package aplicacion;


import java.io.Serializable;
import math.Vector2D;


public class Bloque extends GameObject implements Serializable{
	
    protected int resistencia, PUNTAJE = PUNTOS_BLOQUE;
    public static final int PUNTOS_BLOQUE = 100,PUNTOS_BLOQUE_SORP = 300,PUNTOS_BLOQUE_DURO = 200, PUNTOS_BLOQUE_REPE = 500, PUNTOS_BLOQUE_NIGG = 600;
    public static final int RES_BLOQUE = 1,RES_BLOQUE_DURO=2;
    public static final int WIDTH_DEFAULT = 120, HEIGHT_DEFAULT = 36;
    private final ArkaPOOB arkaPOOB;
    /**
     * 
     * @param posicion - Vector de informacion sobre la posicion
     * @param width
     * @param height
     * @param estado
     * @param arkaPOOB - Juego sobre el cual se posiciona
     * @param resistencia - Resistencia del bloque a los golpes
     * @param puntaje
     */
    public Bloque(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB,int resistencia,int puntaje){
        super(posicion,width,height,estado);
        this.PUNTAJE = puntaje;
        this.resistencia = resistencia;
        this.arkaPOOB = arkaPOOB;

    }
    /**
     * 
     * @param posicion - Vector de informacion sobre la posicion
     * @param arkaPOOB - Juego sobre el cual se posiciona
     */
    public Bloque(Vector2D posicion,ArkaPOOB arkaPOOB){
            super(posicion,WIDTH_DEFAULT,HEIGHT_DEFAULT,0);
            this.resistencia = RES_BLOQUE;
            this.arkaPOOB = arkaPOOB;
    }
    /**
     * 
      * @param posicion - Vector de informacion sobre la posicion
     * @param width
     * @param height
     * @param estado
     * @param arkaPOOB - Juego sobre el cual se posiciona
     */
    public Bloque(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB){
            super(posicion,width,height,estado);
            this.resistencia = RES_BLOQUE;
            this.arkaPOOB = arkaPOOB;
    }
    /**
     * Actualiza el estado del bloque
     */
    @Override
    public void update(){
            //System.out.println(resistencia);
            if(resistencia<=0){
                    destroy();
            }
    }


    /**
     * 
     * @param p
     * @return - puntos obtenidos por el golpe
     */
    public int colicion(Proyectil p){
            resistencia -=p.getDamage();
            if(resistencia<=0){
                    return PUNTAJE;
            }
            return 0;
    }

    /**
     * 
     * @return Resistencia del bloque
     */
    public int getResistencia(){
            return resistencia;
    }
    /**
     * 
     * @return Juego sobre el cual se actua
     */
    public ArkaPOOB getArkaPOOB(){
            return arkaPOOB;
    }
    /**
     * Destruye el bloque
     */
    public void destroy(){
        getArkaPOOB().agregarBloquesFueraDeJuego(this);
        getArkaPOOB().getBloques().remove(this);

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