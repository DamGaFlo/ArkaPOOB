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

public class Abrir {
    
    
    public static ArkaPOOB abrir(File archivo) throws CompilacionExcepcion{
        if (!archivo.getName().endsWith(".dat")) throw new CompilacionExcepcion(CompilacionExcepcion.ARCHIVO_INVALIDO);
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))){
            return (ArkaPOOB)in.readObject();
        }catch(Exception e){
            throw new CompilacionExcepcion(CompilacionExcepcion.ERROR_ABRIR);
			
	}
    }
}
