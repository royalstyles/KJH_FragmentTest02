package com.example.kjh_fragmenttest02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<Holder> {
    ArrayList<String> list;

    ItemAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    // 뷰홀더를 생성(레이아웃 생성)
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_recycler, parent, false);
        return new Holder(view);
    }

    @Override
    // 뷰홀더가 재활용될 때 실행되는 메서드
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.txtview.setText(list.get(position));
    }

    @Override
    // 아이템 개수를 조회
    public int getItemCount() {
        return list.size();
    }
}

class Holder extends RecyclerView.ViewHolder {
    TextView txtview;

    public Holder(@NonNull View itemView) {
        super(itemView);
        txtview = itemView.findViewById(R.id.item_text);
    }
}
