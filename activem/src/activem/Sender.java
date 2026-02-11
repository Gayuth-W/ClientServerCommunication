package activem;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

	// Creating a connection factory object that allows clients to create connections to the JMS provider.
	private ConnectionFactory factory = null;
	// Initializing the connection to the JMS provider. This is the active connection instance.
	private Connection connection = null;
	// Initializing a session, which acts as a single-threaded context for producing and consuming messages.
	private Session session = null;
	// Initializing a destination, which represents the message queue where messages will be sent.
	private Destination destination = null;
	// Initializing a message producer that will send messages to the specified destination.
	private MessageProducer producer = null;

	public Sender() {
	}

	public void sendMessage() {
		try {
			// Creating a connection factory instance using ActiveMQ's default broker URL.
			factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
			
			// Creating a new connection using the factory instance.
			connection = factory.createConnection();
			
			// TODO: Set the async mode to true
			 ((ActiveMQConnection)connection).setUseAsyncSend(true);//Enabling async mode to be true-IT24104152
			
			
			// Starting the connection to begin message delivery.
			connection.start();
			/* Creating a session.
			 * The first argument (false) indicates that the session is not transacted.
			 * The second argument (Session.AUTO_ACKNOWLEDGE) means the session will automatically
			 * acknowledge messages once they have been successfully received.
			 */
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			// Creating a queue named SAMPLEQUEUE, which acts as the message destination.
			destination = session.createQueue("SAMPLEQUEUE");
			
			// Creating a message producer responsible for sending messages to the specified destination.
			producer = session.createProducer(destination);
			
			// Creating a text message instance using the session object.
			TextMessage message = session.createTextMessage();
			// Setting the actual text content of the message.
			message.setText("Hello ...This is a sample message..sending from FirstClient");
			
			// Sending the message to the destination queue.
			producer.send(message);
			// Logging the sent message to the console.
			System.out.println("Sent: " + message.getText());
			
			// Closing the producer to release resources.
			producer.close();
			// Closing the session to properly terminate its lifecycle.
			session.close();
			// Closing the connection to disconnect from the JMS provider.
			connection.close();
		} catch (JMSException e) {
			// Handling any JMS-related exceptions that may occur during execution.
			e.printStackTrace();
		}
	}

	// Entry point of the application, which creates an instance of Sender and calls the sendMessage method.
	public static void main(String[] args) {
		// Instantiating the Sender class and invoking sendMessage to send a message to the queue.
		Sender sender = new Sender();
		sender.sendMessage();
	}
}
