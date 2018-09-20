package ecity_power.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class ResourceHandlers implements WebMvcConfigurer {

    @Value("${localStore.MappingPath}")
    private String localStoreMappingPath;

    @Value("${localStore.MappingUrl}")
    private String localStoreMappingUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String[] staticImageMappingPath = { "file:///"+localStoreMappingPath };
        String[] staticWebMappingPath = { "/"};
        registry.addResourceHandler(localStoreMappingUrl+"**").addResourceLocations(staticImageMappingPath);
        registry.addResourceHandler("/**").addResourceLocations(staticWebMappingPath);
    }
}

