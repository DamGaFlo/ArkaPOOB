package aplicacion;

import java.io.Serializable;
import math.Vector2D;

public class BaseBig implements EstadoBase, Serializable{
	
	private int tolerancia = 3;
	public BaseBig(Base base) {
                if(base.getPosicion().getX()+base.getWidth()+100>=base.getArkaPOOB().getWidth()) base.getPosicion().cambioX(-50); 
		base.setWidth(200); 
	}
	
	/**
	 * actualiza los datos autonomos de la base
	 */
	public void update(Base base) { }
	/**
	 * ejecutacciones en caso de colicion con la bola
	 */
	public void colicion(BolaNormal bola, Base base) {
			base.setColiciones(base.getColiciones()+1);
	}
        /**
         * Define la representacion de la base
         * @param base
         * @return 
         */
	public Representacion representacion(Base base) {
		return new Representacion(getNombre(),(int) base.getPosicion().getX(),(int)base.getPosicion().getY(),base.getEstado());
			
	}
        /**
         * retorna el nombre de la clase
         * @return 
         */
	public String getNombre() {
		return this.getClass().getSimpleName();
	}
        /**
         * 
         * @param width
         * @return 
         */
	public int getWidth(int width) {
		return width;
	}
        /**
         * 
         * @param height
         * @return 
         */
	public int getHeight(int height) {
		return height;
	}
        /**
         * 
         * @param base 
         */
	public void movIzq(Base base) {
		if(base.getPosicion().getX()-base.getMoxEnX() >= 0){
			base.getPosicion().cambioX(-base.getMoxEnX());
			if(base.getResidente()!=null) base.getResidente().mov(new Vector2D(-base.getMoxEnX(),0));
		}
	}
        /**
         * 
         * @param base 
         */
	public void movDer(Base base) {
		if(base.getPosicion().getX()+base.getWidth()+base.getMoxEnX() <= base.getArkaPOOB().getWidth()){
			base.getPosicion().cambioX(base.getMoxEnX());
			if(base.getResidente()!=null) base.getResidente().mov(new Vector2D(base.getMoxEnX(),0));
		}
	}
        /**
         * 
         * @param base 
         */
	public void fire(Base base) {
		if(base.getResidente()!=null) {
			base.getResidente().diparado();
			base.setResidente(null);
		}
	}
        /**
         * 
         * @param base 
         */
        @Override
	public void pegajosa(Base base) {
		base.setEstado(new BasePegajosa(base));
	}
        /**
         * 
         * @param base 
         */
        @Override
	public void normal(Base base) {
		base.setEstado(new BaseNormal(base));
	}
        /**
         * 
         * @param base 
         */
        @Override
	public void especial(Base base) {
		base.setEstado(new BaseEspecial(base));	
	}
        /**
         * 
         * @param base 
         */
	public void small(Base base) {
		base.setEstado(new BaseNormal(base));
	}
        /**
         * 
         * @param base 
         */
	public void big(Base base) {
		base.setColiciones(0);
	}
        /**
         * 
         * @param base 
         */
	public void revisaLimite(Base base) {
		if(tolerancia == base.getColiciones()) base.restaura();
	}

    @Override
    public void shooter(Base base) {
        base.setEstado(new BaseShooter(base));
    }
}
