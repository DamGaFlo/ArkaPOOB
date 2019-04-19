package graficos;


import java.awt.image.BufferedImage;

public class Recursos{
	public static BufferedImage bola,plataforma,bloqueRojo;
	public static void init(){
		bola = Loader.ImageLoader("recursos/balls/ball.png");
		plataforma = Loader.ImageLoader("recursos/plataformas/plataforma.png");
		bloqueRojo = Loader.ImageLoader("recursos/bloques/bloqueRojo.png");
		
	}
}