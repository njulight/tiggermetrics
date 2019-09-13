package com.tiggermetrics.statistics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class Rate {

    @Value("${usd.rmb}")
    private String usdToRmb;

    @Value("${eur.rmb}")
    private String eurToRmb;

    public String getUsdToRmb() {
        return usdToRmb;
    }

    public String getEurToRmb() {
        return eurToRmb;
    }
}
