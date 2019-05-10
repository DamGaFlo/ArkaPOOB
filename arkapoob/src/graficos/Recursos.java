package graficos;


import java.awt.image.BufferedImage;
import java.util.*;


public class Recursos{
	private static HashMap<String,ArrayList<BufferedImage>> graficos = new HashMap<String,ArrayList<BufferedImage>>();;
	public static BufferedImage marco,corazon,arka;
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
	}
	private static void cargaPlataformas() {
		ArrayList<BufferedImage> img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/plataformas/plataforma.png"));
		graficos.put("Base",img);
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
		
	}
	private static void cargaSorpresas(){
		ArrayList<BufferedImage> img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/sorpresas/fastBall.png"));
		graficos.put("FastBall",img);
		
		img = new ArrayList<>();
		img.add(Loader.ImageLoader("recursos/sorpresas/slowBall.png"));
		graficos.put("SlowBall", img);
	}
	private static void addItemsTablero() {
		marco = Loader.ImageLoader("recursos/marcos/marco.png");
		for(int i=0;i<11;i++){
			numeros[i] = Loader.ImageLoader("recursos/numeros/"+i+".png");
		}
		corazon = Loader.ImageLoader("recursos/items/corazon.png");
		arka = Loader.ImageLoader("recursos/items/arka.png");
	}
	public static BufferedImage getImagen(String name,int estado) {
		if(graficos.containsKey(name)) {
			return graficos.get(name).get(estado);
		}
		return null;
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