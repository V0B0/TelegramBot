package telegramversionfive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelegramVersionFiveApplication {
    private static final Logger log = LoggerFactory.getLogger(TelegramVersionFiveApplication.class);

    public static void main(String[] args) {
        log.info("inner main()");
        SpringApplication.run(TelegramVersionFiveApplication.class, args);
        log.info("out main()");
    }

}
