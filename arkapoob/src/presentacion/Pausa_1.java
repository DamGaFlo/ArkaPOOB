/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import persistencia.*;

/**
 *
 * @author IJuanKhoxD
 */
public class Pausa_1 extends JFrame{
    private static final int ANCHO = 550,LARGO = 300;
    private JPanel panel;
    private JLabel nombre;
    private JButton salir, cargar, salvar, nuevo;
    private ArkaPoobGUI juego;
    
    public Pausa_1(ArkaPoobGUI pantalla){
        juego = pantalla;
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ANCHO,LARGO);
	setLocationRelativeTo(null);  
        setFocusable(true);
        prepareElementos();
        prepareAcciones();
    }
    
    private void prepareElementos(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, ANCHO, LARGO); 
        cargar = new JButton("Cargar");
        salvar = new JButton("Salvar");
        nuevo = new JButton("Nuevo Juego");
        nombre = new JLabel(new ImageIcon("recursos/items/Pause.png"));
        nombre.setBounds(200,10,130,82);     
        cargar.setBounds(50,100,100,100);       
        salvar.setBounds(200,100,100,100);           
        nuevo.setBounds(350,100,100,100); 
        salir = new JButton(new ImageIcon("recursos/PantallaInicio/EXIT.gif"));
        salir.setBounds(175,200,200,126);
        salir.setContentAreaFilled(false);
        salir.setBorderPainted(false);         
        JLabel fondo = new JLabel(new ImageIcon("recursos/PantallaInicio/FondoSetup.jpg"));
        fondo.setBounds(0,0,ANCHO,LARGO);
        panel.add(nombre);
        panel.add(salir);
        panel.add(nuevo);
        panel.add(cargar);
        panel.add(salvar);
        panel.add(fondo);
        this.add(panel);
    }
    private void prepareAcciones(){
        
        this.addKeyListener(new KeyListener(){
            @Override
            public void keyReleased(KeyEvent ke) {                                                      
                if(ke.getKeyChar() == 'p') salir();
            }          
            @Override
            public void keyPressed(KeyEvent ke){    
            }
            @Override
            public void keyTyped(KeyEvent ke) {     
            }

            
        });
                
        salir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                salir();
            }            
        });
        cargar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cargar();
            }            
        });
        salvar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                salvar();
            }            
        });
        nuevo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                nuevoJuego();
            }            
        });
    }
    
    /**
     * define la accion al salir del juego
     */
    private void salir(){
        juego.setEnabled(true);
        juego.getGame().pause();
        setVisible(false);
    }
    
    private void salvar(){
        try{
            JFileChooser seleccion = new JFileChooser();  
            seleccion.setDialogTitle("Guardar");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos dat", "dat");
            seleccion.setFileFilter(filter);
            if(seleccion.showSaveDialog(salvar)==JFileChooser.APPROVE_OPTION){
                Salvar.salvar(seleccion.getSelectedFile(), juego.getGame());
                salir();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error al Salvar!!", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    private void cargar(){
        try{
            JFileChooser seleccion = new JFileChooser();               
            seleccion.setDialogTitle("Abrir");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos dat", "dat");
            seleccion.setFileFilter(filter);           
            if(seleccion.showOpenDialog(cargar)==JFileChooser.APPROVE_OPTION){                
                juego.setJuego(Abrir.abrir(seleccion.getSelectedFile())); 
                salir();
            }
        }catch(Exception e){
            e.printStackTrace(System.out);            
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error al Abrir!!", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void nuevoJuego(){               
        salir();
        juego.generaMundo(); 
    }
}
