package Cachewithttl;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TtlCache<Object> cache = new TtlCache<>(2000); // TTL of 2 seconds
        cache.put("A");
        Thread.sleep(1000);
        cache.put("A");
        cache.put("B");
        Thread.sleep(1000);
        cache.put("A");

        System.out.println(cache.get()); // {A=2, B=1}
        Thread.sleep(1000);
        System.out.println(cache.get());

    }
}

