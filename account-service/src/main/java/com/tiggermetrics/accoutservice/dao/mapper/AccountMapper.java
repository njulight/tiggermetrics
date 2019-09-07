package com.tiggermetrics.accoutservice.dao.mapper;

import com.tiggermetrics.accoutservice.dao.model.AccountPo;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {

    int insert(AccountPo accountPo);

    AccountPo selectByPrimaryKey(String uuid);

    int update(AccountPo accountPo);

}
