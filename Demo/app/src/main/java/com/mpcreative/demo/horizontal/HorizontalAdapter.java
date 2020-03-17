package com.mpcreative.demo.horizontal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpcreative.demo.Model.Task_entity;
import com.mpcreative.demo.R;
import com.zerobranch.layout.SwipeLayout;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ItemHolder> {
    private List<Task_entity> items;

    public HorizontalAdapter(List<Task_entity> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        return new ItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_layout_item_0, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int position) {
        itemHolder.dragItem.setText(items.get(position).getTask());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void remove(Context context, int position) {
        Toast.makeText(context, "removed item " + position, Toast.LENGTH_SHORT).show();
    }

    private void upload(Context context, int position) {
        Toast.makeText(context, "upload item " + position, Toast.LENGTH_SHORT).show();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        TextView dragItem;
        ImageView leftView;
        ImageView rightView;
        SwipeLayout swipeLayout;

        ItemHolder(@NonNull final View itemView) {
            super(itemView);
            dragItem = itemView.findViewById(R.id.drag_item);
            swipeLayout = itemView.findViewById(R.id.swipe_layout);
            leftView = itemView.findViewById(R.id.left_view);
            rightView = itemView.findViewById(R.id.right_view);

            rightView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() != NO_POSITION) {
                        remove(itemView.getContext(), getAdapterPosition());
                    }
                }
            });

            leftView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() != NO_POSITION) {
                        upload(itemView.getContext(), getAdapterPosition());
                    }
                }
            });
        }
    }
}
