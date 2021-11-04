package com.mempool.server.components.clients;

import com.mempool.server.bitcoind.entities.results.GetMemPoolInfo;
import com.mempool.server.bitcoind.entities.results.GetRawMemPoolNonVerbose;
import com.mempool.server.bitcoind.entities.results.GetRawTransactionResult;
import com.mempool.server.bitcoind.entities.results.SendRawTransactionResult;

public interface BitcoindClient {

	GetRawMemPoolNonVerbose getRawMemPoolNonVerbose();

	GetMemPoolInfo getMemPoolInfo();

	GetRawTransactionResult getRawTransaction(String txId);

	SendRawTransactionResult sendRawTransaction(String rawTx);

}