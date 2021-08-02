package com.jica.todochack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.jica.todochack.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<Holder> {
    ArrayList<String> list;

    RecyclerAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_todo, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tvTodoText.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class Holder extends RecyclerView.ViewHolder {
    TextView tvTodoText;
    CheckBox cbTodoDone;
    ImageView ivTodoMenu;

    public Holder(@NonNull View itemView) {
        super(itemView);
        tvTodoText = itemView.findViewById(R.id.tvTodoText);
        cbTodoDone = itemView.findViewById(R.id.cbTodoDone);
        ivTodoMenu = itemView.findViewById(R.id.ivTodoMenu);
    }
}