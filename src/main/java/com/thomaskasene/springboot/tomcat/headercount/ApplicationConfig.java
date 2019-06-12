package com.thomaskasene.springboot.tomcat.headercount;

import org.apache.coyote.AbstractProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApplicationConfig {

    /**
     * A second {@link WebServerFactoryCustomizer} for updating how the Tomcat server is configured. This makes use of
     * {@code server.max-http-header-count}-property to define the maximum allowed number of HTTP headers in incoming
     * requests.
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableTomcatWebServerFactory> tomcatCustomizer(
            @Value("${server.max-http-header-count}") int maxHttpHeaderCount) {

        return webServerFactory -> webServerFactory.addConnectorCustomizers(connector -> {
            ((AbstractProtocol) connector.getProtocolHandler()).setMaxHeaderCount(maxHttpHeaderCount);
        });
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
