/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.io.Serializable;
import math.Vector2D;

/**
 *
 * @author IJuanKhoxD
 */
public class MunicionBase extends Sorpresa  implements Serializable{
    
    public MunicionBase(Vector2D posicion,int width,int height,int estado,Bloque bloque){
		super(posicion,width,height,estado,bloque);

	}
    public MunicionBase(Vector2D posicion, Bloque bloque) {
        this(posicion,WIDTH_DEFAULT,HEIGHT_DEFAULT,0,bloque);
    }

    @Override
    public void efecto(Base base) {
        base.shooter();
    }

    @Override
    public  Representacion representacion() {
    	return new Representacion(getNombre(),(int)getPosicion().getX(),(int)getPosicion().getY(),getEstado());
    }
    	
    @Override
    public String getNombre() {
    	return this.getClass().getSimpleName();
    }

    
}
