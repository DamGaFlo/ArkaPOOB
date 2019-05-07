package graficos;


import java.awt.image.BufferedImage;

public class Recursos{
	public static BufferedImage bola,plataforma,bloqueRojo,marco,fastBall,slowBall,bloqueSorpresa,corazon,arka,bloqueDuro,bloqueIndestructible;
	public static BufferedImage[] numeros = new BufferedImage[11];
        
        /**
         * Inicia la carga de imagenes
         */
	public static void init(){
		bola = Loader.ImageLoader("recursos/balls/ball.png");
		plataforma = Loader.ImageLoader("recursos/plataformas/plataforma.png");
		bloqueRojo = Loader.ImageLoader("recursos/bloques/bloqueRojo.png");
		marco = Loader.ImageLoader("recursos/marcos/marco.png");
		fastBall = Loader.ImageLoader("recursos/sorpresas/fastBall.png");
		slowBall = Loader.ImageLoader("recursos/sorpresas/slowBall.png");
		bloqueSorpresa = Loader.ImageLoader("recursos/bloques/bloqueSorpresa.png");
		for(int i=0;i<11;i++){
			numeros[i] = Loader.ImageLoader("recursos/numeros/"+i+".png");
		}
		corazon = Loader.ImageLoader("recursos/items/corazon.png");
		arka = Loader.ImageLoader("recursos/items/arka.png");
		bloqueDuro = Loader.ImageLoader("recursos/bloques/bloqueDuro.png");
		bloqueIndestructible = Loader.ImageLoader("recursos/bloques/bloqueIndestructible.png");
	}
}