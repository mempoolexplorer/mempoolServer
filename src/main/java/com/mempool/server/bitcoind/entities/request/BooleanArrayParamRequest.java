package com.mempool.server.bitcoind.entities.request;

import java.util.List;

public class BooleanArrayParamRequest extends Request {

	private List<Boolean> params;// Esto es un array de objetos, pero de momento cuela

	public List<Boolean> getParams() {
		return params;
	}

	public void setParams(List<Boolean> params) {
		this.params = params;
	}

}
