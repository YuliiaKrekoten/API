package netology;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CrptApi {
    private final int requestLimit;
    private final long intervalMillis;
    private final Lock lock;
    private final AtomicInteger requestCount;
    private long lastRequestTime;

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        this.requestLimit = requestLimit;
        this.intervalMillis = timeUnit.toMillis(1);
        this.lock = new ReentrantLock();
        this.requestCount = new AtomicInteger(0);
        this.lastRequestTime = System.currentTimeMillis();
    }

    public void createDocument(Object document, String signature) {
        try {
            lock.lock();

            // Check if the request limit has been reached
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastRequestTime >= intervalMillis) {
                requestCount.set(0);
                lastRequestTime = currentTime;
            }

            // Wait until the request count is below the limit
            while (requestCount.incrementAndGet() > requestLimit) {
                Thread.sleep(100);
            }

            // Send the API request
            sendRequest(document, signature);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void sendRequest(Object document, String signature) {
        // Implement the API request here
        // Use HTTP client and JSON serialization libraries to send the POST request
        // Example:
        // HttpClient httpClient = HttpClient.newHttpClient();
        // HttpRequest request = HttpRequest.newBuilder()
        //         .uri(URI.create("https://ismp.crpt.ru/api/v3/1k/documents/create"))
        //         .header("Content-Type", "application/json")
        //         .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
        //         .build();
        // HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // System.out.println(response.body());
    }
}