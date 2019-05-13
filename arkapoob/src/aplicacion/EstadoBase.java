package aplicacion;

public interface EstadoBase {
	public void update(Base base);
	default void colicionDeBase(Base base,int desplazamiento) {
		Base baseRival = null;
		for(Player player:base.getArkaPOOB().getJugadores()) {
			if(base != player.getBase()) baseRival = player.getBase();
		}
		if(baseRival != null) {
			int distaciaMinima = base.getWidth()/2 + baseRival.getWidth()/2;
			int distanciaCentros = (int)Math.abs(base.getCentro().getX() - baseRival.getCentro().getX());
			if(distaciaMinima > distanciaCentros) baseRival.getPosicion().cambioX(-desplazamiento);
 		}
	}
	
	public void colicion(BolaNormal bola,Base base);
	public Representacion representacion(Base base);
	public String getNombre();
	public int getWidth(int width);
	public int getHeight(int height);
	public void movIzq(Base base);
	public void movDer(Base base);
	public void fire(Base base);
	
	public void pegajosa(Base base);
	public void normal(Base base);
	public void especial(Base base);
	public void small(Base base);
	public void big(Base base);
	public void revisaLimite(Base base);
	
}
