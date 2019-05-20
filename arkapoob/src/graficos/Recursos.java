package graficos;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;


public class Recursos{
	private static HashMap<String,ArrayList<BufferedImage>> graficos = new HashMap<String,ArrayList<BufferedImage>>();;
	public static BufferedImage marco,corazon,arka,pause;
	public static BufferedImage[] numeros = new BufferedImage[11];;
	
	public static void init(){
		cargaBolas();
		cargaPlataformas();
		cargaBloques();
		cargaSorpresas();
		addItemsTablero();
	}
	private static void cargaBolas() {
		ArrayList<BufferedImage> img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/balls/ball.png"));
		graficos.put("BolaNormal", img);
                
                img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/balls/bullet.png"));
		graficos.put("Bala", img);
	}
	private static void cargaPlataformas() {
		ArrayList<BufferedImage> img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/plataformas/plataforma5.png"));
		graficos.put("BaseNormal",img);
		
		img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/plataformas/plataforma3.png"));
		graficos.put("BaseEspecial",img);
		
		img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/plataformas/plataforma4.png"));
		graficos.put("BasePegajosa",img);
		
		img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/plataformas/plataforma6.png"));
		graficos.put("BaseSmall",img);
		
		img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/plataformas/plataforma.png"));
		graficos.put("BaseBig",img);
                
                img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/plataformas/plataforma7.png"));
		graficos.put("BaseShooter",img);
	}
	private static void cargaBloques() {
		ArrayList<BufferedImage> img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/bloques/bloqueRojo.png"));
		graficos.put("Bloque",img);
		
		img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/bloques/bloqueDuro.png"));
		graficos.put("BloqueDuro",img);
		
		img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/bloques/bloqueSorpresa.png"));
		graficos.put("BloqueSorpresa", img);
		
		img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/bloques/bloqueIndestructible.png"));
		graficos.put("BloqueIndestructible", img);
                
                img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/bloques/bloqueTeletransportador.png"));
		graficos.put("BloqueTeletransportador", img);
                
                img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/bloques/bloqueAmarillo.png"));
		graficos.put("BloqueVida", img);
                
                img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/bloques/bloqueNaranja.png"));
		graficos.put("BloqueRepelente", img);
                
                img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/bloques/bloqueNegro.png"));
		graficos.put("BloqueDitto", img);
                img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/bloques/bloqueRosa.png"));
		graficos.put("BloqueNextLvl", img);
		
	}
	private static void cargaSorpresas(){
		ArrayList<BufferedImage> img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/sorpresas/fastBall.png"));
		graficos.put("FastBall",img);
		
		img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/sorpresas/slowBall.png"));
		graficos.put("SlowBall", img);
		
		img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/sorpresas/pegante.png"));
		graficos.put("Pegante", img);
		
		img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/sorpresas/small.png"));
		graficos.put("ReductorBase", img);
		
		img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/sorpresas/big.png"));
		graficos.put("AgrandadorBase", img);
		
		img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/sorpresas/especial.png"));
		graficos.put("Especial", img);
		
                img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/sorpresas/shooter.png"));
		graficos.put("MunicionBase", img);
		
                img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/sorpresas/corazon.png"));
		graficos.put("VidaExtra", img);
		
	}
	private static void addItemsTablero() {
		marco = Loader.ImageLoader("recursos/marcos/marco.png");
		for(int i=0;i<11;i++){
			numeros[i] = Loader.ImageLoader("recursos/numeros/"+i+".png");
		}
		corazon = Loader.ImageLoader("recursos/items/corazon.png");
		arka = Loader.ImageLoader("recursos/items/arka.png");
                pause = Loader.ImageLoader("recursos/items/Pause.png");
        }
	
	public static BufferedImage getImagen(String name,int estado) {
		if(graficos.containsKey(name)) {
			return graficos.get(name).get(estado);
		}
		return null;
	}
        public static BufferedImage cambiarColor(BufferedImage img, String pintar, int[] RGB){
            for (int i = 0; i < RGB.length; i++){
                Color colorActual = new Color(RGB[i]);
                Color colorFinal = definirColor(pintar,colorActual.getRed(),colorActual.getGreen(),colorActual.getBlue());
                RGB[i] = colorFinal.getRGB();
            }
            img.setRGB(0, 0, img.getWidth(), img.getHeight(), RGB, 0, img.getWidth());
            return img;
        }
        
        private static Color definirColor(String Color, int r, int g, int b){
            if(Color.equals("white")) return new Color(r*255/(r+1),g*255/(g+1),b*255/(b+1));
            if(Color.equals("red")) return new Color(r*255/(r+1),0,0);
            if(Color.equals("blue")) return new Color(0,g*255/(g+1),b*255/(b+1));
            if(Color.equals("yellow")) return new Color(r*255/(r+1),g*255/(g+1),0);
            if(Color.equals("orange")) return new Color(r*255/(r+1),g*165/(g+1),0);
            if(Color.equals("magenta")) return new Color(r*255/(r+1),0,b*255/(b+1));            
            if(Color.equals("green")) return new Color(0,g*255/(g+1),0);
            if(Color.equals("brown")) return new Color(r*139/(r+1),g*69/(g+1),b*19/(b+1));
            if(Color.equals("silver")) return new Color(r*169/(r+1),g*169/(g+1),b*169/(b+1));
            return new Color(r*128/(r+1),g*128/(g+1),b*128/(b+1));
        }
}

//bola = Loader.ImageLoader("recursos/balls/ball.png");
		//plataforma = Loader.ImageLoader("recursos/plataformas/plataforma.png");
		//bloqueRojo = Loader.ImageLoader("recursos/bloques/bloqueRojo.png");
		//fastBall = Loader.ImageLoader("recursos/sorpresas/fastBall.png");
		//slowBall = Loader.ImageLoader("recursos/sorpresas/slowBall.png");
		//bloqueSorpresa = Loader.ImageLoader("recursos/bloques/bloqueSorpresa.png");
		//bloqueDuro = Loader.ImageLoader("recursos/bloques/bloqueDuro.png");
		//bloqueIndestructible = Loader.ImageLoader("recursos/bloques/bloqueIndestructible.png");