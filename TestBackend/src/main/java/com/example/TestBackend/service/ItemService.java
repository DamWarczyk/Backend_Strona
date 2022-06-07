package com.example.TestBackend.service;
import com.example.TestBackend.exepction.UsernNotFoundException;
import com.example.TestBackend.model.Item;
import com.example.TestBackend.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private  final ItemRepo itemRepo;

    @Autowired
    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public Item addItem(Item item){
        return  itemRepo.save(item);
    }

    public List<Item> findAllItem(){return itemRepo.findAll();}

    public Item updateItem(Item item){return itemRepo.save(item);}

    public Item findItemById(Long id){
        return  itemRepo.findItemById(id).orElseThrow(() -> new UsernNotFoundException("User by id: " + id + "Not found"));
    }

    public void deleteItem(Long id){itemRepo.deleteItemtById(id);}
}
