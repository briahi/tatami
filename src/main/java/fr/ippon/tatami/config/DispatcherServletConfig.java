package fr.ippon.tatami.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.site.SitePreferenceHandlerInterceptor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;
import org.springframework.web.servlet.view.*;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Configuration
@ComponentScan("fr.ippon.tatami.web")
@EnableWebMvc
@PropertySource(value = "classpath:/META-INF/tatami/tatami.properties")
public class DispatcherServletConfig extends WebMvcConfigurerAdapter {

    @Inject
    private Environment env;

    // Any other way to inject a Properties object containing all the properties?
    @Bean
    public Properties applicationProps() {
        Properties props = new Properties();
        props.put("tatami.version", this.env.getProperty("tatami.version"));
        return props;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/username").setViewName("username");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/404-error").setViewName("404-error");
        registry.addViewController("/500-error").setViewName("500-error");
    }

    @Bean
    public ViewResolver ContentNegotiatingViewResolver() {
        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();

        Map<String, String> mediaTypes = new HashMap<String, String>();
        mediaTypes.put("html", "text/html");
        mediaTypes.put("json", "application/json");
        viewResolver.setMediaTypes(mediaTypes);

        List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
        viewResolvers.add(new BeanNameViewResolver());

        //UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
           CustomViewResolver customViewResolver=new CustomViewResolver();

        customViewResolver.setViewClass(JstlView.class);

        viewResolvers.add(customViewResolver);
         viewResolver.setViewResolvers(viewResolvers);

        List<View> defaultViews = new ArrayList<View>();
        defaultViews.add(new MappingJacksonJsonView());
        viewResolver.setDefaultViews(defaultViews);

        return viewResolver;
    }

    @Bean
    public SessionLocaleResolver localeChangeInterceptor() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        Locale locale = new Locale("en");
        resolver.setDefaultLocale(locale);
        return resolver;
    }

    @Bean
    public ControllerClassNameHandlerMapping controllerClassNameHandlerMapping() {
        ControllerClassNameHandlerMapping mapping = new ControllerClassNameHandlerMapping();
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        mapping.setInterceptors(new Object[]{localeChangeInterceptor});
        return mapping;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/WEB-INF/messages/messages");
        messageSource.setCacheSeconds(1);
        return messageSource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DeviceResolverHandlerInterceptor());
        registry.addInterceptor(new SitePreferenceHandlerInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/" + this.env.getProperty("tatami.version") + "/**")
                .addResourceLocations("/public-resources/", "classpath:/META-INF/public-web-resources/")
                .setCachePeriod(31556926);
    }

}
