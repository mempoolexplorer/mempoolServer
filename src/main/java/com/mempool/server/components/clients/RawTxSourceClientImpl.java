package com.mempool.server.components.clients;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mempool.server.properties.RawTxSourceProperties;

@Component
public class RawTxSourceClientImpl implements RawTxSourceClient {

	@Autowired
	@Qualifier("rawTxSourceClient")
	RestTemplate restTemplate;

	@Autowired
	RawTxSourceProperties rawTxSourceProperties;

	@Override
	public List<String> getRawTransactions() {
		return Arrays.asList(restTemplate.getForEntity(rawTxSourceProperties.getUrl(), String[].class).getBody());
	}

}
