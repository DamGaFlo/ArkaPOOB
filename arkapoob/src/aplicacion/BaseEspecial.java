package aplicacion;

import math.Vector2D;

public class BaseEspecial extends BaseNormal{
	private int tolerancia = 3;
	
	public BaseEspecial(Base base) {
		super(base);
	}
	@Override
	public void update(Base base) {
		revisaLimite(base);
	}
	
	@Override
	public void movIzq(Base base) {
		if(base.getPosicion().getX()+base.getWidth()+base.getMoxEnX() <= base.getArkaPOOB().getWidth()){
			base.getPosicion().cambioX(base.getMoxEnX());
			if(base.getResidente()!=null) base.getResidente().mov(new Vector2D(base.getMoxEnX(),0));
		}
		
	}
	@Override
	public void movDer(Base base) {
		if(base.getPosicion().getX()-base.getMoxEnX() >= 0){
			base.getPosicion().cambioX(-base.getMoxEnX());
			if(base.getResidente()!=null) base.getResidente().mov(new Vector2D(-base.getMoxEnX(),0));
		}
	}
	
	@Override
	public void revisaLimite(Base base) {
		if(tolerancia == base.getColiciones()) base.restaura();
	}
	@Override
	public String getNombre() {
		return this.getClass().getSimpleName();
	}
}
