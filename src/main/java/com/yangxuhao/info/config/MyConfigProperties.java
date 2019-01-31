package com.yangxuhao.info.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yangxuhao
 * @date 2019-01-31 11:28.
 */
@Data
@ConfigurationProperties(prefix = "yang.data")
public class MyConfigProperties {
    private String file;
}
