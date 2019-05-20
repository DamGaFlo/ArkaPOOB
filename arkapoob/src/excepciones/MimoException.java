package excepciones;

public class MimoException extends Exception{
	public final static String ERROR_MIMO_SOLO = "Error no hay a quien imitar";
        
    public MimoException(String message){		
            super(message);
    }
}
