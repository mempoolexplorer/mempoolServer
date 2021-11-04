package com.mempool.server.controllers.results;

import java.util.ArrayList;
import java.util.List;

import com.mempool.server.bitcoind.entities.results.SendRawTransactionResult;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SendRawTxsResult {
	private Integer numAlreadyInBlockChain = Integer.valueOf(0);
	private Integer numSent = Integer.valueOf(0);
	private Integer numOthers = Integer.valueOf(0);
	private List<SendRawTransactionResult> alreadyInBlockChainList = new ArrayList<>();
	private List<SendRawTransactionResult> sentList = new ArrayList<>();
	private List<SendRawTransactionResult> othersList = new ArrayList<>();

	public void add(SendRawTransactionResult res) {
		if (null == res.getError()) {
			numSent++;
			sentList.add(res);
		} else if (res.getError().getCode() == -27) {
			numAlreadyInBlockChain++;
			alreadyInBlockChainList.add(res);
		} else {
			numOthers++;
			othersList.add(res);
		}
	}
}
