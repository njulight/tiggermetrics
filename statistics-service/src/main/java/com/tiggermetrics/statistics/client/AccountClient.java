package com.tiggermetrics.statistics.client;

import com.tiggermetrics.statistics.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name= "accout-service", fallback = AccountClientFallback.class)
public interface AccountClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}")
    Account getCurrentAccount(@PathVariable String uuid);
}
