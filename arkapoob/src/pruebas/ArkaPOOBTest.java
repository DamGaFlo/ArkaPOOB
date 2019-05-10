
package pruebas;

import aplicacion.*;
import graficos.Recursos;
import java.util.ArrayList;
import math.Vector2D;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author IJuanKhoxD
 */
public class ArkaPOOBTest {
    

    /**
     * Test of getJugadores method, of class ArkaPOOB.
     */
    @Test
    public void deberiaAvanzarDeNivel(){
        
	Recursos.init();
        ArkaPOOB juego = new ArkaPOOB();
        juego.nextLvl();        
        assertEquals(2, juego.getLvl());
    }
    
    @Test
    public void noDeberiaAvanzarDeNivel(){
        
	Recursos.init();
        ArkaPOOB juego = new ArkaPOOB();       
        assertNotEquals(2, juego.getLvl());
    }
    
    /*@Test
    public void deberiaMostrarJugadores(){
        
	Recursos.init();
        ArkaPOOB juego = new ArkaPOOB();  
        ArrayList<Player> expRes = new ArrayList<>();
        expRes.add(new Player(3,1,new Vector2D(400,740),juego));
        ArrayList<Player> res = juego.getJugadores();
        assertNotEquals(expRes, res);
    }*/
    
    @Test
    public void noDeberiaContenerSorpresas(){
        
	Recursos.init();
        ArkaPOOB juego = new ArkaPOOB();
        assertFalse(juego.getSorpresas().size()>0);
    }
    /*
    @Test
    public void deberiaCambiarVida(){
        
	Recursos.init();
        Player jugador = new Player(3,1,new Vector2D(0,0), new ArkaPOOB());       
        jugador.cambioVida(-1);
        assertEquals(jugador.getVida(), 2);
    }
    
    @Test
    public void noDeberiaGanarPuntos(){
        
	Recursos.init();
        BloqueIndestructible bloque = new BloqueIndestructible(new Vector2D(205,220),Recursos.bloqueIndestructible,new ArkaPOOB()); 
        int puntosGanados = bloque.disminuirRes(1000);
        assertEquals(0, puntosGanados);
    }
    @Test
    public void deberiaGanarPuntos(){
        
	Recursos.init();
        BloqueDuro bloque = new BloqueDuro(new Vector2D(205,220),Recursos.bloqueIndestructible,new ArkaPOOB()); 
        int puntosGanados = bloque.disminuirRes(1000);
        assertEquals(200, puntosGanados);
    }
    @Test
    public void noDeberiaReducirResistencia(){
        
	Recursos.init();
        BloqueIndestructible bloque = new BloqueIndestructible(new Vector2D(205,220),Recursos.bloqueIndestructible,new ArkaPOOB()); 
        bloque.disminuirRes(1000);
        assertEquals(2, bloque.getResistencia());
    }
    */
}
