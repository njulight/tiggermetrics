package com.tiggermetrics.accoutservice.dao.inf;

import com.tiggermetrics.accoutservice.model.Saving;

import java.util.List;

public interface SavingDao {

    void insertSaving(List<Saving> savings, String accountId);

    void updateSaving(List<Saving> savings, String accountId);

    List<Saving> queryByAccountId(String accountId);

    void deleteByAccountId(String accountId);
}
