package math;


public class Vector2D{
	private double compX;
	private double compY;
	
        /**
         * Constructor
         * @param x - posicion vector x
         * @param y - posicion vector y
         */
	public Vector2D(double x,double y){
		compX = x;
		compY = y;
	}
        /**
         * Constructor en el punto (0,0)
         * 
         */
	public Vector2D(){
		this(0,0);
	}
	/**
         * Cambia el valor de x
         * @param x - componente x
         */
	public void setX(double x){
		compX = x;
		
	}
        /**
         * Cambia el valor de y
         * @param y - componente y
         */
	public void setY(double y){
		compY = y;
	}
        /**
         * varia el valor de x
         * @param delta - cantidad de variacion
         */
	public void cambioX(double delta){
		compX+=delta;
	}
	/**
         * devuelve el valor en Y
         * @return 
         */
	public double getY(){
		return compY;
	}
        /**
         * Devuelve valor en X
         * @return 
         */
	public double getX(){
		return compX;
	}
	/**
         * Devuelve la magnitud del componente
         * @return 
         */
	public double getMagnitud(){
		return Math.sqrt(compX*compX+compY*compY);
	}
	
        /**
         * suma un nuevo vector
         * @param v
         * @return 
         */
	public Vector2D add(Vector2D v){
		return new Vector2D(compX+v.getX(),compY+v.getY());
	}
        /**
         * multiplica por un escalar
         * @param v
         * @return 
         */
	public Vector2D escalar(double v){
		return new Vector2D(compX*v,compY*v);
	}
        /**
         * Crea un vector a partir de un tipo polares
         * @param angle
         * @param magnitud
         * @return 
         */
	public static Vector2D getVector(double angle,double magnitud){
		return new Vector2D(Math.cos(angle)*magnitud,Math.sin(angle)*magnitud);
	}
        /**
         * devuelve el angulo a partir de las componentes
         * @return 
         */
	public double getAngle(){
		return Math.atan(compY/compX);
	}
        /**
         * suma un angulo a un vector
         * @param angle
         * @return 
         */
	public Vector2D addAngle(double angle){
		return getVector(getAngle()+angle,getMagnitud());
		
	}
}