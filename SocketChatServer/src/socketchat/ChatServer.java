package socketchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {
    // Thread-safe set to store unique client names
    private static Set<String> clientNames = Collections.synchronizedSet(new HashSet<>());
    // Thread-safe set to store client output streams for broadcasting messages
    private static Set<PrintWriter> clientWriters = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) {
        System.out.println("Chat server is running...");
        try (ServerSocket serverSocket = new ServerSocket(12345)) { // Server listens on port 12345
            while (true) {
                // Accept incoming client connections and handle them in separate threads
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // This inner class defines the threads that communicate with each client
    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private String name;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        
        // In a thread class, this method is run in parallel by multiple threads
        public void run() {
            try (
            		
                // input and output streams to read and write from sockets connecting to clients
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            ) {
                out = new PrintWriter(socket.getOutputStream(), true);

                // Prompt the client to enter a unique name
                while (true) {
                    out.println("Enter your name:");
                    name = reader.readLine();
                    if (name == null || name.trim().isEmpty() || clientNames.contains(name)) {
                        out.println("Invalid or duplicate name. Try again.");
                    } else {
                        // TODO Task 2: Explain why the synchronized keyword in Java is used here. Why is it necessary
                    	// to use this keyword when adding a name to the set of names?
                        synchronized (clientNames) {
                            clientNames.add(name);
                        }
                        break;
                    }
                }

                out.println("Welcome, " + name + "! Type your message...");
                clientWriters.add(out); // Add the client's output stream to the broadcast list
                String message;

                // Continuously listen for messages from the client and broadcast them
                while ((message = reader.readLine()) != null) {
                    synchronized (clientWriters) {
                    	
                    	// TODO Task 1: Add a long delay here to demonstrate blocking communication
                    	// Hint: You can use Thread.sleep method to delay a thread.
                    	try {
                        	Thread.sleep(5000);
                    	}catch(InterruptedException e) {
                    		System.out.println("Error when sleep thread");
                    	}
                    	
                    	
                        for (PrintWriter writer : clientWriters) {
                            writer.println(name + ": " + message);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Connection with a client lost");
            } finally {
                // Remove the client from the lists upon disconnection
                if (name != null) {
                    clientNames.remove(name);
                }
                if (out != null) {
                    clientWriters.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
