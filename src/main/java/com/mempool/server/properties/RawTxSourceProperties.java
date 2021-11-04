package com.mempool.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@ConfigurationProperties(prefix = "raw-tx-source")

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RawTxSourceProperties {

	String url;
}
