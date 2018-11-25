package com.dthang.myapp.view.product;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.dthang.myapp.R;
import com.dthang.myapp.adapter.ProductAdapter;
import com.dthang.myapp.customview.GridSpacingItemDecoration;
import com.dthang.myapp.customview.ILoadMoreRCV;
import com.dthang.myapp.customview.LoadMoreRCV;
import com.dthang.myapp.model.objectclass.Product;
import com.dthang.myapp.presenter.product.PresenterHandleLogicProduct;

import java.util.List;

public class ListProductActivity extends AppCompatActivity implements ViewHandleProductActivity, View.OnClickListener, ILoadMoreRCV {

    private static final String TAG = "fb";

    private Toolbar toolbar_product;
    private RecyclerView rcv_product;
    private ImageButton bt_changeadarter;
    private ProgressBar prb_loadmore;

    private boolean isGripView = true;

    private List<Product> products;
    private GridSpacingItemDecoration gridSpacingItemDecoration;
    private RecyclerView.LayoutManager layoutManager;
    private ProductAdapter productAdapter;


    private PresenterHandleLogicProduct mPresenterHandleLogicProduct;

    private int id;
    private boolean isProductType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodcuttrademark);

        initView();


        Intent intent = getIntent();

        id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        isProductType = intent.getBooleanExtra("isProductType", false);

        mPresenterHandleLogicProduct = new PresenterHandleLogicProduct(this);
        mPresenterHandleLogicProduct.handle(isProductType, id);


        Log.e(TAG, id + "___" + name + "___" + isProductType);
        productAdapter = new ProductAdapter(products, this, R.layout.item_rcv_gridviewproducts);
        initRecyclerView();

    }

    @SuppressLint("RestrictedApi")
    private void initView() {
        toolbar_product = findViewById(R.id.toolbar_producttrademark);
        setSupportActionBar(toolbar_product);
        rcv_product = findViewById(R.id.rcv_product);
        bt_changeadarter = findViewById(R.id.ibt_changetypercv);
        prb_loadmore = findViewById(R.id.prb_loadmore);


        bt_changeadarter.setOnClickListener(this);


        getSupportActionBar().setTitle("Danh sách sản phẩm");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gridSpacingItemDecoration = new GridSpacingItemDecoration(5);

    }

    @Override
    public void showProductList(List<Product> products) {
        this.products = products;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ibt_changetypercv: {
                initRecyclerView();
            }
        }
    }


    void initRecyclerView() {
        int scroll = 0;
        if (rcv_product.getLayoutManager() != null) {
            scroll = ((LinearLayoutManager) rcv_product.getLayoutManager()).findFirstCompletelyVisibleItemPosition() + 1;
            Log.e(TAG, "initRecyclerView: "+ scroll  );

        }
        if (!isGripView) {
            layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
            isGripView = !isGripView;
        } else {
            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            isGripView = !isGripView;
        }
        rcv_product.setLayoutManager(layoutManager);
        rcv_product.removeItemDecoration(gridSpacingItemDecoration);
        rcv_product.addItemDecoration(gridSpacingItemDecoration);
        rcv_product.setAdapter(productAdapter);
        rcv_product.scrollToPosition(scroll);



//        rcv_product.addOnScrollListener(new LoadMoreRCV(this, layoutManager));
        productAdapter.notifyDataSetChanged();


    }

    @Override
    public void loadMore(int sum) {
        Log.e("LoadMoreRCV", "loadMore: sum in ACTIVITI" + sum);
        List<Product> products = mPresenterHandleLogicProduct.getListLoadMore(isProductType, id, prb_loadmore, sum);
        Log.e("LoadMoreRCV", "loadMore: LIST" + products.size());
        this.products.addAll(products);
        productAdapter.notifyDataSetChanged();
    }
}
