package visual;

import javax.swing.JFrame;

import swing.PanelBorder;
import swing.PanelGradiente;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import controladores.VentanaMapaControlador;
import controladores.VentanaRegistroControlador;
import sistema.Localidad;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class VentanaRegistroManual extends JFrame{
	private String localidad;
	private String provincia;
	private double latitud;
	private double longitud;
	private File imagen;
	private Image icono;
	private List<Localidad> listaLocalidades;
	private JLabel lblExito;
	private PanelGradiente panelGradiente1;
	private PanelBorder panelRegistro;
	private JTextField usrLocalidad;
	private JTextField usrProvincia;
	private JTextField usrLatitud;
	private JTextField usrLongitud;

	public VentanaRegistroManual() {
		initialize();
	}

	public void initialize() {
		lblExito = new JLabel();
		listaLocalidades = VentanaRegistroControlador.getLista();
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
		
		panelGradiente1 = new swing.PanelGradiente();
		panelRegistro = new swing.PanelBorder();
	
		panelGradiente1.setColorPrimario(new java.awt.Color(146, 233, 251));
        panelGradiente1.setColorSecundario(new java.awt.Color(12, 137, 163));
        
        panelRegistro.setBackground(new java.awt.Color(255, 255, 255));
        
        getContentPane().add(panelGradiente1, BorderLayout.CENTER);

        panelGradiente1.setLayer(panelRegistro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        JLabel lblRegistrar = new JLabel("Registro");
        lblRegistrar.setBounds(0, 23, 230, 37);
        lblRegistrar.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel lblRegistro1 = new JLabel("Manual");
        lblRegistro1.setBounds(0, 66, 230, 24);
        lblRegistro1.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistro1.setFont(new Font("Tahoma", Font.BOLD, 30));
        panelRegistro.setLayout(null);
        panelRegistro.add(lblRegistrar);
        panelRegistro.add(lblRegistro1);
        
        usrLocalidad = new JTextField();
        usrLocalidad.setBounds(24, 128, 181, 24);
        panelRegistro.add(usrLocalidad);
        usrLocalidad.setColumns(10);
        
        JLabel lblLocalidad = new JLabel("Localidad");
        lblLocalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblLocalidad.setBounds(25, 113, 67, 14);
        panelRegistro.add(lblLocalidad);
        
        usrProvincia = new JTextField();
        usrProvincia.setColumns(10);
        usrProvincia.setBounds(24, 178, 181, 24);
        panelRegistro.add(usrProvincia);
        
        JLabel lblProvincia = new JLabel("Provincia");
        lblProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProvincia.setBounds(25, 163, 67, 14);
        panelRegistro.add(lblProvincia);
        
        usrLatitud = new JTextField();
        usrLatitud.setColumns(10);
        usrLatitud.setBounds(24, 228, 181, 24);
        panelRegistro.add(usrLatitud);
        
        JLabel lblLatitud = new JLabel("Latitud");
        lblLatitud.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblLatitud.setBounds(25, 213, 67, 14);
        panelRegistro.add(lblLatitud);
        
        usrLongitud = new JTextField();
        usrLongitud.setColumns(10);
        usrLongitud.setBounds(24, 280, 181, 24);
        panelRegistro.add(usrLongitud);

        JLabel lblLongitud = new JLabel("Longitud");
        lblLongitud.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblLongitud.setBounds(25, 257, 67, 27);
        panelRegistro.add(lblLongitud);
        
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setForeground(new Color(0, 0, 0));
        btnRegistrar.setBackground(Color.LIGHT_GRAY);
        btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRegistrar.setBounds(115, 337, 90, 24);
        panelRegistro.add(btnRegistrar);
        
        GroupLayout gl_panelGradiente1 = new GroupLayout(panelGradiente1);
        gl_panelGradiente1.setHorizontalGroup(
        	gl_panelGradiente1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelGradiente1.createSequentialGroup()
        			.addGap(330)
        			.addComponent(panelRegistro, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
        );
        gl_panelGradiente1.setVerticalGroup(
        	gl_panelGradiente1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelGradiente1.createSequentialGroup()
        			.addGap(100)
        			.addComponent(panelRegistro, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE))
        );
        
        JButton btnMapa = new JButton("Mapa");
        btnMapa.setForeground(new Color(0, 0, 0));
        btnMapa.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnMapa.setBackground(Color.LIGHT_GRAY);
        btnMapa.setBounds(24, 337, 68, 24);
        panelRegistro.add(btnMapa);
        
        panelGradiente1.setLayout(gl_panelGradiente1);

        btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localidad = usrLocalidad.getText();
				provincia = usrProvincia.getText();
				panelRegistro.remove(lblExito);
				Localidad local = VentanaRegistroControlador.generarLocalidad(localidad, provincia, latitud, longitud);
				if (yaIngresada(local, listaLocalidades)) {
					JOptionPane.showMessageDialog(null, "Localidad ya ingresada.");
				} else {
					if (verificarDatos()) {
    					listaLocalidades = VentanaRegistroControlador.registrarLocalidad(local);
    					aniadirExito();
    					limpiarFields();
					}
				}
			}
		});
		
        btnMapa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMapa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				VentanaMapaControlador.mostrar();
				VentanaRegistroControlador.cerrarManual();
			}
        });
	}
	private boolean yaIngresada(Localidad localidad, List<Localidad> localidades) {
	    for (Localidad local : localidades) {
	    	if (localidad.getLatitud() == (local.getLatitud()) && localidad.getLongitud() == (local.getLongitud())) {
	    		return true;
	    	}
	    }
	    return false;
	}
	private void aniadirExito() {
        JLabel lblExito = new JLabel("\u00A1Localidad registrada con éxito!");
        lblExito.setForeground(new Color(0, 128, 0));
        lblExito.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblExito.setBounds(25, 315, 195, 14);
        panelRegistro.add(lblExito);
	}
	private void limpiarFields() {
		usrLocalidad.setText("");
		usrProvincia.setText("");
		usrLatitud.setText("");
		usrLongitud.setText("");
	}
	private boolean verificarDatos() {
		boolean ret = true;
		if (localidad.isBlank() || provincia.isBlank()) {
			ret = false;
		}
        try {
            String latitudS = usrLatitud.getText();
            latitud = Double.parseDouble(latitudS);
        } catch (NumberFormatException E) {
        	JOptionPane.showMessageDialog(null, "Ingrese un valor apropiado.");
        	return ret = false;
        }
        try {
            String longitudS = usrLongitud.getText();
            longitud = Double.parseDouble(longitudS);
        } catch (NumberFormatException E) {
        	JOptionPane.showMessageDialog(null, "Ingrese un valor apropiado.");
        	return ret = false;
        }
        return ret;
	}
}
