package sistema;

public class Conexion implements Comparable<Conexion> {
	private Localidad origen;
    private Localidad destino;
    private double costoTotal;
    private double distancia;
    private double costoPorKM;
    private double costoConAum;
    private double costoFijo;

    public Conexion(Localidad origen, Localidad destino) {
        this.origen = origen;
        this.destino = destino;
        
    }

    public Localidad getOrigen() {
        return origen;
    }

    public Localidad getDestino() {
        return destino;
    }
    
    public double getCostoTotal() {
    	return costoTotal;
    }
    public double getDistancia() {
    	return distancia;
    }
    
    public double getCostoPorKM() {
    	return costoPorKM;
    }
    
    public double getCostoConAum() {
    	return costoConAum;
    }
    
    public double getCostoFijo() {
    	return costoFijo;
    }
    
    public double calcularCosto() {
    	double porcentajeAumento = 0.25;
    	double costoPorKilometro = 5.0;
    	costoFijo = 0;
    	costoConAum = 0;
        distancia = calcularDistancia(origen.getLatitud(), origen.getLongitud(), destino.getLatitud(), destino.getLongitud());
        double costo = distancia * costoPorKilometro;
        costoPorKM = costo;
        if (!origen.getProvincia().equals(destino.getProvincia())) {
            costoFijo = 1500.00;
        }
        if (distancia > 300.0) {
        	costoConAum = costo * porcentajeAumento;
        }
        costoTotal = costo + costoFijo + costoConAum;
        return costo;
    }

    public double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
    	// código para calcular la distancia entre dos puntos en la Tierra
        // usando la fórmula de Haversine
        double radioTierra = 6371; // en kilómetros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distancia = radioTierra * c;
        return distancia;
    }


    @Override
    public int compareTo(Conexion otraArista) {
        return Double.compare(this.calcularCosto(), otraArista.calcularCosto());
    }
}
