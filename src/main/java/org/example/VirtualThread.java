package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThread {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(200);

        for (int i = 0; i < 10_0000; i++) {
            executor.submit(() -> {
                        // calling blocking I/O operations
                    }
            );
        }


    /*
    * **Problems:**
- ❌ **Expensive**: Each platform thread consumes ~1-2 MB of memory
- ❌ **Limited**: OS limits threads (typically thousands, not millions)
- ❌ **Slow to create**: Thread creation/destruction overhead
- ❌ **Context switching**: OS scheduling overhead
- ❌ **Thread pools required**: Complex management to limit resource usage

### **The Math:**
```
10,000 platform threads × 1 MB each = 10 GB memory
1,000,000 platform threads = IMPOSSIBLE (would crash)
```

## The Solution

**Virtual threads** are:
- **Lightweight**: Minimal memory footprint (few KB)
- **Abundant**: Can create millions of them
- **Cheap**: Fast to create and destroy
- **Managed by JVM**: Not 1:1 with OS threads

### **The Magic:**
Virtual threads run on a small pool of **platform threads** (carrier threads). When a virtual thread blocks (I/O, sleep), the JVM automatically unmounts it and mounts another virtual thread on the same carrier thread.
```
Virtual Threads (millions)
    ↓
Carrier Threads (few - match CPU cores)
    ↓
OS Threads
* */

        Thread thread = Thread.startVirtualThread(() ->
                System.out.println("Hello form virtual thread!"));

        thread.join();

        Thread vThread = Thread.ofVirtual()
                .name("my-virtual-thread")
                .start(() -> System.out.println("Running in: " + Thread.currentThread()));

        try (var vExecutor = Executors.newVirtualThreadPerTaskExecutor()){
            vExecutor.submit(() -> {
                System.out.println("Task in virtual thread");
            });
        }


        /// Example - 2
        HttpClient client = HttpClient.newHttpClient();
        String[] urls = new String[10_000]; // 10,000 URLs
        // ... populate urls

        try (var executor3 = Executors.newVirtualThreadPerTaskExecutor()) {
            for (String url : urls) {
                executor3.submit(() -> {
                    // Each request gets its own virtual thread
                    // Blocking call - but that's OK!
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .build();

                    HttpResponse<String> response =
                            client.send(request, HttpResponse.BodyHandlers.ofString());

                    System.out.println("Fetched: " + url);
                    return response.body();
                });
            }
        }


    }
}

