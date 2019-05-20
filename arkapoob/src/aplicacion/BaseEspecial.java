package aplicacion;

import java.io.Serializable;
import math.Vector2D;

public class BaseEspecial extends BaseNormal implements Serializable{
	private int tolerancia = 3;
	
        /**
         * Constructor
         * @param base 
         */
	public BaseEspecial(Base base) {
		super(base);
	}
        /**
         * actualiza la base
         * @param base 
         */
	@Override
	public void update(Base base) {
		revisaLimite(base);
	}
	/**
         * mueve a la izquierda la base
         * @param base 
         */
	@Override
	public void movIzq(Base base) {
		if(base.getPosicion().getX()+base.getWidth()+base.getMoxEnX() <= base.getArkaPOOB().getWidth()){
			base.getPosicion().cambioX(base.getMoxEnX());
			if(base.getResidente()!=null) base.getResidente().mov(new Vector2D(base.getMoxEnX(),0));
		}
		
	}
        /**
         * 
         * @param base 
         */
	@Override
	public void movDer(Base base) {
		if(base.getPosicion().getX()-base.getMoxEnX() >= 0){
			base.getPosicion().cambioX(-base.getMoxEnX());
			if(base.getResidente()!=null) base.getResidente().mov(new Vector2D(-base.getMoxEnX(),0));
		}
	}
	/**
         * 
         * @param base 
         */
	@Override
	public void revisaLimite(Base base) {
		if(tolerancia == base.getColiciones()) base.restaura();
	}
        /**
         * 
         * @return 
         */
	@Override
	public String getNombre() {
		return this.getClass().getSimpleName();
	}
}
