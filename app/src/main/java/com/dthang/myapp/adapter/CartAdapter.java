package com.dthang.myapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dthang.myapp.R;
import com.dthang.myapp.model.objectclass.Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    Context context;
    List<Product> list;

    public CartAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.custom_layout_cart,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String price = numberFormat.format(list.get(position).getPRODUCT_PRICE()).toString();
        holder.txtName.setText(list.get(position).getPRODUCT_NAME());
        holder.txtPrice.setText(price);

        Bitmap bitmap = BitmapFactory.decodeByteArray(list.get(position).getCart_image(),0,list.get(position).getCart_image().length);
        holder.imProductImage.setImageBitmap(bitmap);

        holder.imDelete.setTag(list.get(position).getPRODUCT_ID());

        holder.imDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "xóa sp", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName, txtPrice;
        ImageView imProductImage, imDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.tv_name_rcv_product);
            txtPrice = itemView.findViewById(R.id.tv_price_rcv_product);
            imProductImage = itemView.findViewById(R.id.im_rcv_product);
            imDelete = itemView.findViewById(R.id.imDelete);
        }
    }
}
