package com.xxx.ordersystem.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/25 14:51
 * @Version: 1.0
 * @Description:
 */
@Component
public class WeChatConfig {
    @Autowired
    WeChatAccountConfig weChatAccountConfig;
    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpDefaultConfigImpl wxMpConfigStorage = new WxMpDefaultConfigImpl();
        wxMpConfigStorage.setAppId(weChatAccountConfig.getMpAppId());
        wxMpConfigStorage.setSecret(weChatAccountConfig.getMpAppSecret());
        return wxMpConfigStorage;
    }
}
