package controladores;

import javax.swing.UIManager;

public class Principal {
	
	public static VentanaMapaControlador instanciaMapaControlador;
	public static VentanaRegistroControlador instanciaRegistroControlador;
	
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			instanciaRegistroControlador = new VentanaRegistroControlador();
			instanciaMapaControlador = new VentanaMapaControlador();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}