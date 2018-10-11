package ecity_power;

import ecity_power.config.UniqueNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(nameGenerator = UniqueNameGenerator.class)
public class Application {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        }catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        SpringApplication.run(Application.class, args);
    }
}
