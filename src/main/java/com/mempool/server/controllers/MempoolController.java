package com.mempool.server.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mempool.server.bitcoind.entities.results.GetMemPoolInfoData;
import com.mempool.server.components.clients.BitcoindClient;
import com.mempool.server.components.clients.RawTxSourceClient;
import com.mempool.server.controllers.results.SendRawTxsResult;

@RestController
@RequestMapping("/memPool")
public class MempoolController {

	@Autowired
	private BitcoindClient bitcoindClient;

	@Autowired
	private RawTxSourceClient rawTxSourceClient;

	@GetMapping("/info")
	public GetMemPoolInfoData getMemPoolInfo() {
		return bitcoindClient.getMemPoolInfo().getGetMemPoolInfoData();
	}

	@GetMapping("/rawTxs")
	public List<String> getRawTxs() {
		return bitcoindClient.getRawMemPoolNonVerbose().getTrxHashList().stream()
				.map(txId -> bitcoindClient.getRawTransaction(txId)).filter(rawTxRes -> rawTxRes.getError() == null)
				.map(rawTxRes -> rawTxRes.getRawTx()).collect(Collectors.toList());
	}

	@GetMapping("/sendRawTxs")
	public SendRawTxsResult sendRawTxs() {
		SendRawTxsResult sendRes = new SendRawTxsResult();
		rawTxSourceClient.getRawTransactions().stream().map(rawTx -> bitcoindClient.sendRawTransaction(rawTx))
				.forEach(res -> sendRes.add(res));
		return sendRes;
	}
}
