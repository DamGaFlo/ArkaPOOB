package gameObjects;

public class Chronometer {
	private long delta, lastTime;
	private long time;
	private boolean running;
	
        /**
         * Constructor del cronometro
         */
	public Chronometer(){
		delta = 0;
		lastTime = System.currentTimeMillis();
		running = false;
	}
	/**
         * Corre el cronometro desde un tiempo especifico
         * @param time 
         */
	public void run(long time){
		running = true;
		this.time = time;
	}
	/**
         * actualiza el estado del cronometro
         */
	public void update(){	
		if(running)
			delta += System.currentTimeMillis() - lastTime;
		if(delta >= time){
			running = false;
			delta = 0;
		}
		
		lastTime = System.currentTimeMillis();
	}
	/**
         * 
         * @return Verifica si esta corriendo
         */
	public boolean isRunning(){
		return running;
	}
	
}