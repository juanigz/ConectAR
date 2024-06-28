package sistema;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;

public class TestSistema {
	private Localidad localidadA;
    private Localidad localidadB;
    private Localidad localidadC;
    private Localidad localidadD;
    
    private Conexion conexionAB;
    private Conexion conexionAC;
    private Conexion conexionAD;
    private Conexion conexionBC;
    private Conexion conexionBD;
    private Conexion conexionCD;    
    
    private List<Localidad> localidades;
    private List<Conexion> conexiones;
    
	@BeforeEach
	public void setUp() throws Exception {
    localidadA = new Localidad("A", "A", 40.7128, -74.0060);
    localidadB = new Localidad("B", "B", 34.0522, -118.2437);
    localidadC = new Localidad("C", "C", -118.2437, 34.0522);
    localidadD = new Localidad("D", "D", -74.0060, 40.7128);
    
    // Crear conexiones con pesos (distancias, costos, etc.) entre localidades
    conexionAB = new Conexion(localidadA, localidadB);
    conexionAC = new Conexion(localidadA, localidadC);
    conexionAD = new Conexion(localidadA, localidadD);
    conexionBC = new Conexion(localidadB, localidadC);
    conexionBD = new Conexion(localidadB, localidadD);
    conexionCD = new Conexion(localidadC, localidadD);
	
    localidades = Arrays.asList(localidadA, localidadB, localidadC, localidadD);
    conexiones = Arrays.asList(conexionAB, conexionAC, conexionAD, conexionBC, conexionBD, conexionCD);
    
	}
	
	@org.junit.jupiter.api.Test
	public void agregarLocalidadDaTrue() {
		Localidad localidadE = new Localidad("E", "E", 20.45, 24.040);
		
		localidades = Registro.registrarLocalidad(localidadE);
		
		assertTrue(localidades.contains(localidadE));
	}
	
	@org.junit.jupiter.api.Test
	public void eliminarLocalidadDaTrue() {
		localidades = Registro.eliminarLocalidad(localidadA.getNombre());
		
		assertTrue(!localidades.contains(localidadA));
	}
	
	
    @org.junit.jupiter.api.Test
    public void testCalcularCostoNoIgual() {
        double costoEsperado = 3934.0; // Calcula el costo esperado manualmente o utilizando una herramienta externa
        double costoCalculado = conexionAB.calcularCosto();
        assertNotEquals(costoEsperado, costoCalculado, 1e-6); //1e-6 es una variable que permite cierto margen de error en el test
    }
    
    @org.junit.jupiter.api.Test
    public void calcularCostoDaIgual() {
        double costoEsperado = 19678.731273048612;
        double costoCalculado = conexionAB.calcularCosto();
        assertEquals(costoEsperado, costoCalculado, "El costo calculado no coincide con el costo esperado");
    }
    
	@org.junit.jupiter.api.Test
    public void AGMContiene() {
		
        List<Conexion> resultado = Kruskal.arbolGeneradorMinimo(localidades, conexiones);

        assertEquals(3, resultado.size());
        assertTrue(resultado.contains(conexionAB));
        assertTrue(resultado.contains(conexionBC));
        assertTrue(resultado.contains(conexionCD));
    }
	
	@org.junit.jupiter.api.Test
	public void AGMnull() {
		List<Localidad> locs = new ArrayList<>();
		List<Conexion> conexs = new ArrayList<>();
		
		List<Conexion> agm = Kruskal.arbolGeneradorMinimo(locs, conexs);
		
		assertEquals(agm, null, "El AGM no es null");
	}
	
	@org.junit.jupiter.api.Test
    public void calcularDistanciaNoIgual() {
		Localidad localidad1 = conexionAB.getOrigen();
		Localidad localidad2 = conexionAB.getDestino();
        double distanciaEsperada = 3934.0;
        double distanciaCalculada = conexionAB.calcularDistancia(localidad1.getLatitud(), localidad1.getLongitud(), localidad2.getLatitud(), localidad2.getLongitud());

        assertNotEquals(distanciaEsperada, distanciaCalculada, 1e-6);
    }
	
	@org.junit.jupiter.api.Test
    public void calcularDistanciaDaIgual() {
		Localidad localidad1 = conexionAB.getOrigen();
		Localidad localidad2 = conexionAB.getDestino();
        double distanciaEsperada = 3935.746254609722; // Calcula la distancia esperada manualmente o utilizando una herramienta externa
        double distanciaCalculada = conexionAB.calcularDistancia(localidad1.getLatitud(), localidad1.getLongitud(), localidad2.getLatitud(), localidad2.getLongitud());

        assertEquals(distanciaEsperada, distanciaCalculada, "La distancia calculada no coincide con la distancia esperada");
    }
}
