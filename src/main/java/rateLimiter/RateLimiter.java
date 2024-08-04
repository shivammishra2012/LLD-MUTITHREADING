package rateLimiter;

public interface RateLimiter {
    Boolean allowRequest(String userId);
}
