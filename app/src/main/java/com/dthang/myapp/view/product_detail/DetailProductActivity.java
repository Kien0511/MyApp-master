package com.dthang.myapp.view.product_detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dthang.myapp.R;
import com.dthang.myapp.adapter.ProductDetailViewPagerAdapter;
import com.dthang.myapp.model.objectclass.Product;
import com.dthang.myapp.model.objectclass.ProductDetail;
import com.dthang.myapp.presenter.product_detail.PresenterLogicProductDetail;
import com.dthang.myapp.util.App;
import com.dthang.myapp.view.product_detail.Fragment.FragmentSliderProductDetail;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailProductActivity extends AppCompatActivity implements IViewProductDetail, ViewPager.OnPageChangeListener{

    private Bundle bData;
    ViewPager viewPager;
    PresenterLogicProductDetail presenterLogicProductDetail;

    TextView[] txtDot;
    LinearLayout layoutDots, ll_ProductParameter;

    List<Fragment> fragmentList;

    TextView txtProductName, txtProductPrice, txtNameOfPackingShop, txtProductDetailInfomation;

    Toolbar toolbar;

    ImageView showProductDetailInfomation;

    boolean check = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailproduct);

        viewPager = findViewById(R.id.viewpagerSlider);
        layoutDots = findViewById(R.id.layoutDots);
        txtProductName = findViewById(R.id.txtProductName);
        txtProductPrice = findViewById(R.id.txtProductPrice);
        toolbar = findViewById(R.id.toolbar);
        txtNameOfPackingShop = findViewById(R.id.txtNameOfPackingShop);
        txtProductDetailInfomation = findViewById(R.id.txtProductDetailInfomation);
        showProductDetailInfomation = findViewById(R.id.showProductDetailInfomation);
        ll_ProductParameter = findViewById(R.id.ll_ProductParameter);


        setSupportActionBar(toolbar);

        int product_id = getIntent().getIntExtra("product_id",0);
        presenterLogicProductDetail = new PresenterLogicProductDetail(this);
        presenterLogicProductDetail.getProductDetail(product_id);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public void ShowProductDetail(final Product product) {
        txtProductName.setText(product.getPRODUCT_NAME());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String price = numberFormat.format(product.getPRODUCT_PRICE()).toString();
        txtProductPrice.setText(price + " VNĐ");
        txtNameOfPackingShop.setText(product.getEMPLOYEE_NAME());
        txtProductDetailInfomation.setText(product.getPRODUCT_INFOMATION().substring(0,100));

        if(product.getPRODUCT_INFOMATION().length() < 100)
        {
            showProductDetailInfomation.setVisibility(View.GONE);
        }
        else
        {
            showProductDetailInfomation.setVisibility(View.VISIBLE);

            showProductDetailInfomation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    check = !check;
                    if(check)
                    {
                        txtProductDetailInfomation.setText(product.getPRODUCT_INFOMATION());
                        showProductDetailInfomation.setImageDrawable(getImageProductDetail(R.drawable.ic_keyboard_arrow_up_black_24dp));
                        ll_ProductParameter.setVisibility(View.VISIBLE);
                        ShowProductParameter(product);
                    }
                    else
                    {
                        txtProductDetailInfomation.setText(product.getPRODUCT_INFOMATION().substring(0,100));
                        showProductDetailInfomation.setImageDrawable(getImageProductDetail(R.drawable.ic_keyboard_arrow_down_black_24dp));
                        ll_ProductParameter.setVisibility(View.GONE);

                    }
                }
            });
        }


    }

    private void ShowProductParameter(Product product)
    {
        List<ProductDetail> productDetailList = product.getProductDetailList();

        ll_ProductParameter.removeAllViews();
        for (int i = 0; i < productDetailList.size(); i++)
        {
            LinearLayout ll_Detail = new LinearLayout(this);
            ll_Detail.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ll_Detail.setOrientation(LinearLayout.HORIZONTAL);
            TextView txtParameterName = new TextView(this);
            txtParameterName.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtParameterName.setText(productDetailList.get(i).getPRODUCT_DETAIL_NAME());

            TextView txtParameterValue = new TextView(this);
            txtParameterValue.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtParameterValue.setText(productDetailList.get(i).getPRODUCT_DETAIL_VALUE());


            ll_Detail.addView(txtParameterName);
            ll_Detail.addView(txtParameterValue);

            ll_ProductParameter.addView(ll_Detail);
        }
    }

    private Drawable getImageProductDetail(int idDrawable)
    {
        Drawable drawable;
        if(Build.VERSION.SDK_INT < 21)
        {
            drawable = ContextCompat.getDrawable(this,idDrawable);
        }
        else
        {
            drawable = getResources().getDrawable(idDrawable);
        }
        return drawable;
    }
    @Override
    public void ShowProductSlider(String[] linkProductImage) {

        fragmentList = new ArrayList<>();

        for (int i = 0; i < linkProductImage.length; i++)
        {
            FragmentSliderProductDetail fragmentSliderProductDetail = new FragmentSliderProductDetail();
            Bundle bundle = new Bundle();
            bundle.putString("linkImage",App.SERVER + linkProductImage[i]);
            fragmentSliderProductDetail.setArguments(bundle);

            fragmentList.add(fragmentSliderProductDetail);
        }

        ProductDetailViewPagerAdapter adapter = new ProductDetailViewPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        addDotSlider(0);
        viewPager.addOnPageChangeListener(this);
    }

    private void addDotSlider(int position){
        txtDot = new TextView[fragmentList.size()];

        layoutDots.removeAllViews();
        for (int i = 0; i < fragmentList.size(); i++)
        {
            txtDot[i] = new TextView(this);
            txtDot[i].setText(Html.fromHtml("&#8226;"));
            txtDot[i].setTextSize(40);
            txtDot[i].setTextColor(getIdColor(R.color.colorSlider));

            layoutDots.addView(txtDot[i]);
        }

        txtDot[position].setTextColor(getIdColor(R.color.colorBlack));
    }

    private int getIdColor(int idColor)
    {
        int color = 0;
        if(Build.VERSION.SDK_INT > 21)
        {
            color = ContextCompat.getColor(this,idColor);
        }
        else
        {
            color = getResources().getColor(idColor);
        }
        return color;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        addDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
