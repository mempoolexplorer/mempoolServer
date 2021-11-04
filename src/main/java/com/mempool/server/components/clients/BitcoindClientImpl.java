package com.mempool.server.components.clients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mempool.server.bitcoind.entities.request.BooleanArrayParamRequest;
import com.mempool.server.bitcoind.entities.request.ObjectArrayParamRequest;
import com.mempool.server.bitcoind.entities.request.StringArrayParamRequest;
import com.mempool.server.bitcoind.entities.results.GetMemPoolInfo;
import com.mempool.server.bitcoind.entities.results.GetRawMemPoolNonVerbose;
import com.mempool.server.bitcoind.entities.results.GetRawTransactionResult;
import com.mempool.server.bitcoind.entities.results.SendRawTransactionResult;

@Component
public class BitcoindClientImpl implements BitcoindClient {

	@Autowired
	@Qualifier("bitcoindClient")
	private RestTemplate restTemplate;


	@Override
	public GetRawMemPoolNonVerbose getRawMemPoolNonVerbose() {
		BooleanArrayParamRequest boolParams = new BooleanArrayParamRequest();
		boolParams.setId("1");
		boolParams.setMethod("getrawmempool");
		List<Boolean> params = new ArrayList<Boolean>();
		params.add(false);
		boolParams.setParams(params);

		return restTemplate.postForObject("/", boolParams, GetRawMemPoolNonVerbose.class);
	}

	@Override
	public GetMemPoolInfo getMemPoolInfo() {
		StringArrayParamRequest stringParams = new StringArrayParamRequest();

		stringParams.setId("2");
		stringParams.setMethod("getmempoolinfo");
		stringParams.setParams(new ArrayList<String>());

		return restTemplate.postForObject("/", stringParams, GetMemPoolInfo.class);
	}

	@Override
	public GetRawTransactionResult getRawTransaction(String txId) {
		ObjectArrayParamRequest objectParams = new ObjectArrayParamRequest();

		objectParams.setId("3");
		objectParams.setMethod("getrawtransaction");
		List<Object> params = new ArrayList<>();
		params.add(txId);
		params.add(Boolean.valueOf(false));
		objectParams.setParams(params);
		return restTemplate.postForObject("/", objectParams, GetRawTransactionResult.class);
	}

	@Override
	public SendRawTransactionResult sendRawTransaction(String rawTx) {
		ObjectArrayParamRequest objectParams = new ObjectArrayParamRequest();
		objectParams.setId("4");
		objectParams.setMethod("sendrawtransaction");
		List<Object> params = new ArrayList<>();
		params.add(rawTx);
		objectParams.setParams(params);
		return restTemplate.postForObject("/", objectParams, SendRawTransactionResult.class);
	}

}
