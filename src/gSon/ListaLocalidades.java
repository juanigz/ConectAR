package gSon;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import sistema.Localidad;

public class ListaLocalidades implements Serializable {
    private static final long serialVersionUID = 1L;
    private LinkedList<Localidad> lista;

    public ListaLocalidades() {
        lista = new LinkedList<Localidad>();
    }

    public void agregarLocalidad(String nombre, String provincia, double latitud, double longitud) {
        Localidad localidad = new Localidad(nombre, provincia, latitud, longitud);
        lista.add(localidad);
    }

    public Localidad getLocalidad() {
        if (!lista.isEmpty()) {
            return lista.removeFirst();
        } else {
            throw new RuntimeException();
        }
    }

    public boolean noExistenLocalidades() {
        return lista.isEmpty();
    }
    
    public List<Localidad> getLista() {
        return lista;
    }
}
