/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author IJuanKhoxD
 */
public class PantallaInicio extends JFrame{
    
    private static final int ANCHO = 640,LARGO = 480;
    private JButton player1, player2, exit;
    /**
     * Constructor de la pantalla inicial
     */
    public PantallaInicio(){  
        super("Arkanoid");         
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE );
        prepareElementos();   
        prepareAcciones();
    }
    
    /**
     * metodo principal
     * @param args 
     */
    public static void main(String[] args){
        PantallaInicio pantallaInicio = new PantallaInicio();
        pantallaInicio.setVisible(true);  
        
       
                
    }
    
    /**
     * Prepara la pantalla inicial del juego
     */
    private void preparaPantallaInicial(){
            JPanel principal = new JPanel();                       
            principal.setLayout(null);                        
            principal.setBounds(0, 0,ANCHO, LARGO);         
            JLabel Arkanoid = new JLabel(new ImageIcon("recursos/PantallaInicio/Arkanoid.png"));      
            JLabel BackGround = new JLabel(new ImageIcon("recursos/PantallaInicio/Fondo.gif"));            
            BackGround.setBounds(0,0,ANCHO, LARGO);                       
            Arkanoid.setBounds(ANCHO/4,10,320,116);                   
            player1.setBounds(220,150,200,126);
            player1.setContentAreaFilled(false);
            player1.setBorderPainted(false);
            player2.setBounds(220,220,200,126);
            player2.setContentAreaFilled(false);
            player2.setBorderPainted(false);
            exit.setBounds(220,350,200,126);
            exit.setContentAreaFilled(false);
            exit.setBorderPainted(false);            
            
            principal.add(player1);
            principal.add(player2);
            principal.add(exit);    
            principal.add(Arkanoid); 
            principal.add(BackGround);
                   
            add(principal);
        }
    
    /**
     * prepara los elementos del juego
     */
    private void prepareElementos() {         
        setSize(ANCHO,LARGO);
	setResizable(false);
	setLocationRelativeTo(null);               
        player1 = new JButton(new ImageIcon("recursos/PantallaInicio/1_Player.gif"));
        player2 = new JButton(new ImageIcon("recursos/PantallaInicio/2_Players.gif"));
        exit = new JButton(new ImageIcon("recursos/PantallaInicio/EXIT.gif"));   
        preparaPantallaInicial();
        
    }
    /**
     * Prepara las acciones del juego
     */
    private void prepareAcciones(){
        //prepara boton X de la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { 
                salir();
            }
        });
        player1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                player1();
            }            
        });
        player2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                player1();
            }            
        });
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                salir();
            }            
        });
    }
    
    /**
     * define la accion al salir del juego
     */
    private void salir(){
            int confirma = JOptionPane.showConfirmDialog(null, 
                                            "Esta seguro que desea salir?",
                                            "Exit Confirmation", JOptionPane.YES_NO_OPTION);

            if(confirma== JOptionPane.YES_OPTION) {
                    System.exit(1);
            }
    }
    /**
     * define la accion al seleccionar un jugador
     */
    private void player1() {
        this.setVisible(false);
        ArkaPoobGUI juego = new ArkaPoobGUI();
        juego.setVisible(true);
    }
}
