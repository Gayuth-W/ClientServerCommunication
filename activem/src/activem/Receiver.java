package activem;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver implements MessageListener {
    // Factory for creating JMS connections to the ActiveMQ broker
    private ConnectionFactory factory = null;
    // Connection instance to establish communication with the message broker
    private Connection connection = null;
    // JMS session for sending and receiving messages
    private Session session = null;
    // The destination (queue) where messages are sent and received
    private Destination destination = null;
    // Consumer responsible for receiving messages from the destination queue
    private MessageConsumer consumer = null;

    public Receiver() {
    }

    public void receiveMessage() {
        try {
            // Establishing a JMS connection from the ActiveMQ broker
            factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = factory.createConnection();
            connection.start(); // Starting the connection to begin message reception
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("SAMPLEQUEUE");
            
            // Creating a consumer to receive messages from the specified queue
            consumer = session.createConsumer(destination);
            
            // Receiving a message synchronously (blocking call until a message arrives)
            Message message = consumer.receive();

            // Checking if the received message is of type TextMessage and printing its content
            if (message instanceof TextMessage) {
                TextMessage text = (TextMessage) message;
                System.out.println("Message is : " + text.getText());
            }
        } catch (JMSException e) {
            e.printStackTrace(); // Handling exceptions related to JMS operations
        }
    }

    // Main method to receive messages from the message broker
    public static void main(String[] args) throws JMSException, InterruptedException {
        // Creating an instance of the Receiver class and calling the receiveMessage method
        Receiver receiver = new Receiver();

        // TODO: Uncomment this to register as a listener
        Thread.sleep(1000);
         receiver.registerListener();
        
        // TODO: Comment this line
//        receiver.receiveMessage();
        
        System.out.println("print");
        int i = 0;
        while (true) { // Infinite loop to keep the application running
            Thread.sleep(1000); // Pausing execution for 1 second before updating the counter
            i++;
            System.out.print("\r" + i); // Printing counter value dynamically
        }
    }
    
    // Registers this class as an asynchronous listener with the message broker
    private void registerListener() throws JMSException {
        factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        connection = factory.createConnection();
        connection.start(); // Starting the connection to listen for messages asynchronously
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("SAMPLEQUEUE");
        
        // Creating a message consumer to receive messages asynchronously from the queue
        consumer = session.createConsumer(destination);
        
        // Setting this class as the message listener (callback-based asynchronous mode)
        consumer.setMessageListener(this);
    }
    
    // Asynchronous callback method invoked by the message broker, when a new message is received
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                System.out.println("Received: " + ((TextMessage) message).getText()); // Printing received message
            } catch (JMSException e) {
                e.printStackTrace(); // Handling JMS exception if message retrieval fails
            }
        } else {
            System.out.println("Unexpected non-text message received."); // Handling unexpected message types
        }
    }
}
