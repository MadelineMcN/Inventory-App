package com.example.inventoryappmcneeleymadeline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
public class MainActivity extends AppCompatActivity {

    //creates variables for recycler view
    private RecyclerView itemRV;
    private static final int ADD_ITEM_REQUEST = 1;
    private static final int EDIT_ITEM_REQUEST = 2;
    private ViewModal viewModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing recycler view and fab
        itemRV = findViewById(R.id.idRVItems);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        //on click listener for fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewItemActivity.class);
                startActivityForResult(intent, ADD_ITEM_REQUEST);
            }
        });

        //layout manager for adapter class
        itemRV.setLayoutManager(new LinearLayoutManager(this));
        itemRV.setHasFixedSize(true);

        //intializing adapter for recycle view
        final ItemRVAdapter adapter = new ItemRVAdapter();

        //setting adapter class for recycler view modal
        itemRV.setAdapter(adapter);

        viewModal = ViewModelProviders.of(this).get(ViewModal.class);
        viewModal.getAllItems().observe(this, new Observer<List<ItemModal>>() {
            @Override
            public void onChanged(List<ItemModal> models) {
                adapter.submitList(models);
            }
        });

        //Swipe to delete item from grid
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // on recycler view item swiped then this is deleting the item from recycler view.
                viewModal.delete(adapter.getItemAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                attachToRecyclerView(itemRV);
        adapter.setOnItemClickListener(new ItemRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ItemModal model) {
                Intent intent = new Intent(MainActivity.this, NewItemActivity.class);
                intent.putExtra(NewItemActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewItemActivity.EXTRA_ITEM_NAME, model.getItemName());
                intent.putExtra(NewItemActivity.EXTRA_DESCRIPTION, model.getItemDescription());
                intent.putExtra(NewItemActivity.EXTRA_AMOUNT, model.getItemAmountInStock());

                //starts new activity
                startActivityForResult(intent, EDIT_ITEM_REQUEST);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ITEM_REQUEST && resultCode == RESULT_OK) {
            String itemName = data.getStringExtra(NewItemActivity.EXTRA_ITEM_NAME);
            String itemDesc = data.getStringExtra(NewItemActivity.EXTRA_DESCRIPTION);
            String itemAmount = data.getStringExtra(NewItemActivity.EXTRA_AMOUNT);
            ItemModal model = new ItemModal(itemName, itemDesc, itemAmount);
            viewModal.insert(model);
            Toast.makeText(this, "Item Saved", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == EDIT_ITEM_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewItemActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Item can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String itemName = data.getStringExtra(NewItemActivity.EXTRA_ITEM_NAME);
            String itemDesc = data.getStringExtra(NewItemActivity.EXTRA_DESCRIPTION);
            String itemAmount = data.getStringExtra(NewItemActivity.EXTRA_AMOUNT);
            ItemModal model = new ItemModal(itemName, itemDesc, itemAmount);
            model.setId(id);
            viewModal.update(model);
            Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Item Not Saved", Toast.LENGTH_SHORT).show();
        }
    }

}