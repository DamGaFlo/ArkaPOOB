package presentacion;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import aplicacion.*;
import graficos.*;
import input.*;
import math.Vector2D;


public class ArkaPoobGUI extends JFrame implements Runnable{
	private static final int WIDTH = 1000,HIGTH = 800;
	public static final int IN_GAME = 1;
	private Canvas canvas; 
	private Thread thread;
	private boolean running = false;
	private ArkaPOOB game;
	private Teclado teclado;
	private  int estado =1;
	
	
	private BufferStrategy bufferS;
	private Graphics graficos;
	
	private static final int FPS = 60;
	private double TARGETTIME = 1000000000/FPS;
	private double delta = 0;
	private int AVERAGEFPS;
	
        /**
         * Constructor de la interfaz
         */
	public ArkaPoobGUI(){
		super("Arkanoid");
		start();
		preparaElementos();
		preparaAcciones();
	}
        
        /**
         * Prepara los elementos del juego
         */
	private void preparaElementos(){
		setSize(WIDTH,HIGTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		preparaCanvas();
	}
        
        /**
         * Prepara el canvas para su dibujo
         */
	private void preparaCanvas(){
		canvas = new Canvas();
		teclado = new Teclado();
		canvas.setPreferredSize(new Dimension(WIDTH,HIGTH));
		canvas.setMaximumSize(new Dimension(WIDTH,HIGTH));
		canvas.setMinimumSize(new Dimension(WIDTH,HIGTH));
		//canvas.setFocusable(true);
		add(canvas);
		canvas.addKeyListener(teclado);
		
		
	}
        
        /**
         * prepara las acciones de respuesta en el juego
         */
	private void preparaAcciones(){
		
	}
        
        /**
         * inicia el juego 
         */
	private void init(){
		Recursos.init();
		game = new ArkaPOOB(800,800);
	}
        
        /**
         * dibuja los componentes en pantalla
         */
	private void draw(){
		bufferS = canvas.getBufferStrategy();
		if(bufferS == null){ canvas.createBufferStrategy(3);return;}
		graficos = bufferS.getDrawGraphics();
		//-----------------------
		graficos.setColor(Color.BLACK);
		graficos.fillRect(0,0,WIDTH,HIGTH);
		score();
		game.draw(graficos);
		//-----------------------
		graficos.dispose();
		bufferS.show();
	}
        
        /**
         * actualiza los valores del juego
         */
	public void update(){
		teclado.update();
		game.update();
	}
        /**
         * 
         */
	public void vidas(){
		// por definir
	}
        
        /**
         * Muestra el puntaje que recibe el jugador
         */
	public void score(){
		Vector2D pos = new Vector2D(810,140);
		graficos.setColor(Color.DARK_GRAY);
		graficos.fillRect(800,0,WIDTH,HIGTH);
		graficos.drawImage(Recursos.marco,0,0,null);
		graficos.drawImage(Recursos.arka,800,0,null);
		graficos.drawImage(Recursos.corazon,(int)pos.getX(),(int)pos.getY(),null);
		pos.setX(pos.getX()+20);
		graficos.drawImage(Recursos.numeros[10],(int)pos.getX(),(int)pos.getY(),null);
		
	}
	/**
         * corre el juego
         */
        @Override
	public void run(){
		long now = 0;
		long lastTime = System.nanoTime();
		int fotogramasXS = 0;
		long time = 0;
		init();
		while(running){
			now = System.nanoTime();
			delta+=(now - lastTime)/TARGETTIME;
			time += (now - lastTime);
			lastTime = now;
			if(delta>=1){
				update();
				draw();
				delta--;
				fotogramasXS++;
			}
			if(time>=1000000000){
				AVERAGEFPS = fotogramasXS;
				fotogramasXS = 0;
				time = 0;
			}
		}
		stop();
	}
        
        /**
         * empieza el juego
         */
	private void start(){
		thread = new Thread(this);
		running=true;
		thread.start();
		
	}
        
        /**
         * detiene el juego
         */
	private void stop(){
		try{
			thread.join();
			running = false;
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}