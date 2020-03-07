package com.xxx.ordersystem.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/25 15:02
 * @Version: 1.0
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {
    @Value("mpAppId")
    private String mpAppId;
    @Value("mpAppSecret")
    private String mpAppSecret;
}
