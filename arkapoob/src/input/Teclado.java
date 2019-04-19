package input;

import java.awt.event.*;

public class Teclado implements KeyListener{
	
	private boolean[] keys = new boolean[256];
	private static boolean left,right;
	
	public Teclado(){
		left = false;
		right = false;
	}
	public void update(){
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}
	public void keyPressed(KeyEvent e){
		keys[e.getKeyCode()] = true;
	}
	public void keyReleased(KeyEvent e){
		keys[e.getKeyCode()] = false;
	}
	public void keyTyped(KeyEvent e){}
	
	public static boolean getLeft(){
		return left;
	}
	public static boolean getRight(){
		return right;
	}

} 