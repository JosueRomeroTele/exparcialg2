package com.example.exparcialg2.Dtos;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/imagenesexam/**")
                //Aqui coloca la carpeta donde estaran las imagenes
                .addResourceLocations("file:C:/Users/User/imagenesexam/");
    }

}