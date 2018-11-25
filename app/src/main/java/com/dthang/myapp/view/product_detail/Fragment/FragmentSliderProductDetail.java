package com.dthang.myapp.view.product_detail.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dthang.myapp.R;
import com.squareup.picasso.Picasso;

public class FragmentSliderProductDetail extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_slider_product_detail,container,false);

        Bundle bundle = getArguments();
        String linkImage = bundle.getString("linkImage");

        ImageView imageView = view.findViewById(R.id.imSlider);

        Picasso.get().load(linkImage).into(imageView);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
