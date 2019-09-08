package com.tiggermetrics.accoutservice.dao.inf;

import com.tiggermetrics.accoutservice.model.Item;
import com.tiggermetrics.accoutservice.model.ItemType;

import java.util.List;

public interface ItemDao {

    void insertItem(List<Item> items, String accountId, ItemType itemType);

    List<Item> queryByAccountId(String accountId);
}
