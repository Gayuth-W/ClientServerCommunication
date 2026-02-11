package temp;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TemperatureServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Create and start the gRPC server on port 50051
        Server server = ServerBuilder.forPort(50051)
                .addService(new TemperatureServiceImpl())
                .build();

        System.out.println("Temperature Server started on port 50051");
        server.start();
        server.awaitTermination(); // Keep server running
    }

    // Implementation of the TemperatureService
    static class TemperatureServiceImpl extends TemperatureServiceGrpc.TemperatureServiceImplBase {
        @Override
        public void subscribeTemperature(SubscribeRequest request, StreamObserver<TemperatureUpdate> responseObserver) {
            Random random = new Random(); // Random generator for temperature values
            float lastTemp = -1;

            while (true) {  // Loop indefinitely until the client disconnects
                try {
                    TimeUnit.SECONDS.sleep(3); // Wait for 3 seconds between updates
                    float currentTemp = 20 + random.nextFloat() * 10; // Generate random temperature between 20 and 30

                    // Always print the current temperature to the console
                    System.out.println("Current Temperature: " + currentTemp + "�C");

                    // Notify client only if the temperature change is significant
                    if (lastTemp == -1 || Math.abs(currentTemp - lastTemp) > 5) { // Threshold for change is 5�C
                    	
                        lastTemp = currentTemp;
                        TemperatureUpdate update = TemperatureUpdate.newBuilder().setTemperature(currentTemp).build();
                        responseObserver.onNext(update); // Send update to the client
                        System.out.println("Clients Notified: " + currentTemp + "�C");
                    }

                    // Check if client has disconnected
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Client disconnected, stopping updates.");
                        responseObserver.onCompleted(); // Complete the stream
                        break;
                    }

                } catch (InterruptedException e) {
                    System.out.println("Temperature update loop interrupted.");
                    responseObserver.onError(e); // Notify client of the error
                    return;
                }
            }
        }
    }
}
