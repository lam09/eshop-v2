package com.lam.eshopv2.config;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */
import com.lam.eshopv2.interceptor.UrlInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // Tải file: validation.properties
        messageSource.setBasename("classpath:validation");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public ClassLoaderTemplateResolver yourTemplateResolver() {
        ClassLoaderTemplateResolver yourTemplateResolver = new ClassLoaderTemplateResolver();
        yourTemplateResolver.setPrefix("templates2/");
        yourTemplateResolver.setSuffix(".html");
        yourTemplateResolver.setTemplateMode(TemplateMode.HTML);
        yourTemplateResolver.setCharacterEncoding("UTF-8");
        yourTemplateResolver.setOrder(0);  // this is iportant. This way spring
        //boot will listen to both places 0
        //and 1
        yourTemplateResolver.setCheckExistence(true);

        return yourTemplateResolver;
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UrlInterceptor());
    }
}