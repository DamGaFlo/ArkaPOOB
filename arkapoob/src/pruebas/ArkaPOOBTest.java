
package pruebas;

import aplicacion.*;
import static aplicacion.ArkaPOOB.*;
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
        
        ArkaPOOB juego = new ArkaPOOB();
        juego.nextLvl();        
        assertEquals(2, juego.getLvl());
    }
    
    @Test
    public void noDeberiaAvanzarDeNivel(){
        
        ArkaPOOB juego = new ArkaPOOB();       
        assertNotEquals(2, juego.getLvl());
    }
    
    @Test
    public void deberiaMostrarJugadores(){
        
        ArkaPOOB juego = new ArkaPOOB();  
        ArrayList<Player> expRes = new ArrayList<>();
        expRes.add(new Player(3,Base.NORMAL,new Vector2D(WIDTH/2,HEIGHT-150),juego,"izq1","der1","fire1"));
        ArrayList<Player> res = juego.getJugadores();
        assertNotEquals(expRes, res);
    }
    @Test
    public void noDeberiaMostrarJugadores(){
        
        ArkaPOOB juego = new ArkaPOOB();  
        ArrayList<Player> expRes = new ArrayList<>();
        expRes.add(new Player(3,Base.NORMAL,new Vector2D(WIDTH/2,HEIGHT-150),new ArkaPOOB(),"izq1","der1","fire1"));
        ArrayList<Player> res = juego.getJugadores();
        assertNotEquals(expRes, res);
    }
    @Test
    public void noDeberiaContenerSorpresas(){
        
        ArkaPOOB juego = new ArkaPOOB();
        assertFalse(juego.getSorpresas().size()>0);
    }
    
    @Test
    public void deberiaCambiarVida(){
        
        Player jugador = new Player(3,Base.NORMAL,new Vector2D(WIDTH/2,HEIGHT-150),new ArkaPOOB(),"izq1","der1","fire1");       
        jugador.cambioVida(-1);
        assertEquals(jugador.getVida(), 2);
    }
    @Test
    public void noDeberiaCambiarVida(){
        
        Player jugador = new Player(3,Base.NORMAL,new Vector2D(WIDTH/2,HEIGHT-150),new ArkaPOOB(),"izq1","der1","fire1");       
        jugador.cambioVida(0);
        assertNotEquals(jugador.getVida(), 2);
    }
    @Test
    public void deberiaPausar(){
        ArkaPOOB Juego = new ArkaPOOB();
        Juego.inicio();
        Boolean pausa = Juego.getPause();
        Juego.pause();
        assertNotEquals(pausa,Juego.getPause());
    }
    
    @Test
    public void noDeberiaPausar(){
        ArkaPOOB Juego = new ArkaPOOB();
        Juego.inicio();
        Boolean pausa = Juego.getPause();
        assertEquals(pausa,Juego.getPause());
    }
    @Test
    public void noDeberiaSerIgualRepresentacion(){
        ArkaPOOB Juego = new ArkaPOOB();
        Juego.inicio();
        Player jugador = new Player(3,Base.NORMAL,new Vector2D(WIDTH/2,HEIGHT-150),Juego,"izq1","der1","fire1");       
        assertNotEquals(Juego.getPlayers().get(0).representacion(), jugador.representacion());
    
    }
}
