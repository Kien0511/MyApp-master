package com.dthang.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dthang.myapp.R;
import com.dthang.myapp.model.objectclass.ProductDetail;
import com.dthang.myapp.model.objectclass.TradeMark;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TechnologyTopTrademarkAdapter extends RecyclerView.Adapter<TechnologyTopTrademarkAdapter.VH> {

    private List<TradeMark> listTrademaMark;
    private Context context;

    public TechnologyTopTrademarkAdapter(List<TradeMark> listTrademaMark, Context context) {
        this.listTrademaMark = listTrademaMark;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trademark, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Picasso.get().load(listTrademaMark.get(position).getTrademakImage()).resize(180, 80).
                into(holder.im_trademark, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return listTrademaMark.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private ImageView im_trademark;


        public VH(View itemView) {
            super(itemView);
            im_trademark = itemView.findViewById(R.id.im_trademark);
        }
    }
}
