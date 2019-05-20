package aplicacion;

import java.io.Serializable;
import math.*;

public class BaseNormal implements EstadoBase, Serializable{
	public BaseNormal(Base base) {
		base.setWidth(140);
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

	public Representacion representacion(Base base) {
		return new Representacion(getNombre(),(int) base.getPosicion().getX(),(int)base.getPosicion().getY(),base.getEstado());
			
	}

	public String getNombre() {
		return this.getClass().getSimpleName();
	}

	public int getWidth(int width) {
		return width;
	}

	public int getHeight(int height) {
		return height;
	}
	public void movIzq(Base base) {
		if(base.getPosicion().getX()-base.getMoxEnX() >= 0){
			base.getPosicion().cambioX(-base.getMoxEnX());
			if(base.getResidente()!=null) base.getResidente().mov(new Vector2D(-base.getMoxEnX(),0));
		}
	}
	public void movDer(Base base) {
		if(base.getPosicion().getX()+base.getWidth()+base.getMoxEnX() <= base.getArkaPOOB().getWidth()){
			base.getPosicion().cambioX(base.getMoxEnX());
			if(base.getResidente()!=null) base.getResidente().mov(new Vector2D(base.getMoxEnX(),0));
		}
	}
	public void fire(Base base) {
		if(base.getResidente()!=null) {
			base.getResidente().diparado();
			base.setResidente(null);
		}
	}

	public void pegajosa(Base base) {
		base.setEstado(new BasePegajosa(base));
	}

	public void normal(Base base) {
		base.setEstado(new BaseNormal(base));
	}

	public void especial(Base base) {
		base.setEstado(new BaseEspecial(base));	
	}

	public void small(Base base) {
		base.setEstado(new BaseSmall(base));
	}

	public void big(Base base) {
		base.setEstado(new BaseBig(base));
	}
        /**
         * permite a la base disparar
         * @param base
         */
	public void shooter(Base base){
		base.setEstado(new BaseShooter(base));
	}
	public void revisaLimite(Base base) {}

}

