package com.tiggermetrics.accoutservice.dao.impl;

import com.tiggermetrics.accoutservice.dao.inf.SavingDao;
import com.tiggermetrics.accoutservice.dao.mapper.SavingMapper;
import com.tiggermetrics.accoutservice.dao.model.SavingPo;
import com.tiggermetrics.accoutservice.model.Saving;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class SavingDaoImpl implements SavingDao {

    @Autowired
    private SavingMapper savingMapper;

    @Override
    public void insertSaving(List<Saving> savings, String accountId) {
        if (CollectionUtils.isEmpty(savings)) {
            return;
        }
        List<SavingPo> savingPos = savings.stream().map(item -> {
            if (StringUtils.isEmpty(item.getUuid())) {
                item.setUuid(UUID.randomUUID().toString());
            }
            return new SavingPo(item, accountId);
        }).collect(Collectors.toList());
        savingMapper.batchInsert(savingPos);
    }

    @Override
    public void updateSaving(List<Saving> savings, String accountId) {
        if (CollectionUtils.isEmpty(savings)) {
            return;
        }
        List<SavingPo> savingPos = savings.stream().map(item -> new SavingPo(item, accountId)).collect(Collectors.toList());
        savingMapper.batchUpdate(savingPos);
    }

    @Override
    public List<Saving> queryByAccountId(String accountId) {
        List<SavingPo> savingPos = savingMapper.selectByAccountId(accountId);
        return savingPos.stream().map(item -> item.toMo()).collect(Collectors.toList());
    }
}
