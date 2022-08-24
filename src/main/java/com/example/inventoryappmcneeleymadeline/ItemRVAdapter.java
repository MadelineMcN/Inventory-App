package com.example.inventoryappmcneeleymadeline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ItemRVAdapter extends ListAdapter<ItemModal, ItemRVAdapter.ViewHolder> {

    //creates a variable for on item click listener
    private OnItemClickListener listener;

    //creates a constructor class for adapter class
    ItemRVAdapter() {
        super(DIFF_CALLBACK);
    }

    //creates callback for item on recycler view
    private static final DiffUtil.ItemCallback<ItemModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<ItemModal>() {
        @Override
        public boolean areItemsTheSame(ItemModal oldItem, ItemModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(ItemModal oldItem, ItemModal newItem) {
            return oldItem.getItemName().equals(newItem.getItemName()) &&
                    oldItem.getItemDescription().equals(newItem.getItemDescription()) &&
                    oldItem.getItemAmountInStock().equals(newItem.getItemAmountInStock());
        }
    };

    //inflates layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
      View item = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.item_rv_item, parent, false);
      return new ViewHolder(item);
    }

    //sets data to each item
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModal model = getItemAt(position);
        holder.itemNameTV.setText(model.getItemName());
        holder.itemDescriptionTV.setText(model.getItemDescription());
        holder.itemAmountTV.setText(model.getItemAmountInStock());
    }

    //creates method to get course modal for specific position
    public ItemModal getItemAt(int position) {
        return getItem(position);
    }

    //creates variable for each view
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemNameTV, itemDescriptionTV, itemAmountTV;

        //initializing card variables
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTV = itemView.findViewById(R.id.idTVItemName);
            itemDescriptionTV = itemView.findViewById(R.id.idTVItemDescription);
            itemAmountTV = itemView.findViewById(R.id.idTVItemAmount);

            //adding click on listeners
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ItemModal model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
