package com.tiggermetrics.accoutservice.dao.mapper;

import com.tiggermetrics.accoutservice.dao.model.SavingPo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingMapper {

    int insert(SavingPo savingPo);

    int batchInsert(List<SavingPo> savingPos);

    int update(SavingPo savingPo);

    int batchUpdate(List<SavingPo> savingPos);

    List<SavingPo> selectByAccountId(String accountId);

}
