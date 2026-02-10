package temp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class TemperatureClient {
    public static void main(String[] args) throws InterruptedException {
        // Create a gRPC channel to connect to the server
        // The server is running on localhost at port 50051
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext() // Use plaintext (non-SSL) connection
                .build();

        // Create an asynchronous stub to communicate with the gRPC service
        TemperatureServiceGrpc.TemperatureServiceStub stub = TemperatureServiceGrpc.newStub(channel);

        // Create a CountDownLatch to keep the client running while receiving responses
        CountDownLatch latch = new CountDownLatch(1);

        // Build the request to subscribe to temperature updates
        SubscribeRequest request = SubscribeRequest.newBuilder().setClientId("client_1").build();

        // Call the gRPC service asynchronously
        stub.subscribeTemperature(request, new StreamObserver<TemperatureUpdate>() {
            
        	// TODO: This is the callback function called by the server
        	// when the temperature changes by more than 5 degrees of Celcius.
        	// Update it to print the received temperature update. 
        	// hint: you can use the getTemperature method in update object to get
        	// the temperature value
        	@Override
            public void onNext(TemperatureUpdate update) {
                // Print the received temperature update to the console
        		System.out.println("Updated "+update);
        	
            }

        	// callback function to handle errors
            @Override
            public void onError(Throwable t) {
                // Handle any errors encountered during streaming
                System.err.println("Error: " + t.getMessage());
                latch.countDown(); // Allow main thread to exit
            }

        	// callback function called when the communication is completed
            @Override
            public void onCompleted() {
                // Handle stream completion when the server stops sending data
                System.out.println("Temperature stream completed.");
                latch.countDown(); // Allow main thread to exit
            }
        });

        // Keep the client running until the stream completes
        latch.await(); 

        // Shutdown the gRPC channel gracefully
        channel.shutdown();
        channel.awaitTermination(5, TimeUnit.SECONDS);
    }
}
