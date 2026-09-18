package ee.sandra.veebipood.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Random getRandom() {
        return new Random(); // luua suvalisi numbreid
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
