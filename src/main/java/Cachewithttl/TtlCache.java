package Cachewithttl;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// I wanted to make generics case
public class TtlCache<K> {

    private final long ttl;
    private final Map<K, PriorityQueue< Long>>cache;
    private final Object lock=new Object();

    public TtlCache(long ttl) {
        this.ttl = ttl;
        this.cache = new HashMap<>();
    }

    // put operations
    public void put(K key){
        synchronized (lock){
            long now=System.currentTimeMillis();
            PriorityQueue<Long>timestamps=cache.getOrDefault(key,new PriorityQueue<Long>());
            timestamps.add(now);
            cache.put(key,timestamps);
        }
    }

    // get operations
    public Map<K, Integer>  get(){
        synchronized (lock){
            long now=System.currentTimeMillis();
            // making map to store keys and their frequency
            Map<K, Integer> result = new HashMap<>();

            // check and remove the stale key present in cache
            for(K key:cache.keySet()){
                PriorityQueue<Long>timestamp=cache.get(key);
                while(!timestamp.isEmpty() && timestamp.peek()+ttl <now){
                    timestamp.poll();
                }
                if(!timestamp.isEmpty()){
                    result.put(key, timestamp.size());
                }
            }
            return result;


        }
    }



}

