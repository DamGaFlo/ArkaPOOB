package math;


public class Vector2D{
	private double compX;
	private double compY;
	
	public Vector2D(double x,double y){
		compX = x;
		compY = y;
	}
	public Vector2D(){
		this(0,0);
	}
	
	public void setX(double x){
		compX = x;
		
	}
	public void setY(double y){
		compY = y;
	}
	public void cambioX(double delta){
		compX+=delta;
	}
	
	public double getY(){
		return compY;
	}
	public double getX(){
		return compX;
	}
	
	public double getMagnitud(){
		return Math.sqrt(compX*compX+compY*compY);
	}
	
	public Vector2D add(Vector2D v){
		return new Vector2D(compX+v.getX(),compY+v.getY());
	}
	public Vector2D escalar(double v){
		return new Vector2D(compX*v,compY*v);
	}
	public static Vector2D getVector(double angle,double magnitud){
		return new Vector2D(Math.cos(angle)*magnitud,Math.sin(angle)*magnitud);
	}
	public double getAngle(){
		return Math.atan(compY/compX);
	}
	public Vector2D addAngle(double angle){
		return getVector(getAngle()+angle,getMagnitud());
		
	}
}