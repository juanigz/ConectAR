package sistema;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import gSon.GenerarGson;
import gSon.ListaLocalidades;

public class Registro {
    private static GenerarGson gsonGenerator = new GenerarGson();
    private static ListaLocalidades listaLocalidades = gsonGenerator.getListaDesdeJson();
    private static List<Localidad> listaLocs = listaLocalidades.getLista();
    
	public static Localidad generarLocalidad(String nombre, String provincia, double latitud, double longitud) {
		Localidad localidad = new Localidad(nombre, provincia, latitud, longitud);
		return localidad;
	}
	
	public static List<Localidad> registrarLocalidad(Localidad localidad) {
		listaLocs.add(localidad);
		return listaLocs;
	}
	
	public static List<Localidad> eliminarLocalidad(String localidad) {
	    boolean encontrada = false;
	    List<Localidad> ret = new ArrayList<>();
	    for (Localidad local : listaLocs) {
	        if (local.getNombre().equalsIgnoreCase(localidad.trim())) {
	            encontrada = true;
	        } else {
	            ret.add(local);
	        }
	    }
	    listaLocs = ret;
	    if (!encontrada && !listaLocs.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "La localidad no se encontró en la lista");
	        return listaLocs;
	    }
	    return ret;
	}
	
	private static boolean yaIngresada(Localidad localidad, List<Localidad> localidades) {
	    for (Localidad local : localidades) {
	    	if (localidad.getLatitud() == (local.getLatitud()) && localidad.getLongitud() == (local.getLongitud())) {
	    		return true;
	    	}
	    }
	    return false;
	}
	
	public static void guardarJson(List<Localidad> localidades) {
	    ListaLocalidades listaLocalidades = new ListaLocalidades();
	    for (Localidad localidad : localidades) {
	        if (!yaIngresada(localidad, listaLocalidades.getLista())) {
	            listaLocalidades.agregarLocalidad(localidad.getNombre(), localidad.getProvincia(), localidad.getLatitud(), localidad.getLongitud());                
	        }
	    }
	    Gson gson = new Gson();
	    String json = gson.toJson(listaLocalidades);
	    try (Writer writer = new FileWriter("listaLocalidades.json")) {
	        writer.write(json);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static ListaLocalidades getListaLocalidades() {
		return listaLocalidades;
	}
	public static List<Localidad> getLista() {
		return listaLocs;
	}

}
