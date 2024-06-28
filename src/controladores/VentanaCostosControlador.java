package controladores;

import java.util.List;

import sistema.Conexion;
import sistema.Kruskal;
import sistema.Localidad;
import visual.VentanaCostos;

public class VentanaCostosControlador {

	static VentanaCostos ventanaCostos = new VentanaCostos();

	public static void cerrar() {
		ventanaCostos.setVisible(false);
	}
	
	public static void mostrar() {
		ventanaCostos.setVisible(true);
	}
	
	public static double mostrarCostoTotal(List<Localidad> localidades, List<Conexion> conexiones) {
		double ret = 0;
	    List<Conexion> result = Kruskal.arbolGeneradorMinimo(localidades, conexiones);
	    if (result == null) {
	    	return 0;
	    }
	    for (Conexion conex : result) {
	    	ret += conex.getCostoTotal();
	    }
	    return ret;
	}
	public static double mostrarCostoConAumento(List<Localidad> localidades, List<Conexion> conexiones) {
		double ret = 0;
	    List<Conexion> result = Kruskal.arbolGeneradorMinimo(localidades, conexiones);
	    if (result == null) {
	    	return 0;
	    }
	    for (Conexion conex : result) {
	    	ret += conex.getCostoConAum();
	    }
	    return ret;
	}
	public static double mostrarCostoFijo(List<Localidad> localidades, List<Conexion> conexiones) {
		double ret = 0;
	    List<Conexion> result = Kruskal.arbolGeneradorMinimo(localidades, conexiones);
	    if (result == null) {
	    	return 0;
	    }
	    for (Conexion conex : result) {
	    	ret += conex.getCostoFijo();
	    }
	    return ret;
	}
	public static double mostrarCostoPorKM(List<Localidad> localidades, List<Conexion> conexiones) {
		double ret = 0;
	    List<Conexion> result = Kruskal.arbolGeneradorMinimo(localidades, conexiones);
	    if (result == null) {
	    	return 0;
	    }
	    for (Conexion conex : result) {
	    	ret += conex.getCostoPorKM();
	    }
	    return ret;
	}
	public static void actualizarTabla(List<Localidad> localidades, List<Conexion> conexiones) {
		VentanaCostos.actualizarTabla(localidades, conexiones);
	}
}
