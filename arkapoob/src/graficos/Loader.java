package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

public class Loader {
	
    /**
     * Carga imagenes desde una direccion
     * @param path - direccion de la imagen
     * @return 
     */
	public static BufferedImage ImageLoader(String path)
	{
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}    