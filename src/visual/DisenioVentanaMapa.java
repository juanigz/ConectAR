package visual;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import sistema.Conexion;
import sistema.Kruskal;
import sistema.Localidad;

public class DisenioVentanaMapa {

	public static void crearVertices(ArrayList<MapMarker> marcas, List<Localidad> localidades) {
	    for (Localidad localidad : localidades) {
	    	Coordinate coordenadas = new Coordinate(localidad.getLatitud(), localidad.getLongitud());
			MapMarker marca = new MapMarkerDot(localidad.getNombre(), coordenadas);
	    	marcas.add(marca);
	    }
	}
	public static void cargarVertices(JMapViewer mapa, ArrayList<MapMarker> marcas) {
		for (MapMarker marca : marcas) {
			mapa.addMapMarker(marca);			
		}
	}
	public static JMapViewer mostrarAristasAGM(JMapViewer mapa, List<Localidad> localidades) {
		ArrayList<Coordinate> coords = new ArrayList<>();
		JMapViewer mapaRet = mapa;
		for (Localidad local : localidades) {
			coords.add(new Coordinate(local.getLatitud(), local.getLongitud()));
			MapPolygon polygon = new MapPolygonImpl(coords);
			polygon.getStyle().setBackColor(null);
			polygon.getStyle().setColor(Color.BLUE);
			mapaRet.addMapPolygon(polygon);
		}
		return mapaRet;
	}
	public static List<Conexion> crearConexiones(List<Localidad> localidades) {
		ArrayList<Conexion> conexiones = new ArrayList<>();
		for (Localidad origen : localidades) {
			for (Localidad destino : localidades) {
				if (origen != destino) {
					Conexion conexion = new Conexion(origen, destino);
					conexiones.add(conexion);
				}
			}
		}
		return conexiones;
	}
	
	public static void mostrarAGM(JMapViewer mapa, List<Localidad> localidades, List<Conexion> conexiones) {
	    List<Conexion> result = Kruskal.arbolGeneradorMinimo(localidades, conexiones);

	    if (result == null) {
	    	return;
	    }
	    
	    for (Conexion conex : result) {
	    	List<Coordinate> coords = new ArrayList<Coordinate>();
	    	Localidad origen = conex.getOrigen();
	    	Localidad destino = conex.getDestino();
	    	coords.add(new Coordinate(origen.getLatitud(), origen.getLongitud()));
	    	coords.add(new Coordinate(destino.getLatitud(), destino.getLongitud()));
	    	coords.add(new Coordinate(origen.getLatitud(), origen.getLongitud())); //Cierra el poligono

	    	MapPolygon polygon = new MapPolygonImpl(coords);
	    	polygon.getStyle().setColor(Color.RED);
	    	polygon.getStyle().setBackColor(null);
	    	mapa.addMapPolygon(polygon);
	    }
	}
}
