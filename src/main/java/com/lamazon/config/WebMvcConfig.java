package com.lamazon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/*
 * DispatcherServlet용 애플리케이션 컨텍스트
 */

@Configuration
@EnableWebMvc
@ComponentScan("com.lamazon")
//@Import({RootConfig.class})
public class WebMvcConfig extends WebMvcConfigurerAdapter{

	/*
	//ViewResolver 설정 - JSP
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		super.configureViewResolvers(registry);

		//JSP용 ViewResolver로 설정 - /WEB-INF 디렉터리 바로 아래에 저장된 JSP파일이 뷰로 취급된다.
		//registry.jsp().prefix("/WEB-INF/views/");
		//registry.jsp();

		registry.tiles();
	}
	*/

    // ViewResolver 설정 - Tiles
    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] {
        		                                        "/WEB-INF/tiles/tiles.xml"
        		                                    //  , "/WEB-INF/tiles/tiles_admin.xml"
        		                                    });
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

    // ViewResolver 설정 - Tiles용
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }

    //웹애플리케이션의 문서 루트 이하의 파일에 접근하기위한 설정
    //요청 경로에 대응하는 핸들러 메서드가 존재하지 않는 경우에는 기본 서블릿에 요청을 전송
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    	configurer.enable();
    }

    /**
     * CSS / JS / IMAGE 등 정적인 자원들 접근할수있게 설정
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	//resources폴더 하위 경로 전체 허용
        registry.addResourceHandler("/**").addResourceLocations("/resources/");

    	//개별설정
        /*
    	registry.addResourceHandler("/admin/**").addResourceLocations("/resources/admin/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/");
        registry.addResourceHandler("/image/**").addResourceLocations("/resources/image/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        */
    }

    //비동기 실행기능 자동으로 활성화
    /*
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    	//타임아웃시간설정
    	configurer.setDefaultTimeout(5000);

    	//스레드 풀을 이용하도록 커스터마이징한 TaskExecutor를 설정한다.
    	configurer.setTaskExecutor(mvcTaskExecutor());
    }
    */

    @Bean
    public TaskExecutor mvcTaskExecutor() {
    	ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    	executor.setCorePoolSize(5);
    	executor.setMaxPoolSize(10);
    	executor.setQueueCapacity(25);
    	return executor;
    }


	/*
	* 컨트롤러 없이 바로 뷰로갈 경우 사용
	*/
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//일반
		//registry.addViewController("/").setViewName("/home/index");

		//리다이렉트만 하는 컨트롤러
		//registry.addRedirectViewController("/", "index");

		//상태코드를 반환만 하는 컨트롤러
		//registry.addStatusController("/", HttpStatus.ACCEPTED);
	}

	/*
	 * HttpMessageConvertor의 커스터마이징
	 * HttpMessageConverter의 설정을 바꾸고 싶거나 직접 확장한 HttpMessageConverter의 구현 클래스를
	 * 적용해야 하는 경우에는 다음과 같은 방법으로 빈을 정의할 수 있다.
	 *
	 * 기본 HttpMessageConverter를 적용하고 싶지 않다면 extendMessageConverters 메서드가 아닌
	 * configureMessageConverters 메서드를 오버라이드 한다.
	 */
	/*
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConvertor() {
		return new MappingJackson2HttpMessageConverter(Jackson2ObjectMapperBuilder.json().indentOutput(true).build());
	}

	@Override
	public void extendMessageConverters( List<HttpMessageConverter<?>> converters) {
		converters.add(0, mappingJackson2HttpMessageConvertor());
	}
	*/
}
