package com.tiggermetrics.accoutservice.dao.impl;

import com.tiggermetrics.accoutservice.dao.inf.ItemDao;
import com.tiggermetrics.accoutservice.dao.mapper.ItemMapper;
import com.tiggermetrics.accoutservice.dao.model.ItemPo;
import com.tiggermetrics.accoutservice.model.Item;
import com.tiggermetrics.accoutservice.model.ItemType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ItemDaoImpl implements ItemDao {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public void insertItem(List<Item> items, String accountId, ItemType itemType) {
        if (CollectionUtils.isEmpty(items)) {
            return;
        }
        List<ItemPo> itemPos = items.stream().map(item -> {
            if (StringUtils.isEmpty(item.getUuid())) {
                item.setUuid(UUID.randomUUID().toString());
            }
            return new ItemPo(item, accountId, itemType);
        }).collect(Collectors.toList());
        itemMapper.batchInsert(itemPos);
    }

    @Override
    public List<Item> queryByAccountId(String accountId) {
        List<ItemPo> itemPos = itemMapper.selectByAccountId(accountId);
        return itemPos.stream().map(item -> item.toMo()).collect(Collectors.toList());
    }

    @Override
    public void deleteByAccountId(String accountId) {
        itemMapper.deleteByAccountId(accountId);
    }
}
