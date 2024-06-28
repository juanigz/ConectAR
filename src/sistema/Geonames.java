package sistema;

import javax.swing.JOptionPane;

import org.geonames.InvalidParameterException;
import org.geonames.Style;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

public class Geonames {
	public static Localidad buscarPorNombre(String nombre) throws InvalidParameterException {
		WebService.setUserName("franbarri"); //Nombre de mi cuenta para poder acceder a la API de Geonames
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		searchCriteria.setQ(nombre); //Busca la localidad por nombre
		searchCriteria.setCountryCode("AR"); //Seteo para que solo busque en Argentina
		searchCriteria.setStyle(Style.FULL); //Me muestra la provincia de la localidad
		ToponymSearchResult searchResult;
		try {
			searchResult = WebService.search(searchCriteria);
			if (searchResult.getToponyms().size() > 0) {
				for (Toponym toponym : searchResult.getToponyms()) {
					Localidad local = Registro.generarLocalidad(toponym.getName(), toponym.getAdminName1(), toponym.getLatitude(), toponym.getLongitude());
					return local;
				}
			} else {
				JOptionPane.showMessageDialog(null, "No se encontraron resultados para: " + nombre);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
