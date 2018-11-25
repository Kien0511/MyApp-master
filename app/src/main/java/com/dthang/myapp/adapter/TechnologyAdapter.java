package com.dthang.myapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dthang.myapp.R;
import com.dthang.myapp.customview.GridSpacingItemDecoration;
import com.dthang.myapp.model.objectclass.Technology;

import java.util.List;

public class TechnologyAdapter extends RecyclerView.Adapter<TechnologyAdapter.VH> {

    private List<Technology> mListTechnology;
    private Context mContext;

    public TechnologyAdapter(List<Technology> mListTechnology, Context mContext) {
        this.mListTechnology = mListTechnology;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_technology, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        TechnologyFirstAdapter technologyFirstAdapter = null;

        TechnologySecondAdapter technologySecondAdapter = new
                TechnologySecondAdapter(mListTechnology.get(position).getmListProduct(), mContext);

        holder.tv_rcv_technology1.setText(mListTechnology.get(position).getTitle1());
        holder.tv_rcv_technology2.setText(mListTechnology.get(position).getTitle2());


        RecyclerView recyclerView = holder.rcv_technology_first;
        RecyclerView recyclerView2 = holder.rcv_technology_second;

        RecyclerView.LayoutManager layoutManager = null;

        switch (position) {
            case 0: {
                technologyFirstAdapter = new
                        TechnologyFirstAdapter(mListTechnology.get(0).getmListTrademark(), mContext, false);
                holder.im_rcv_technology.setImageResource(R.drawable.festivalsale90);
                layoutManager = new GridLayoutManager(mContext, 3, GridLayoutManager.HORIZONTAL, false);
                break;
            }
            case 1: {
                technologyFirstAdapter = new
                        TechnologyFirstAdapter(mListTechnology.get(1).getmListTrademark(), mContext, true);
                holder.im_rcv_technology.setImageResource(R.drawable.conlocgiamgiamuahe);
                layoutManager = new GridLayoutManager(mContext, 2, GridLayoutManager.HORIZONTAL, false);
                break;
            }
            case 2: {
                technologyFirstAdapter = new
                        TechnologyFirstAdapter(mListTechnology.get(2).getmListTrademark(), mContext, true);
                holder.im_rcv_technology.setImageResource(R.drawable.conlocgiamgiamuahe);
                layoutManager = new GridLayoutManager(mContext, 3, GridLayoutManager.HORIZONTAL, false);
                break;
            }
            default: {

            }

        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(5));
        recyclerView.setAdapter(technologyFirstAdapter);
        technologyFirstAdapter.notifyDataSetChanged();

        //
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.addItemDecoration(new GridSpacingItemDecoration(5));
        recyclerView2.setAdapter(technologySecondAdapter);
        technologySecondAdapter.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class VH extends RecyclerView.ViewHolder {

        private ImageView im_rcv_technology;
        private TextView tv_rcv_technology1, tv_rcv_technology2;
        private RecyclerView rcv_technology_first, rcv_technology_second;

        public VH(View itemView) {
            super(itemView);

            im_rcv_technology = itemView.findViewById(R.id.im_rcv_technology);
            tv_rcv_technology1 = itemView.findViewById(R.id.tv_rcv_technology1);
            tv_rcv_technology2 = itemView.findViewById(R.id.tv_rcv_technology2);
            rcv_technology_first = itemView.findViewById(R.id.rcv_technology_first);
            rcv_technology_second = itemView.findViewById(R.id.rcv_technology_second);
        }
    }
}
