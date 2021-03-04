package main.repositories;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

import java.util.HashMap;

@Component
public class RedisRepository {

    private final String HOST_ADDRESS = "127.0.0.1";
    private final int PORT_NUMBER = 6379;

    private Jedis jedis;

    public RedisRepository() {
        jedis = new Jedis(HOST_ADDRESS, PORT_NUMBER);
    }

    public HashMap<String, Integer> getCities() {
        HashMap<String, Integer> resultMap = new HashMap<>();
        ScanResult<Tuple> scanResult = jedis.zscan("cities:1", String.valueOf(0));
        scanResult.getResult().stream().forEach(o -> resultMap.put(o.getElement(), (int)o.getScore()));
        return resultMap;
    }

}
