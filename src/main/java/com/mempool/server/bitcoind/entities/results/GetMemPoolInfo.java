package com.mempool.server.bitcoind.entities.results;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class GetMemPoolInfo extends BitcoindResult {

	@JsonProperty("result")
	private GetMemPoolInfoData getMemPoolInfoData;

}
