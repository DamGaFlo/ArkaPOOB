package presentacion;


import aplicacion.ArkaPOOB2P;

public class ArkaPoob2pGUI extends ArkaPoobGUI {
	
	public void generaMundo() {
		game = new ArkaPOOB2P();
		game.inicio();
	}
}
