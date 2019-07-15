package com.weichuang.dao;

import com.weichuang.pojo.Item;

import java.util.List;

public interface ItemMapper {

    List<Item> getAllItem();

    Item getItemById(Integer id);

    void updateItem(Item item);

    void deleteItems(String[] ids);

    void addItem(Item item);
}
