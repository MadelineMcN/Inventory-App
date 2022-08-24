package com.example.inventoryappmcneeleymadeline;
import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//adding needed annotation
@androidx.room.Dao
public interface Dao {


    //adding data to the database
    @Insert
    void insert (ItemModal model);

    //update
    @Update
    void update(ItemModal model);

    //delete
    @Delete
    void delete(ItemModal model);

    //to delete all items from database
    @Query("DELETE FROM item_table")
    void deleteAllItems();

    //read all items from database
    @Query("SELECT * FROM item_table ORDER BY itemName ASC")
    LiveData<List<ItemModal>> getAllItems();

}
