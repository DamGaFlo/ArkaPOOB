/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import aplicacion.ArkaPOOB;
import java.awt.event.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import persistencia.*;

/**
 *
 * @author IJuanKhoxD
 */
public class Opciones extends JFrame{
    private static final int ANCHO = 500,LARGO = 300;
    private JButton salir, cargar, nuevo;
    private final PantallaInicio inicio;
    private final String tipo;
    private ArkaPoobGUI juego;
    public Opciones(PantallaInicio inicio,String tipo){
        this.inicio = inicio;
        this.tipo = tipo;
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ANCHO,LARGO); 
        setFocusable(true);
        prepareElementos();
        prepareAcciones();
    }
    
    private void prepareElementos(){
        setLayout(null);
        setBounds(0, 0, ANCHO, LARGO); 
        cargar = new JButton("Cargar");
        nuevo = new JButton("Nuevo");  
        cargar.setBounds(300,100,100,100);            
        nuevo.setBounds(100,100,100,100); 
        salir = new JButton(new ImageIcon("recursos/PantallaInicio/EXIT.gif"));
        salir.setBounds(175,200,200,126);
        salir.setContentAreaFilled(false);
        salir.setBorderPainted(false);         
        JLabel fondo = new JLabel(new ImageIcon("recursos/PantallaInicio/FondoSetup.jpg"));
        fondo.setBounds(0,0,ANCHO,LARGO);
        add(salir);
        add(nuevo);
        add(cargar);
        add(fondo);        
	setLocationRelativeTo(null); 
    }
    private void prepareAcciones(){
        
                       
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
        nuevo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                player();
            }            
        });
    }
    
    /**
     * define la accion al salir del juego
     */
    private void salir(){
        setVisible(false);
    }
    
    
    private void cargar(){
        try{
            JFileChooser seleccion = new JFileChooser();               
            seleccion.setDialogTitle("Abrir");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos dat", "dat");
            seleccion.setFileFilter(filter);           
            if(seleccion.showOpenDialog(cargar)==JFileChooser.APPROVE_OPTION){                
                setJuego(Abrir.abrir(seleccion.getSelectedFile())); 
                salir();
            }
        }catch(Exception e){
            e.printStackTrace(System.out);            
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error al Abrir!!", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void player() {
        
        try {
            String nombreClase = "presentacion."+tipo;
            Class<?> cls = Class.forName(nombreClase);
            Constructor<?> cons = cls.getDeclaredConstructor(new Class[] {});
            cons.newInstance();            
            this.setVisible(false);
            inicio.setVisible(false);  
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    private void setJuego(ArkaPOOB game){        
        this.setVisible(false);
        inicio.setVisible(false);
        juego = new ArkaPoobGUI();        
        juego.setPantallaInicio(inicio);
        juego.setVisible(true);   
        juego.setJuego(game);
    }
}
