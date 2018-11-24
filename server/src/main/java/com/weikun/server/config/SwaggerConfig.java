package com.weikun.server.config;

import com.google.common.base.Predicate;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * �����ߣ�weikun��YST��   ���ڣ�2018/7/19
 * ˵˵���ܣ�
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.declaringClass();
                if (declaringClass == BasicErrorController.class)// �ų�
                    return false;
                if(declaringClass.isAnnotationPresent(RestController.class)) // ��ע�����
                    return true;
                if(input.isAnnotatedWith(ResponseBody.class)) // ��ע��ķ���
                    return true;
                return false;
            }
        };
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(predicate)
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("�ҵ�api�ĵ�")//�����
                .version("1.0")//�汾
                .licenseUrl("http://www.papaok.org")
                .license("��������ʥͨ����������޹�˾")
                .build();
    }
}
