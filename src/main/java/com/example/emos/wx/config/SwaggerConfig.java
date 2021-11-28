package com.example.emos.wx.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
//        ApliInfoBuilder 用于在 Swagger 界面上添加各种信息
        ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("EMOS在线办公系统");
        ApiInfo apiInfo = builder.build();
        docket.apiInfo(apiInfo);
//        ApiSelectorBuilder 用来设置哪些类中的方法会生成到 REST API 中
        ApiSelectorBuilder selectorBuilder = docket.select();
        selectorBuilder.paths(PathSelectors.any());
//        使用@apioperation的方法会被提取到 REST API 中
        selectorBuilder.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
        docket = selectorBuilder.build();

        /*
         * 下面的语句时开启对 JWT 的支持，当用户用 Swagger 调用收到 JWT 认证保护的方法，
         * 必须要先提交参数（例如：令牌)
         * */
        // 存储用户必须提交的参数
        List<ApiKey> apikey = new ArrayList();
        // 规定用户需要输入什么参数
        apikey.add(new ApiKey("token","token","header"));
        docket.securitySchemes(apikey);
        // 如果用户 JWT 认证通过，则在 Swagger 中全局有效
        AuthorizationScope scope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] scopeArray = {scope};
        // 存储令牌 和 作用域
        SecurityReference reference = new SecurityReference("token", scopeArray);
        List reflist=new ArrayList();
        reflist.add(reference);
        SecurityContext context = SecurityContext.builder().securityReferences(reflist).build();
        List cxtList =new ArrayList();
        cxtList.add(context);
        docket.securityContexts(cxtList);
        return docket;
    }
}
