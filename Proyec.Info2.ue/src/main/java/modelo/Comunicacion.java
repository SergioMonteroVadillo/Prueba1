package modelo;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import controlador.ControladorVentana;
import vista.Ventana;

public class Comunicacion {
	
	private String mensaje = "";
	Ventana ventana = new Ventana(null);

    public Comunicacion() throws MqttException  {

        // Configuración del servidor MQTT
        String broker = "tcp://192.168.1.117:1883";
        String clientId = "Proyecto2";        
        MemoryPersistence persistence = new MemoryPersistence();
        final MqttClient client = new MqttClient(broker, clientId, persistence);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[] { broker });
        options.setCleanSession(true);
        System.out.println("Cara polla");

        // Configuración del suscriptor
        MqttCallback callback = new MqttCallback() {
            //@Override
            public void connectionLost(Throwable throwable) {}

            //@Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                conector(new String(message.getPayload()));
            	System.out.println("Mensaje recibido en " + topic + ": " + new String(message.getPayload()));
               // System.out.println("Cara polla");
                // Publicar mensaje recibido en otro tema
                MqttTopic pubTopic = client.getTopic("Topic");
                MqttMessage pubMessage = new MqttMessage(message.getPayload());
                pubTopic.publish(pubMessage);
            }

            //@Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {}
        };
        client.setCallback(callback);

        // Conexión y suscripción al tema
        client.connect(options);
        String topic = "outTopic";
        client.subscribe(topic);

        // Espera indefinida para recibir mensajes
       
    }
    public void conector(String mensaje) {
    	this.mensaje = mensaje;
    	ControladorVentana control = new ControladorVentana(this.mensaje);
    	System.out.println("Conector: "+mensaje);
    	this.ventana.getTextListado1().setText(this.mensaje);    	

    }
    public String toString() {
    	System.out.println(this.mensaje);
    	//this.ventana.getTextListado1().setText("Mecajo en la putaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    	return "cara polla";//this.mensaje;
    }
}