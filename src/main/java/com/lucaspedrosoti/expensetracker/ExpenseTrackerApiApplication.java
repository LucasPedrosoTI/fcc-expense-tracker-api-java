package com.lucaspedrosoti.expensetracker;

import com.lucaspedrosoti.expensetracker.filters.AuthFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class ExpenseTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApiApplication.class, args);
	}

	@Bean // CORS CONFIGURATION
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration config = new CorsConfiguration();

		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");

		registrationBean.setFilter(new CorsFilter(source));
		registrationBean.setOrder(0);

		return registrationBean;
	}

	@Bean // AUTHENTICATION VERIFICATION
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {

		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();

		AuthFilter authFilter = new AuthFilter();

		registrationBean.setFilter(authFilter);
		registrationBean.addUrlPatterns("/api/categories/*");

		return registrationBean;
	}

}
