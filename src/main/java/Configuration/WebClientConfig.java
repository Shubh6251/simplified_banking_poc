package Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Configuration
    public class WebClientConfig {

        @Value("${system2.base-url}")
        private String system2BaseUrl;

        @Bean
        public WebClient system2Client() {
            return WebClient.builder()
                    .baseUrl(system2BaseUrl)
                    .build();
        }
    }


