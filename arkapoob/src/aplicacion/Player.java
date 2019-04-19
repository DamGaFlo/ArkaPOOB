package aplicacion;

import java.util.*;
import math.Vector2D;
import java.awt.Graphics;

public class Player{
	private int puntos;
	private int vidas;
	private Base base;
	public Player(int vidas,int plataforma,Vector2D posicion,ArkaPOOB arkaPOOB){
		this.vidas = vidas;
		base = Base.getBase(plataforma,posicion,arkaPOOB);
	}
	public void update(){
		base.update();
	}
	public void draw(Graphics g){
		base.draw(g);
	}
	public Base getBase(){
		return base;
	}
}