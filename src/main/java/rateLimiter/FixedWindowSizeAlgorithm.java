package rateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowSizeAlgorithm implements RateLimiter{

    private Long limit;
    private Long windowSize;
    private ConcurrentHashMap<String,Long>windowStartTimes;
    private ConcurrentHashMap<String, AtomicInteger>requestCounts;
    public FixedWindowSizeAlgorithm(Long limit, Long windowSize) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.requestCounts = new ConcurrentHashMap<>();
        this.windowStartTimes = new ConcurrentHashMap<>();
    }

    @Override
    public Boolean allowRequest(String userId) {

        Long currentTime=System.currentTimeMillis();
        windowStartTimes.putIfAbsent(userId,currentTime);
        requestCounts.putIfAbsent(userId,new AtomicInteger(0));

        // check the current userId Request is valid or not
        if(currentTime-windowStartTimes.get(userId)>windowSize){
            windowStartTimes.put(userId,currentTime);
            requestCounts.put(userId,new AtomicInteger(1));
            return true;

        }
        else{
            if(requestCounts.get(userId).incrementAndGet()<=limit){
                return true;
            }
            return false;
        }
    }
}
