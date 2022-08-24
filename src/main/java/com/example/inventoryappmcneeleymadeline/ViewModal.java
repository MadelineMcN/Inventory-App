package com.example.inventoryappmcneeleymadeline;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class ViewModal extends AndroidViewModel {

    //creates a variable for item repository
    private ItemRepository repository;

    //creates varaible for all live data items
    private LiveData<List<ItemModal>> allItems;

    //creates view modal constructor
    public ViewModal (@NonNull Application application) {
        super(application);
        repository = new ItemRepository(application);
        allItems = repository.getAllItems();
    }

    //method to insert data in repository
    public void insert(ItemModal model) {
        repository.insert(model);
    }

    //method to update data in repository
    public void update(ItemModal model) {
        repository.update(model);
    }

    //method to delete data in repository
    public void delete(ItemModal model) {
        repository.delete(model);
    }

    //method to delete all items in list
    public void deteleAllItems() {
        repository.deleteAllItems();
    }

    //method to get all items in list
    public LiveData<List<ItemModal>> getAllItems() {
        return allItems;
    }




}
