package com.tiggermetrics.accoutservice.dao.mapper;

import com.tiggermetrics.accoutservice.dao.model.AccountPo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {

    int insert(AccountPo accountPo);

    int delete(String uuid);

    AccountPo selectByPrimaryKey(String uuid);

    List<AccountPo> selectAll();

    int update(AccountPo accountPo);

}
