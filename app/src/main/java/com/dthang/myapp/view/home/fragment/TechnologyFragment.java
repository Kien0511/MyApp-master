package com.dthang.myapp.view.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dthang.myapp.R;
import com.dthang.myapp.adapter.TechnologyAdapter;
import com.dthang.myapp.adapter.TechnologySecondAdapter;
import com.dthang.myapp.adapter.TechnologyTopTrademarkAdapter;
import com.dthang.myapp.customview.GridSpacingItemDecoration;
import com.dthang.myapp.model.objectclass.Product;
import com.dthang.myapp.model.objectclass.Technology;
import com.dthang.myapp.model.objectclass.TradeMark;
import com.dthang.myapp.presenter.technology.PresenterHandleTechnology;
import com.dthang.myapp.view.home.ViewHandleTechnology;

import java.util.List;

public class TechnologyFragment extends Fragment implements ViewHandleTechnology{

    private static final String TAG = "TechnologyFragment";

    private PresenterHandleTechnology mPresenterHandleTechnology;
    private RecyclerView rcv_technology;
    private List<Technology> listTechnology;
    private List<TradeMark> listLogo;
    private List<Product> listProductNew;

    private RecyclerView rcv_logotoptrademark,rcv_productnew;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenterHandleTechnology = new PresenterHandleTechnology(this);
        mPresenterHandleTechnology.handle();
        Log.e(TAG, "onCreate: " );

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_technology,container,false);
        rcv_technology = view.findViewById(R.id.rcv_technology);
        rcv_logotoptrademark = view.findViewById(R.id.rcv_toptrademark_technology);
        rcv_productnew = view.findViewById(R.id.rcv_productnew_technology);

        TechnologyAdapter technologyAdapter = new TechnologyAdapter(this.listTechnology,getActivity());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        rcv_technology.setLayoutManager(layoutManager);
        rcv_technology.setAdapter(technologyAdapter);
        technologyAdapter.notifyDataSetChanged();


        TechnologyTopTrademarkAdapter technologyTopTrademarkAdapter = new
                TechnologyTopTrademarkAdapter(listLogo,getActivity());

        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(getActivity(),2, LinearLayoutManager.HORIZONTAL, false);
        rcv_logotoptrademark.setLayoutManager(layoutManager1);
        rcv_logotoptrademark.setAdapter(technologyTopTrademarkAdapter);
        technologyTopTrademarkAdapter.notifyDataSetChanged();



        TechnologySecondAdapter technologySecondAdapter = new
                TechnologySecondAdapter(listProductNew, getActivity());


        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rcv_productnew.setLayoutManager(layoutManager2);
        rcv_productnew.addItemDecoration(new GridSpacingItemDecoration(5));
        rcv_productnew.setAdapter(technologySecondAdapter);
        technologySecondAdapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void showTechnology(List<Technology> listTechnology,List<TradeMark> listLogo,List<Product> listProductNew) {
        this.listTechnology = listTechnology;
        this.listLogo = listLogo;
        this.listProductNew = listProductNew;
    }
}
