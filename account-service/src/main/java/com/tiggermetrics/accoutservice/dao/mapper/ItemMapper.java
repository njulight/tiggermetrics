package com.tiggermetrics.accoutservice.dao.mapper;

import com.tiggermetrics.accoutservice.dao.model.ItemPo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper {

    int insert(ItemPo itemPo);

    int batchInsert(List<ItemPo> itemPos);

    List<ItemPo> selectByAccountId(String accountId);

}
