package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

@SpringBootApplication
public class Main implements ApplicationRunner {

    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Jedis jedis = new Jedis();
        ScanResult<Tuple> scanResult = jedis.zscan("cities:1", String.valueOf(0));
        scanResult.getResult().stream().forEach(o -> System.out.println(o.getScore() + "," + o.getElement()));

    }

}