package com.example.myapplication.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Model;
import com.example.myapplication.R;
import com.example.myapplication.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<Model> textList;
    private Context context;

    public Adapter(Context context) {
        this.textList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model text = textList.get(position);
        holder.txt_message.setText(text.getText());

        holder.txt_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context.getClass().getSimpleName().contains("HomeActivity")) {
                    Toast.makeText(context, text.getText(), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }

            }
        });

    }

    public void updateList(List<Model> textList) {
        this.textList = textList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return textList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_message;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_message = itemView.findViewById(R.id.txt_message);

        }
    }
}
