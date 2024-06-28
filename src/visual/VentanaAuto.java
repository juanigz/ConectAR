package visual;

import javax.swing.JFrame;

import swing.PanelBorder;
import swing.PanelGradiente;

import javax.imageio.ImageIO;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class VentanaAuto extends JFrame{
	private String localidad;
	private File imagen;
	private Image icono;
	private boolean info;
	private static List<Localidad> listaLocalidades;
	private JLabel lblExito;
	private PanelGradiente panelGradiente1;
	private PanelBorder panelRegistro;
	private PanelBorder panelRegistro2;
	private JTextField usrLocalidad;

	public VentanaAuto() {
		initialize();
	}

	public void initialize() {
		lblExito = new JLabel();
		listaLocalidades = VentanaRegistroControlador.getLista();
		info = false;
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Cargar icono y titulo de ventana
		try {
			imagen = new File("imagenes\\icono.png");
			icono = ImageIO.read(imagen);
			setIconImage(icono);
		} catch (Exception e) {
			System.out.println("Error cargando imagen: " + e.getMessage());
		}
		setTitle("ConectAR");
		setLocationRelativeTo(null); //Centra la ventana en pantalla
		setResizable(false);

		panelGradiente1 = new swing.PanelGradiente();
		panelRegistro = new swing.PanelBorder();
		panelRegistro.setBounds(330, 150, 230, 233);
	
		panelGradiente1.setColorPrimario(new java.awt.Color(146, 233, 251));
        panelGradiente1.setColorSecundario(new java.awt.Color(12, 137, 163));
        
        JLabel lblBienvenido = new JLabel("Bienvenido a ConectAR");
        lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
        lblBienvenido.setForeground(new Color(255, 255, 255));
        lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 60));
        lblBienvenido.setBounds(10, 22, 864, 117);
        panelGradiente1.add(lblBienvenido);
        
        panelRegistro.setBackground(new java.awt.Color(255, 255, 255));
        
        getContentPane().add(panelGradiente1, BorderLayout.CENTER);

        panelGradiente1.setLayer(panelRegistro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        JLabel lblRegistrar = new JLabel("Registrar");
        lblRegistrar.setBounds(0, 23, 230, 37);
        lblRegistrar.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel lblRegistro1 = new JLabel("Localidad");
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
        
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setForeground(new Color(0, 0, 0));
        btnRegistrar.setBackground(Color.LIGHT_GRAY);
        btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRegistrar.setBounds(115, 178, 90, 24);
        panelRegistro.add(btnRegistrar);
        
        JButton btnMapa = new JButton("Mapa");
        btnMapa.setForeground(new Color(0, 0, 0));
        btnMapa.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnMapa.setBackground(Color.LIGHT_GRAY);
        btnMapa.setBounds(24, 178, 68, 24);
        panelRegistro.add(btnMapa);
        
        JLabel lblRegManual = new JLabel("\u00A1Tengo Coordenadas!");
        lblRegManual.setForeground(new Color(0, 0, 255));
        lblRegManual.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegManual.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblRegManual.setBounds(24, 208, 181, 14);
        panelRegistro.add(lblRegManual);
        
        lblRegManual.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblRegManual.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	VentanaRegistroControlador.mostrarManual();
            	VentanaRegistroControlador.cerrarAuto();
            }
        });
        
        btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegistrar.setToolTipText("Registra la primer localidad encontrada");
        btnRegistrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		listaLocalidades = VentanaMapa.getLista();
        		localidad = usrLocalidad.getText();
        		panelRegistro.remove(lblExito);
        		if (!localidad.isBlank()) {
        			Localidad local = VentanaRegistroControlador.buscarPorNombre(localidad);
        			if (local != null) {
        				if (yaIngresada(local, listaLocalidades)) {
        					JOptionPane.showMessageDialog(null, "Localidad ya ingresada.");
        				} else {
        					listaLocalidades = VentanaRegistroControlador.registrarLocalidad(local);
        					if (info) {
        						panelGradiente1.remove(panelRegistro2);
        					}
        					aniadirInfo(local);
        					aniadirExito();
        					limpiarFields();
        				}
        			}
        		} else {
        			JOptionPane.showMessageDialog(null, "Ingrese datos apropiados.");
        		}
        	}
        });
        panelGradiente1.setLayout(null);
        panelGradiente1.add(panelRegistro);
        
        btnMapa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMapa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				VentanaMapaControlador.mostrar();
				VentanaRegistroControlador.cerrarAuto();
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
	private void aniadirInfo(Localidad local) {
		info = true;
		panelRegistro2 = new swing.PanelBorder();
		panelRegistro2.setBounds(330, 394, 230, 133);
		panelGradiente1.add(panelRegistro2);
		panelRegistro2.setBackground(new java.awt.Color(255, 255, 255));
		panelGradiente1.setLayer(panelRegistro2, javax.swing.JLayeredPane.DEFAULT_LAYER);

		JTextArea txtrSeRegistr = new JTextArea();
		txtrSeRegistr.setEditable(false);
		txtrSeRegistr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtrSeRegistr.setText("Se registr\u00F3: \r\n" + local.getNombre() + ", " + local.getProvincia() + 
				"\r\nCon las coordenadas: \r\n" + "Latitud " + local.getLatitud() 
				+ "\r\nLongitud " + local.getLongitud());
		txtrSeRegistr.setBounds(10, 11, 210, 111);
		panelRegistro2.add(txtrSeRegistr);
	}
	private void aniadirExito() {
		lblExito = new JLabel("\u00A1Localidad registrada con éxito!");
		lblExito.setHorizontalAlignment(SwingConstants.LEFT);
		lblExito.setForeground(new Color(0, 128, 0));
		lblExito.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblExito.setBounds(24, 158, 195, 14);
		panelRegistro.add(lblExito);
	}
	private void limpiarFields() {
		usrLocalidad.setText("");
	}
}
