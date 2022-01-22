package alexandr.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.PrintStream;

@ConfigurationProperties(prefix = "path")
@Configuration
@Setter
@Getter
public class PropsConfig {
    private String csvFilepath;

    @Bean
    public PrintStream out () {
        return new PrintStream(System.out);
    }
}


