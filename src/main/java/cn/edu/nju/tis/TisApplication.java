package cn.edu.nju.tis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class TisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TisApplication.class, args);
    }
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation("/Users/cruckie/Desktop/graduation/code/dis");
        return factory.createMultipartConfig();
    }
}