package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;

import modelo.Comunicacion;
//import modelo.Año;
//import modelo.Control;
import vista.Ventana;

public class ControladorVentana implements ActionListener{
	
	Ventana ventana = new Ventana(null);
	
	Sistema sistema;
	
	String mensaje;

	public ControladorVentana(Sistema s) {
		
		this.sistema = s;
				
	}
	
public ControladorVentana(String m) {
		
		this.mensaje = m;
		System.out.println("Hola zorra "+mensaje);
				
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
			
		
		
		
		

			
			
				Comunicacion control = null;
				try {
					control = new Comunicacion();
				} catch (MqttException e1) {
					e1.printStackTrace();
				}

				this.ventana.getTextListado1().setText(mensaje);
				System.out.println("Hola mierda "+mensaje);
			
			
		
	
		
	}
	
	public void setVentanaAControlar (Ventana v) {
		this.ventana = v;
	}

}
