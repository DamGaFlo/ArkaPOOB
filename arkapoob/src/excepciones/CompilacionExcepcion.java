/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author IJuanKhoxD
 */
public class CompilacionExcepcion extends Exception {
    public final static String ERROR_SALVAR = "Error guardando";
	public final static String ERROR_ABRIR = "Error durante la apertura del archivo";
	public final static String ERROR_EXPORTAR = "Error durante la exportacion del archivo";
	public final static String ERROR_IMPORTAR = "Error durante la importacion del archivo";
	public static final String ARCHIVO_INVALIDO = "Archivo no compatible";
        
    public CompilacionExcepcion(String message){		
            super(message);
    }
    
}
