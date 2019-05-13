package aplicacion;

import math.Vector2D;

public class BasePegajosa implements EstadoBase{
	private int tolerancia;
	
	public BasePegajosa(Base base) {
		tolerancia = 3;
		base.setWidth(140);
	}
	@Override
	public void update(Base base) {}

	@Override
	public void colicion(BolaNormal bola, Base base) {
		base.setColiciones(base.getColiciones()+1);
		bola.atrapado();
		base.setResidente(bola);
	}

	@Override
	public Representacion representacion(Base base) {
		return new Representacion(getNombre(),(int) base.getPosicion().getX(),(int)base.getPosicion().getY(),base.getEstado());
			
	}

	@Override
	public String getNombre() {
		return this.getClass().getSimpleName();
	}

	@Override
	public int getWidth(int width) {
		return width;
	}

	@Override
	public int getHeight(int height) {
		return height;
	}
	@Override
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
	@Override
	public void fire(Base base) {
		if(base.getResidente()!=null) {
			base.getResidente().diparado();
			base.setResidente(null);
			revisaLimite(base);
		}
	}

	@Override
	public void pegajosa(Base base) {
		base.setColiciones(0);
	}

	@Override
	public void normal(Base base) {
		base.setEstado(new BaseNormal(base));
		
	}

	@Override
	public void especial(Base base) {
		base.setEstado(new BaseEspecial(base));
		
	}

	@Override
	public void small(Base base) {
		base.setEstado(new BaseSmall(base));
		
	}

	@Override
	public void big(Base base) {
		base.setEstado(new BaseBig(base));
		
	}
	@Override
	public void revisaLimite(Base base) {
		if(tolerancia == base.getColiciones()) base.restaura();
	}

}
