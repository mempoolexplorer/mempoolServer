package com.mempool.server;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.mempool.server.components.clients.BitcoindClientResponseErrorHandler;
import com.mempool.server.properties.BitcoindProperties;


@SpringBootApplication
//@RefreshScope
//@EnableFeignClients
//@EnableCircuitBreaker
public class MempoolServerApplication {

	@Autowired
	private BitcoindProperties bitcoindProperties;

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(MempoolServerApplication.class, args);
	}

	public static void exit() {
		SpringApplication.exit(context, new ExitCodeGenerator() {
			@Override
			public int getExitCode() {
				// return the error code
				return 1;
			}
		});
	}

	@Bean
	public RestTemplate bitcoindClient(RestTemplateBuilder restTemplateBuilder)
			throws NumberFormatException, URISyntaxException {
		return restTemplateBuilder.basicAuthentication(bitcoindProperties.getUser(), bitcoindProperties.getPassword())
				.additionalMessageConverters(new MappingJackson2HttpMessageConverter())
				.rootUri(UriComponentsBuilder.newInstance().scheme("http").host(bitcoindProperties.getHost())
						.port(Integer.valueOf(bitcoindProperties.getRpcPort())).toUriString())
				.errorHandler(new BitcoindClientResponseErrorHandler()).build();
	}

	@Bean
	public RestTemplate rawTxSourceClient(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.additionalMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}
}
