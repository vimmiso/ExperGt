package com.example.expergt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {

    private String[] data,answer;
    public ProgrammingAdapter(String[] data,String[] answer)
    {
        this.data = data;
        this.answer = answer;
    }
    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item_layout,viewGroup,false);
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder programmingViewHolder, int i) {
        String title = data[i];
        String title2 = answer[i];
        programmingViewHolder.txtQuestion1.setText(title);
        programmingViewHolder.txtAnswer.setText(title2);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        ImageView imgIcon;
        TextView txtQuestion1,txtAnswer;
        public ProgrammingViewHolder(View itemView){
            super(itemView);
            imgIcon = (ImageView)itemView.findViewById(R.id.imgIcon);
            txtQuestion1 = (TextView)itemView.findViewById(R.id.txtQuestion1);
            txtAnswer = (TextView)itemView.findViewById(R.id.txtAnswer);
        }
    }
}
