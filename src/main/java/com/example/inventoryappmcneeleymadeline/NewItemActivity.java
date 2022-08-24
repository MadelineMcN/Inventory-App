package com.example.inventoryappmcneeleymadeline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NewItemActivity extends AppCompatActivity {

    //creates a variables for screen elements
    private EditText itemNameEdt, itemDescEdt, itemAmtEdt;
    private Button itemBtn;

    //creates contants string variables
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_ITEM_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ITEM_NAME";
    public static final String EXTRA_DESCRIPTION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ITEM_DESCRIPTION";
    public static final String EXTRA_AMOUNT = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ITEM_AMOUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        //initializing variables
        itemNameEdt = findViewById(R.id.idEdtItemName);
        itemDescEdt = findViewById(R.id.idEdtItemDescription);
        itemAmtEdt = findViewById(R.id.idEdtItemAmount);
        itemBtn = findViewById(R.id.idBtnSaveItem);

        //creates intent to grab data
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            itemNameEdt.setText(intent.getStringExtra(EXTRA_ITEM_NAME));
            itemDescEdt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            itemAmtEdt.setText(intent.getStringExtra(EXTRA_AMOUNT));
        }

        //creates on click listener for save button
        itemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = itemNameEdt.getText().toString();
                String itemDesc = itemDescEdt.getText().toString();
                String itemAmount = itemAmtEdt.getText().toString();
                if (itemName.isEmpty() || itemDesc.isEmpty() || itemAmount.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "Please Enter More Details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveItem(itemName, itemDesc, itemAmount);
            }
        });
    }

    //passing data inside new intent
    private void saveItem(String itemName, String itemDesc, String itemAmount) {
        Intent data = new Intent();

        //passing item detail
        data.putExtra(EXTRA_ITEM_NAME, itemName);
        data.putExtra(EXTRA_DESCRIPTION, itemDesc);
        data.putExtra(EXTRA_AMOUNT, itemAmount);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        Toast.makeText(this,"Item has been saved to Inventory Database.", Toast.LENGTH_SHORT).show();
    }
}