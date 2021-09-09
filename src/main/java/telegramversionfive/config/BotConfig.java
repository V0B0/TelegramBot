package telegramversionfive.config;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import telegramversionfive.entity.Bot;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegram")
@ComponentScan("telegramversionfive")
public class BotConfig {
    private static final Logger log = LoggerFactory.getLogger(BotConfig.class);

    private String botUserName;
    private String botToken;
    private String webHookPath;

    @Bean
    public Bot bot() {
        log.info("inner bot() bean");
        DefaultBotOptions options = new DefaultBotOptions();
        Bot bot = new Bot(options);
        log.info("setBotUserName "+botUserName);
        bot.setBotUserName(botUserName);
        log.info("setBotToken "+botToken);
        bot.setBotToken(botToken);
        log.info("setWebHookPath "+webHookPath);
        bot.setWebHookPath(webHookPath);
        return bot;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource resource =
                new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }
}
