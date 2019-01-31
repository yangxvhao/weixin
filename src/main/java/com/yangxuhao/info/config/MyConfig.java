package com.yangxuhao.info.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxuhao
 * @date 2019-01-31 11:48.
 */
@Configuration
@EnableConfigurationProperties(MyConfigProperties.class)
public class MyConfig {
}
