package aplicacion;

import java.awt.Graphics;
import math.Vector2D;
import java.awt.image.BufferedImage;
import graficos.Recursos;
import java.util.*;

public class ArkaPOOB{
	private int numBloques;
	private Proyectil bolaEnJuego;
	private Player player;
	private ArrayList<Bloque> bloques;
	
	private static int WIDTH,HIGHT;
	
	
	public ArkaPOOB(int width,int hight){
		WIDTH = width;
		HIGHT = hight+200;
		
		bolaEnJuego = new BolaNormal(new Vector2D(200,200),Recursos.bola,this,null);
		player = new Player(3,1,new Vector2D(100,HIGHT-60-200),this);
		bloques = new ArrayList<Bloque>();
		bloques.add(new Bloque(new Vector2D(100,100),Recursos.bloqueRojo));
		
	}
	public void update(){
		bolaEnJuego.update();
		player.update();
	}
		
	
	public void draw(Graphics g){
		bolaEnJuego.draw(g);
		player.draw(g);
		for(Bloque b: bloques) b.draw(g);
	}
	
	public int getWidth(){
		return WIDTH;
	}
	public int getHight(){
		return HIGHT;
	}
	public Player getPlayer(){
		return player;
	}
	public ArrayList<Bloque> getBloques(){
		return bloques;
	}
}

//javac -d bin src/presentacion/*.java src/aplicacion/*.java src/math/*.java src/gameObjects/*.java src/graficos/*.java src/input/*.java