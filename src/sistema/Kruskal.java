package sistema;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kruskal {

    private static class Subset {
        int parent;
        int rank;
    }

    private static int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    private static void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
    
    public static List<Conexion> arbolGeneradorMinimo(List<Localidad> localidades, List<Conexion> conexiones) {
        if (localidades.isEmpty() || conexiones.isEmpty()) {
        	return null;
        }
    	
    	List<Conexion> resultado = new ArrayList<>();

        conexiones.sort(Comparator.comparingDouble(Conexion::calcularCosto));

        Subset[] subsets = new Subset[localidades.size()];
        for (int i = 0; i < localidades.size(); ++i) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        int e = 0;
        int i = 0;
        while (e < localidades.size() - 1 && i < conexiones.size()) {
            Conexion conexionActual = conexiones.get(i++);
            int x = find(subsets, localidades.indexOf(conexionActual.getOrigen()));
            int y = find(subsets, localidades.indexOf(conexionActual.getDestino()));

            if (x != y) {
                resultado.add(conexionActual);
                union(subsets, x, y);
                e++;
            }
        }

        return resultado;
    }
}

