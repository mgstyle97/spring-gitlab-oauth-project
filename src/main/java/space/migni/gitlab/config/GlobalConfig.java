package space.migni.gitlab.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import space.migni.gitlab.domain.auth.oauth.properties.GitlabOAuthProperties;

@Configuration
@EnableConfigurationProperties({
    GitlabOAuthProperties.class
})
public class GlobalConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
