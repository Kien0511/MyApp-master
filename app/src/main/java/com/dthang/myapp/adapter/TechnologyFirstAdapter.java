package com.dthang.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dthang.myapp.R;
import com.dthang.myapp.model.objectclass.TradeMark;
import com.dthang.myapp.view.product.ListProductActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TechnologyFirstAdapter extends RecyclerView.Adapter<TechnologyFirstAdapter.VH> {

    private List<TradeMark> mListTrademark;
    private Context context;
    private boolean isProduct_Type = false;


    public TechnologyFirstAdapter(List<TradeMark> mListTrademark, Context context, boolean isProduct_Type) {
        this.mListTrademark = mListTrademark;
        this.context = context;
        this.isProduct_Type = isProduct_Type;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_technology_first, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, int position) {
        holder.tv_rcv_technology_first.setText(mListTrademark.get(position).getTrademarkName());

        Picasso.get().load(mListTrademark.get(position).getTrademakImage()).resize(120, 120).
                into(holder.im_rcv_technology_first, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.prb_rcv_technology_first.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });


    }

    @Override
    public int getItemCount() {
        return mListTrademark.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private TextView tv_rcv_technology_first;
        private ImageView im_rcv_technology_first;
        private ProgressBar prb_rcv_technology_first;

        public VH(final View itemView) {
            super(itemView);
            im_rcv_technology_first = itemView.findViewById(R.id.im_rcv_technology_first);
            tv_rcv_technology_first = itemView.findViewById(R.id.tv_rcv_technology_first);
            prb_rcv_technology_first = itemView.findViewById(R.id.prb_rcv_technology_first);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, ListProductActivity.class);
                    int position = getAdapterPosition();
                    intent.putExtra("id", mListTrademark.get(position).getTrademarkID());
                    intent.putExtra("name", mListTrademark.get(position).getTrademarkName());
                    intent.putExtra("isProductType", isProduct_Type);
                    context.startActivities(new Intent[]{intent});


                }
            });
        }
    }
}
