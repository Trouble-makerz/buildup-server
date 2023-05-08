package tm.project.buildup.global.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("buildup")
                .version("1.0")
                .description("buildup swagger");

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(info);
    }
}