package controladores;

import java.awt.EventQueue;
import java.util.List;

import sistema.Geonames;
import sistema.Localidad;
import sistema.Registro;
import visual.VentanaAuto;
import visual.VentanaRegistroManual;

public class VentanaRegistroControlador {
	
	
	public static VentanaAuto ventanaAuto;
	public static VentanaRegistroManual ventanaRegistroManual;
	
	public VentanaRegistroControlador() {
		inicializarVentanaRegistro();
	}
	
	private static void inicializarVentanaRegistro() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaAuto = new VentanaAuto();
					mostrarAuto();
					ventanaRegistroManual = new VentanaRegistroManual();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void cerrarManual() {
		ventanaRegistroManual.setVisible(false);
	}
	public static void cerrarAuto() {
		ventanaAuto.setVisible(false);
	}
	public static void mostrarManual() {
		ventanaRegistroManual.setVisible(true);
	}
	public static void mostrarAuto() {
		ventanaAuto.setVisible(true);
	}
	public static Localidad generarLocalidad(String nombre, String provincia, double latitud, double longitud) {
		return Registro.generarLocalidad(nombre, provincia, latitud, longitud);
	}
	public static List<Localidad> getLista() {
		return Registro.getLista();
	}
	public static List<Localidad> registrarLocalidad(Localidad localidad) {
		return Registro.registrarLocalidad(localidad);
	}
	public static List<Localidad> eliminarLocalidad(String localidad) {
		return Registro.eliminarLocalidad(localidad);
	}
	public static void guardarJson(List<Localidad> localidades) {
		Registro.guardarJson(localidades);
	}
	public static Localidad buscarPorNombre(String nombre) {
		try {
			return Geonames.buscarPorNombre(nombre);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
