package com.mempool.server.components.clients;

import java.util.List;

public interface RawTxSourceClient {
	List<String> getRawTransactions();
}
