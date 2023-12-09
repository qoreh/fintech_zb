package com.zerobase.fintech.config;

import com.zerobase.fintech.converter.StringToOrganizationCodeConverter;
import com.zerobase.fintech.converter.StringToProductCodeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToOrganizationCodeConverter());
        registry.addConverter(new StringToProductCodeConverter());
    }
}
