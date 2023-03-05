package org.gy.framework.openai.properties;

import java.util.Objects;
import java.util.Optional;
import lombok.Data;
import org.gy.framework.openai.utils.OpenAiUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

@Data
@ConfigurationProperties(prefix = "openai")
public class OpenAiProperties implements InitializingBean {

    public static final String TOKEN_KEY = "OPENAI_TOKEN";

    // 秘钥
    String token;
    // 超时时间
    Integer timeout;

    // 设置属性时同时设置给OpenAiUtils
    @Override
    public void afterPropertiesSet() throws Exception {
        token = Optional.ofNullable(token).filter(StringUtils::hasText).orElseGet(() -> getProperty(TOKEN_KEY));
        OpenAiUtils.OPENAPI_TOKEN = Objects.requireNonNull(token, () -> "openai token is null");
        OpenAiUtils.TIMEOUT = timeout;
    }

    private static String getProperty(String key) {
        String value = System.getProperty(key);
        return StringUtils.hasText(value) ? value : System.getenv(key);
    }
}
