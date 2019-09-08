package com.tiggermetrics.statistics.service.inf;

import com.tiggermetrics.statistics.model.AccountView;

public interface StatisticsService {

    AccountView getCurrentAccount(String uuid);
}
