/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import aplicacion.*;
import excepciones.CompilacionExcepcion;
import java.io.*;

/**
 *
 * @author IJuanKhoxD
 */
public class Salvar {
    
    
    public static void salvar(File archivo, ArkaPOOB juego) throws CompilacionExcepcion{
        if (!archivo.getName().endsWith(".dat")) throw new CompilacionExcepcion(CompilacionExcepcion.ARCHIVO_INVALIDO);
        try{
			
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo));
            out.writeObject(juego);
            out.close();
	}catch(IOException e){            
            throw new CompilacionExcepcion(CompilacionExcepcion.ERROR_SALVAR);
	}
    }

    
		
		
   
}
