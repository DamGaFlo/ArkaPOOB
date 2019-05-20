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
public class BaseShooter implements EstadoBase, Serializable {
    private final int tolerancia = 5;
    private final Bala bala;
    private final ArkaPOOB arkaPOOB;
    
    public BaseShooter(Base base) {
        base.setWidth(140);
        arkaPOOB = base.getArkaPOOB();
        bala = crearBala(base);
        
    }

    public Bala crearBala(Base base){
        return new Bala(new Vector2D(base.getCentro().getX(),base.getPosicion().getY()),arkaPOOB,null,Vector2D.getVector(-1.5708,7));

    }

    /**
     * actualiza los datos autonomos de la base
     * @param base
     */
    @Override
    public void update(Base base) {
        base.getPlayer().addScore(bala.getPuntaje());
        bala.setUltimoGolpe(base.getPlayer());
        bala.setPuntaje(0);
        revisaLimite(base);            
    }
    /**
     * ejecutacciones en caso de colicion con la bola
     * @param bola
     * @param base
     */
    @Override
    public void colicion(BolaNormal bola, Base base) {

    }

    @Override
    public Representacion representacion(Base base) {
        return new Representacion(getNombre(),(int) base.getPosicion().getX(),(int)base.getPosicion().getY(),base.getEstado());

    }

    @Override
    public String getNombre() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getWidth(int width) {
        return width;
    }

    @Override
    public int getHeight(int height) {
        return height;
    }
    @Override
    public void movIzq(Base base) {
        if(base.getPosicion().getX()-base.getMoxEnX() >= 0){
            base.getPosicion().cambioX(-base.getMoxEnX());
            if(base.getResidente()!=null) base.getResidente().mov(new Vector2D(-base.getMoxEnX(),0));
        }
    }
    @Override
    public void movDer(Base base) {
        if(base.getPosicion().getX()+base.getWidth()+base.getMoxEnX() <= base.getArkaPOOB().getWidth()){
            base.getPosicion().cambioX(base.getMoxEnX());
            if(base.getResidente()!=null) base.getResidente().mov(new Vector2D(base.getMoxEnX(),0));
        }
    }
@Override
    public void fire(Base base) {                
        if(!arkaPOOB.getBolasEnJuego().contains(bala)){                    
            arkaPOOB.agregarProyectil(bala);
            bala.ajustarSobreBase(base);
            bala.diparado();
            if(bala.fueDestruida()) {
                bala.reconstruir();
            } 
            base.setColiciones(base.getColiciones()+1);
        }
        if(base.getResidente()!=null) {
            base.getResidente().diparado();
            base.setResidente(null);
            revisaLimite(base);
        }
    }

    @Override
    public void pegajosa(Base base) {
        base.setEstado(new BasePegajosa(base));
    }

    @Override
    public void normal(Base base) {
        base.setEstado(new BaseNormal(base));
    }

    @Override
    public void especial(Base base) {
        base.setEstado(new BaseEspecial(base));	
    }

    @Override
    public void small(Base base) {
        base.setEstado(new BaseSmall(base));
    }

    @Override
    public void big(Base base) {
        base.setEstado(new BaseBig(base));
    }

    @Override
    public void shooter(Base base) {
        base.setColiciones(0);
    }

    @Override
    public void revisaLimite(Base base) {
        if(tolerancia == base.getColiciones()) base.restaura();
    }

        
}
