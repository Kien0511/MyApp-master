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
import android.widget.Toast;

import com.dthang.myapp.R;
import com.dthang.myapp.model.objectclass.Product;
import com.dthang.myapp.view.product_detail.DetailProductActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class TechnologySecondAdapter extends
        RecyclerView.Adapter<TechnologySecondAdapter.VH> {

    private List<Product> mListProduct;
    private Context mContext;

    public TechnologySecondAdapter(List<Product> mListProduct, Context mContext) {
        this.mListProduct = mListProduct;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_technology_second, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, final int position) {
        holder.tv_name_rcv_technology_second.setText(mListProduct.get(position).getPRODUCT_NAME());

        NumberFormat format =  new DecimalFormat("###,###");

        holder.tv_price_rcv_technology_second.setText(format.format(mListProduct.get(position).getPRODUCT_PRICE())+" VNĐ");
        holder.tv_sale_rcv_technology_second.setText(format.format(mListProduct.get(position).getPRODUCT_PRICE())+" VNĐ");

        Picasso.get().load(mListProduct.get(position).getPRODUCT_BIG_IMAGE()).resize(120, 120).
                into(holder.im_rcv_technology_second, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.prb_rcv_technology_second.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailProductActivity.class);

                intent.putExtra("product_id",   mListProduct.get(position).getPRODUCT_ID());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListProduct.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private TextView tv_sale_rcv_technology_second, tv_name_rcv_technology_second, tv_price_rcv_technology_second;
        private ImageView im_rcv_technology_second;
        private ProgressBar prb_rcv_technology_second;

        public VH(View itemView) {
            super(itemView);
            im_rcv_technology_second = itemView.findViewById(R.id.im_rcv_technology_second);
            tv_name_rcv_technology_second = itemView.findViewById(R.id.tv_name_rcv_technology_second);
            tv_price_rcv_technology_second = itemView.findViewById(R.id.tv_price_rcv_technology_second);
            tv_sale_rcv_technology_second = itemView.findViewById(R.id.tv_sale_rcv_technology_second);
            prb_rcv_technology_second = itemView.findViewById(R.id.prb_rcv_technology_second);
        }
    }
}
