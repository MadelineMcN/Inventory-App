package com.example.inventoryappmcneeleymadeline;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//annotation for database entities & version
@Database(entities = {ItemModal.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {

    //creates instance for database class
    private static ItemDatabase instance;

    //creates abstract variable for dao
    public abstract Dao Dao();

    //getting instance for database
    public static synchronized  ItemDatabase getInstance(Context context) {
        //checks for null
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ItemDatabase.class, "item_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    //creates callback to db
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    //creates async task to preform tasks in the background
    private static  class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(ItemDatabase instance) {
            Dao dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void...voids) {
            return null;
        }
    }
}
