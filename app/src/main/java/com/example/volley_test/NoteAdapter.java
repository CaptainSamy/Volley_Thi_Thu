package com.example.volley_test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    Context context;
    ArrayList<NoteModel> noteModels;

    public NoteAdapter(Context context, ArrayList<NoteModel> noteModels) {
        this.context = context;
        this.noteModels = noteModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NoteModel noteModel = noteModels.get(position);
        holder.tvTitle.setText(noteModel.getTitle());

        if (noteModel.getCompleted() == true){
            holder.tvTitle.setTextColor(Color.BLUE);
        }else {
            holder.tvTitle.setTextColor(Color.RED);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("userId", noteModel.getUserId());
                bundle.putInt("Id", noteModel.getId());
                bundle.putString("title", noteModel.getTitle());
                bundle.putBoolean("complete", noteModel.getCompleted());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
