package input;

import java.awt.event.*;

public class Teclado implements KeyListener{
	
	private boolean[] keys = new boolean[256];
	private static boolean left,right,space,p;
	
        /**
         * Constructor de Listener del teclado
         */
	public Teclado(){
		left = false;
		right = false;
		space = false;
	}
        
        /**
         * Actualiza el etado del teclado
         */
	public void update(){
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		space = keys[KeyEvent.VK_SPACE];
		p = keys[KeyEvent.VK_P];
	}
        
        @Override
	public void keyPressed(KeyEvent e){
		keys[e.getKeyCode()] = true;
	}
        @Override
	public void keyReleased(KeyEvent e){
		keys[e.getKeyCode()] = false;
	}
        @Override
	public void keyTyped(KeyEvent e){}
	
	public static boolean getLeft(){
		return left;
	}
	public static boolean getRight(){
		return right;
	}
	public static boolean getSpace(){
		return space;
	}
	public boolean getP(){
		return p;
	}

} 