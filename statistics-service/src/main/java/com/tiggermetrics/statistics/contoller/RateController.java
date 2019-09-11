package com.tiggermetrics.statistics.contoller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class RateController {

    @Value("${usd.rmb}")
    private String usdToRmb;

    @Value("${eur.rmb}")
    private String eurToRmb;

    @RequestMapping("/usd/rmb")
    public String usdRate() {
        return this.usdToRmb;
    }

    @RequestMapping("/eur/rmb")
    public String eurRate() {
        return this.eurToRmb;
    }
}
