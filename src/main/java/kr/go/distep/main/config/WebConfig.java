package kr.go.distep.main.config;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import kr.go.distep.cmmn.web.UserInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "kr.go.distep")
@PropertySource("classpath:config.properties")  // 설정 파일 명시
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private ServletContext servletContext;
    
    @Autowired
    private UserInterceptor userInterceptor;

    // properties 주입을 위해 필요
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    // 값 정상 주입되는지 확인
    @PostConstruct
    public void init() {
        log.info(">>> 설정된 업로드 경로: {}", uploadPath);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = uploadPath;

        // 슬래시 정리
        if (!path.endsWith("/")) {
            path += "/";
        }

        // OS 구분에 따른 file prefix 설정
        String osPrefix = System.getProperty("os.name").toLowerCase().contains("win") ? "file:///" : "file:";
        String fullResourcePath = osPrefix + path.replace("\\", "/");

        log.info("정적 리소스 핸들러 등록 경로: {}", fullResourcePath);

        registry.addResourceHandler("/upload/**").addResourceLocations(fullResourcePath);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/**")                 // 모든 요청에 적용
                .excludePathPatterns("/static/**", "/css/**", "/js/**", "/images/**"); // 정적 리소스 제외
    }
    
    // 아래는 필요 없으면 생략해도 됨
    @Override public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {}
    @Override public void addCorsMappings(CorsRegistry registry) {}
    @Override public void addFormatters(FormatterRegistry registry) {}
    @Override public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {}
    @Override public void addViewControllers(ViewControllerRegistry registry) {}
    @Override public void configureAsyncSupport(AsyncSupportConfigurer configurer) {}
    @Override public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {}
    @Override public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {}
    @Override public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {}
    @Override public void configurePathMatch(PathMatchConfigurer configurer) {}
    @Override public void configureViewResolvers(ViewResolverRegistry registry) {}
    @Override public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {}
    @Override public MessageCodesResolver getMessageCodesResolver() { return null; }
    @Override public Validator getValidator() { return null; }
}
