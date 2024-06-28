package visual;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controladores.VentanaCostosControlador;
import controladores.VentanaMapaControlador;
import sistema.Conexion;
import sistema.Kruskal;
import sistema.Localidad;
import swing.PanelGradiente;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class VentanaCostos extends JFrame {
	private PanelGradiente panelGradiente1;
    private static JTable tabla;
    private static DefaultTableModel modelo;
	private File imagen;
	private Image icono;
    private static int filasPorPagina = 100;
    public VentanaCostos() {
    	initialize();
    }
    
    public void initialize() {
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			imagen = new File("imagenes\\icono.png");
			icono = ImageIO.read(imagen);
			setIconImage(icono);
		} catch (Exception e) {
			System.out.println("Error cargando imagen: " + e.getMessage());
		}
		setTitle("ConectAR");
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		panelGradiente1 = new swing.PanelGradiente();
		
		panelGradiente1.setColorPrimario(new java.awt.Color(146, 233, 251));
        panelGradiente1.setColorSecundario(new java.awt.Color(12, 137, 163));
        panelGradiente1.setBounds(0, 0, 884, 600);

        getContentPane().add(panelGradiente1);
        
        JButton btnVolver = new JButton("Volver al Mapa");
        btnVolver.setForeground(new Color(0, 0, 0));
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnVolver.setBackground(Color.LIGHT_GRAY);
        btnVolver.setBounds(10, 525, 119, 24);
        panelGradiente1.add(btnVolver);
        
        String[] columnas = {"Conexion", "Origen", "Destino", "Distancia", 
        		"Costo KM", "Aumento", "Costo Fijo", "Total"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Aca se define si la celda es editable o no
                return false;
            }
        };
        tabla = new JTable(modelo);
        tabla.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Font font = new Font("Tahoma", Font.BOLD, 12);
        tabla.getTableHeader().setFont(font);
        tabla.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(10, 11, 864, 490);

        panelGradiente1.add(scrollPane);
       
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				VentanaMapaControlador.mostrar();
				VentanaCostosControlador.cerrar();
			}
        });
    }
    
    // Actualizar la tabla con los datos
    public static void actualizarTabla(List<Localidad> localidades, List<Conexion> conexiones) {
        // Obtener la lista de localidades, conexiones y generar AGM
    	modelo.setRowCount(0); // reincio las localidades
        List<Conexion> conex = Kruskal.arbolGeneradorMinimo(localidades, conexiones);
	    if (conex == null) {
	    	return;
	    }
        int fin = Math.min(filasPorPagina, conex.size());
        for (int i = 0; i < fin; i++) {
    		DecimalFormat df = new DecimalFormat("#.##");
            Conexion conexion = conex.get(i);
            modelo.addRow(new Object[]{i, conexion.getDestino().getNombre(), conexion.getOrigen().getNombre(), 
            		df.format(conexion.getDistancia()),
            		df.format(conexion.getCostoPorKM()), df.format(conexion.getCostoConAum()), 
            		df.format(conexion.getCostoFijo()), df.format(conexion.getCostoTotal())});
        }
        modelo.fireTableDataChanged();
        //Cambio el tamano de las columnas 1 y 2 para que entren bien los nombres
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
    }
}
