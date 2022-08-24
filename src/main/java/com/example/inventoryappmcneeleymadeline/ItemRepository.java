package com.example.inventoryappmcneeleymadeline;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataKt;

import java.util.List;

public class ItemRepository {

    //creates variable for dao and list for all items
    private Dao dao;
    private LiveData<List<ItemModal>> allItems;

    //creates constructor and passes variables to it
    public ItemRepository(Application application) {
        ItemDatabase database = ItemDatabase.getInstance(application);
        dao = database.Dao();
        allItems = dao.getAllItems();
    }

    //creates a method for inserting data into database
    public void insert(ItemModal model) {
        new InsertItemAsyncTask(dao).execute(model);
    }

    //creates a method for updating data
    public void update(ItemModal model) {
        new UpdateItemAsyncTask(dao).execute(model);
    }

    //creates a method for deleting data
    public void delete(ItemModal model) {
        new DeleteItemAsyncTask(dao).execute(model);
    }

    //creates a method for deleting all items
    public void deleteAllItems() {
        new DeleteAllItemsAsyncTask(dao).execute();
    }

    //Creates a method to read items
    public LiveData<List<ItemModal>> getAllItems() {
        return allItems;
    }

    //async task for inserting new items
    private static class InsertItemAsyncTask extends AsyncTask<ItemModal, Void, Void> {
        private Dao dao;

        private InsertItemAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(ItemModal... model) {
            dao.insert(model[0]);
            return null;
        }
    }

    //async task for updating new items
    private static class UpdateItemAsyncTask extends AsyncTask<ItemModal, Void, Void> {
        private Dao dao;

        private UpdateItemAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(ItemModal... models) {
            dao.update(models[0]);
            return null;
        }
    }

    //async task to delete an item
    private static class DeleteItemAsyncTask extends AsyncTask<ItemModal, Void, Void> {
        private Dao dao;

        private DeleteItemAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ItemModal... models) {
            dao.delete(models[0]);
            return null;
        }
    }

    //async task to delete all items
    private static class DeleteAllItemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;
        private DeleteAllItemsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllItems();
            return null;
        }
    }

}
