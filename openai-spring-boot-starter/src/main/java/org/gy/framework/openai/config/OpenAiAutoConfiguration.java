package org.gy.framework.openai.config;

import org.gy.framework.openai.properties.OpenAiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类
 */
@Configuration
@EnableConfigurationProperties(OpenAiProperties.class)
public class OpenAiAutoConfiguration {
}
