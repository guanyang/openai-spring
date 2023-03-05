package org.gy.framework.openai.properties;

import lombok.Data;
import org.gy.framework.openai.utils.OpenAiUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "openai")
public class OpenAiProperties implements InitializingBean {

    // 秘钥
    String token;
    // 超时时间
    Integer timeout;

    // 设置属性时同时设置给OpenAiUtils
    @Override
    public void afterPropertiesSet() throws Exception {
        OpenAiUtils.OPENAPI_TOKEN = token;
        OpenAiUtils.TIMEOUT = timeout;
    }
}
