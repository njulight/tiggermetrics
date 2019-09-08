package com.tiggermetrics.statistics.contoller;

import com.tiggermetrics.statistics.model.AccountView;
import com.tiggermetrics.statistics.service.inf.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public AccountView getCurrentAccount(@PathVariable String uuid) {
        return statisticsService.getCurrentAccount(uuid);
    }
}
