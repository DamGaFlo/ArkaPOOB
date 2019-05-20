/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author IJuanKhoxD
 */
public class Setup extends JFrame{
    private static final int ANCHO = 550,LARGO = 300;
    private JPanel panel;
    private JButton salir;
    private PantallaInicio pantallaInicial;
    
    public Setup(PantallaInicio pantalla){
        pantallaInicial = pantalla;
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ANCHO,LARGO);
	setLocationRelativeTo(null);  
        setOpacity(0.8f);
        prepareElementos();
        prepareAcciones();
    }
    
    private void prepareElementos(){
        panel = new JPanel();        
        panel.setLayout(null);
        panel.setBounds(0, 0, ANCHO, LARGO);        
        salir = new JButton(new ImageIcon("recursos/PantallaInicio/EXIT.gif"));
        salir.setBounds(175,200,200,126);
        salir.setContentAreaFilled(false);
        salir.setBorderPainted(false);  
        JLabel fondo = new JLabel(new ImageIcon("recursos/PantallaInicio/FondoSetup.jpg"));
        fondo.setBounds(0,0,ANCHO,LARGO);
        panel.add(salir);        
        panel.add(fondo);
        this.add(panel);
    }
    private void prepareAcciones(){
        salir.addActionListener(new ActionListener(){
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
        pantallaInicial.setEnabled(true);
        setVisible(false);
    }
}
