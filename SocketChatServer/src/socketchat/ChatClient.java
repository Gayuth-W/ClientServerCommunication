package socketchat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        // Establish connection to the chat server running on localhost at port 12345
        try (Socket socket = new Socket("localhost", 12345);
             // Reader to receive messages from the server
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             // Writer to send messages to the server
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             // Scanner to read user input from the console
             Scanner scanner = new Scanner(System.in)) {

            // Receive and print the initial prompt from the server (asking for a name)
            System.out.println(reader.readLine());
            // Read the user's name from the console and send it to the server
            String name = scanner.nextLine();
            writer.println(name);
            // Receive and print the server's welcome message
            System.out.println(reader.readLine());

            // Create a separate thread to continuously listen for messages from the server
            Thread listener = new Thread(() -> {
                try {
                    String message;
                    // Keep reading messages from the server and display them to the user
                    while ((message = reader.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Connection with the server lost.");
                }
            });
            // Start the listener thread
            listener.start();

            // Main loop to continuously read user input and send messages to the server
            while (scanner.hasNextLine()) {
                writer.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
