package controladores;

import java.awt.EventQueue;

import visual.VentanaMapa;

public class VentanaMapaControlador {

	static VentanaMapa ventanaMapa;
	
	public VentanaMapaControlador() {
		inicializarVentanaMapa();
	}
	
	public static void inicializarVentanaMapa() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaMapa = new VentanaMapa();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//Eventos
	public static void cerrar() {
		ventanaMapa.setVisible(false);
	}
	
	public static void mostrar() {
		ventanaMapa.setVisible(true);
	}
}
