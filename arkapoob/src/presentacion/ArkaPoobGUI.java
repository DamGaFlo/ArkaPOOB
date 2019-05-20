package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import aplicacion.*;
import graficos.*;
import java.awt.image.*;
import math.Vector2D;


public class ArkaPoobGUI extends JFrame implements Runnable{
	private static final int WIDTH = 1000,HIGTH = 600;
	public static final int IN_GAME = 1;
        private int puntaje;
        private int vidas = 1;
	private Canvas canvas; 
	private Thread thread;
	private boolean running = false;
	protected ArkaPOOB game;
        private PantallaInicio pantalla;
        private Pausa pausa;
	
	
	private BufferStrategy bufferS;
	private Graphics graficos;
	
	private static final int FPS = 60;
	private final double TARGETTIME = 1000000000/FPS;
	private double delta = 0;
	private int AVERAGEFPS;
	
        /**
         * Constructor de la interfaz
         */
	public ArkaPoobGUI(){
            super("ArkaPOOB");
            this.puntaje = 0;
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
            //setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);                
            pausa = new Pausa(this);
            preparaCanvas();
	}
        
        /**
         * Prepara el canvas para su dibujo
         */
	private void preparaCanvas(){
            canvas = new Canvas();
            canvas.setPreferredSize(new Dimension(WIDTH,HIGTH));
            canvas.setMaximumSize(new Dimension(WIDTH,HIGTH));
            canvas.setMinimumSize(new Dimension(WIDTH,HIGTH));
            canvas.setFocusable(true);
            add(canvas);

		
	}
        
        /**
         * prepara las acciones de respuesta en el juego
         */
	private void preparaAcciones(){
            preparaTeclado();
	}
	
	private void preparaTeclado() {
		
            canvas.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent e){
                    if(e.getKeyCode() == KeyEvent.VK_A) game.izqPlayer1(true);
                    if(e.getKeyCode() == KeyEvent.VK_D) game.derPlayer1(true);
                    if(e.getKeyCode() == KeyEvent.VK_W) game.firePlayer1(true);

                    if(e.getKeyCode() == KeyEvent.VK_LEFT) game.izqPlayer2(true);
                    if(e.getKeyCode() == KeyEvent.VK_RIGHT) game.derPlayer2(true);
                    if(e.getKeyCode() == KeyEvent.VK_UP) game.firePlayer2(true);


                }
                @Override
                public void keyReleased(KeyEvent e){
                    if(e.getKeyChar() == 'p'){
                        game.pause();
                        pausar();
                    }                                 
                    if(e.getKeyChar() == 't') pintarBase();
                    if(e.getKeyCode() == KeyEvent.VK_A) game.izqPlayer1(false);
                    if(e.getKeyCode() == KeyEvent.VK_D) game.derPlayer1(false);
                    if(e.getKeyCode() == KeyEvent.VK_W) game.firePlayer1(false);

                   if(e.getKeyCode() == KeyEvent.VK_LEFT) game.izqPlayer2(false);
                   if(e.getKeyCode() == KeyEvent.VK_RIGHT) game.derPlayer2(false);
                   if(e.getKeyCode() == KeyEvent.VK_UP) game.firePlayer2(false);
                }
                @Override
                public void keyTyped(KeyEvent e){}
            });
	}
        
        /**
         * Genera un nuevo juego y lo inicia
         */
	public void generaMundo() {
            game = new ArkaPOOB();
            game.inicio();
	}
        /**
         * Cambia la pantalla de inicio del juego
         * @param pantalla 
         */
        public void setPantallaInicio(PantallaInicio pantalla){
            this.pantalla = pantalla;
        }
        
        /**
         * Inicia el juego
         */
	private void init(){
		Recursos.init();
		generaMundo();
	}
        private void pintarBase(){
            game.pause();
            String[] list = {"green", "white", "red","blue","yellow","orange","magenta","brown","silver","default"};
            JComboBox jcb = new JComboBox(list);
            jcb.setEditable(true);
            String nombre = game.getPlayers().get(0).getBase().getNombre();
            JOptionPane.showMessageDialog( null, jcb, "Seleccione un color", JOptionPane.QUESTION_MESSAGE);
            System.out.print(nombre);            
            BufferedImage img = Loader.ImageLoader(getImagen(nombre));
            int[] RGB = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
            Recursos.cambiarColor(Recursos.getImagen(nombre,0), (String) jcb.getSelectedItem(), RGB);
            game.pause();
        }
        private String getImagen(String nombre){
            if(nombre.equals("BaseNormal")) return "recursos/plataformas/plataforma5.png";
            if(nombre.equals("BaseEspecial")) return "recursos/plataformas/plataforma3.png";
            if(nombre.equals("BasePegajosa")) return "recursos/plataformas/plataforma4.png";
            if(nombre.equals("BaseSmall")) return "recursos/plataformas/plataforma6.png";
            if(nombre.equals("BaseBig")) return "recursos/plataformas/plataforma.png";
            return "";
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
		
		gameRepresenta(graficos);
		//-----------------------
		graficos.dispose();
		bufferS.show();
	}
	private void gameRepresenta(Graphics g) {
		score();
		for(Representacion r: game.enEscenario()) {
			g.drawImage(Recursos.getImagen(r.getNameClass(), r.getEstado()),r.getX(),r.getY(),null);
		}
	}
        
        /**
         * actualiza los valores del juego
         */
	public void update(){
		game.update();
                puntaje = game.getScorePlayer1();
                vidas();
	}
        /**
         * 
         */
	public void vidas(){
            if(vidas<=0) init();
            else vidas = game.getVidasPlayer1();
	}
        public void pausar(){             
            setEnabled(false); 
            pausa.setVisible(true);  
        }
        public ArkaPOOB getGame(){
            return game;
        }
        public void setJuego(ArkaPOOB juego){
            game = juego;
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
		pos.setX(pos.getX()+20);
		graficos.drawImage(Recursos.numeros[vidas],(int)pos.getX(),(int)pos.getY(),null);
                pos.setX(pos.getX()-20);
                pos.setY(pos.getY()+350);                
		graficos.setColor(Color.WHITE);
                graficos.setFont(new Font(graficos.getFont().getFontName(), Font.ITALIC, 20));
                graficos.drawString("SCORE: "+puntaje,(int)pos.getX() , (int)pos.getY());
		
	}
	/**
         * corre el juego
         */
        @Override
	public void run(){
		long now;
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
		}
	}
}