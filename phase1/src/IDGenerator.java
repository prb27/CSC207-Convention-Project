import java.time.Instant;

public class IDGenerator {
    public IDGenerator(){}

    public String id(String identifier){
        long timestamp = System.currentTimeMillis();
        return identifier + timestamp;
    }
}
