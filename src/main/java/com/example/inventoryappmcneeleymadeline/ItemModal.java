package com.example.inventoryappmcneeleymadeline;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//setting table name
@Entity(tableName = "item_table")

public class ItemModal {

    //auto increment id for each item
    @PrimaryKey(autoGenerate = true)

    //variable for id
    private int id;

    //variable for item name
    private String itemName;

    //variable for item description
    private String itemDescription;

    //variavle for item amount in stock
    private String itemAmountInStock;

    //constructor class - not passing id because it increments automatically
    public ItemModal(String itemName, String itemDescription, String itemAmountInStock) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemAmountInStock = itemAmountInStock;
    }

    //getter & setter methods

    //item name
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    //description
    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    //item in stock
    public String getItemAmountInStock() {
        return itemAmountInStock;
    }

    public void setItemAmountInStock(String itemAmountInStock) {
        this.itemAmountInStock = itemAmountInStock;
    }

    //id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
