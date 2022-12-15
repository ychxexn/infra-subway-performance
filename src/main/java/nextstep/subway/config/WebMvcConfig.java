package nextstep.subway.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public static final String PREFIX_STATIC_RESOURCES = "/resources";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(60 * 60 * 24 * 365);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        Filter etagHeaderFilter = new ShallowEtagHeaderFilter();
        registrationBean.setFilter(etagHeaderFilter);
        registrationBean.addUrlPatterns(PREFIX_STATIC_RESOURCES + "/*");
        return registrationBean;
    }
}
