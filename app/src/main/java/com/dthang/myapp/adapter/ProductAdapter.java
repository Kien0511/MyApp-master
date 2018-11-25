package com.dthang.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dthang.myapp.R;
import com.dthang.myapp.model.objectclass.Product;
import com.dthang.myapp.view.product_detail.DetailProductActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VH> {

    private List<Product> products;
    private Context context;
    private int layout;
    private boolean isList = true;

    public ProductAdapter(List<Product> products, Context context, int layout) {
        this.products = products;
        this.context = context;
        this.layout = layout;
        isList = !isList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(isList ? R.layout.item_rcv_listviewproducts : R.layout.item_rcv_gridviewproducts, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, final int position) {
        holder.tv_name_rcv_product.setText(products.get(position).getPRODUCT_NAME());

        NumberFormat format = new DecimalFormat("###,###");

        holder.tv_price_rcv_product.setText(format.format(products.get(position).getPRODUCT_PRICE()) + " VNĐ");
        holder.tv_sale_rcv_product.setText(format.format(products.get(position).getPRODUCT_PRICE()) + " VNĐ");

        Picasso.get().load(products.get(position).getPRODUCT_BIG_IMAGE()).resize(120, 120).
                into(holder.im_rcv_product, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.prb_rcv_product.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iOpenDetailProduct = new Intent(context, DetailProductActivity.class);
                iOpenDetailProduct.putExtra("product_id",products.get(position).getPRODUCT_ID());
                Log.e("product_id",products.get(position).getPRODUCT_ID()+"");
                context.startActivity(iOpenDetailProduct);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private TextView tv_name_rcv_product, tv_price_rcv_product, tv_sale_rcv_product;
        private ImageView im_rcv_product;
        private ProgressBar prb_rcv_product;

        public VH(View itemView) {
            super(itemView);
            im_rcv_product = itemView.findViewById(R.id.im_rcv_product);
            tv_name_rcv_product = itemView.findViewById(R.id.tv_name_rcv_product);
            tv_price_rcv_product = itemView.findViewById(R.id.tv_price_rcv_product);
            tv_sale_rcv_product = itemView.findViewById(R.id.tv_sale_rcv_product);
            prb_rcv_product = itemView.findViewById(R.id.prb_rcv_product);

        }
    }
}
