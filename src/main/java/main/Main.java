package main;

import main.repositories.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;

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
    public void run(ApplicationArguments args)  {

        RedisRepository repository = context.getBean("redisRepository", RedisRepository.class);
        HashMap<String, Integer> citiesMap = repository.getCities();
        for(String key: citiesMap.keySet()) {
            System.out.println(key + " : " + citiesMap.get(key));
        }

    }

}