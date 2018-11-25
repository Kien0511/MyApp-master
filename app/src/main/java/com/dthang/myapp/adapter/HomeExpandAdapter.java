package com.dthang.myapp.adapter;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dthang.myapp.R;
import com.dthang.myapp.model.home.DataHome;
import com.dthang.myapp.model.objectclass.ProductType;

import java.util.List;

public class HomeExpandAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "HomeExpandAdapter";

    private Context mContext;
    private List<ProductType> mListProductTypes;
    private DataHome dataHome;

    private ViewHD viewHD;

    public HomeExpandAdapter(Context mContext, List<ProductType> mListProductTypes) {
        this.mContext = mContext;
        this.mListProductTypes = mListProductTypes;
        dataHome = new DataHome();
        int lenght = mListProductTypes.size();
        for (int i = 0; i < lenght; i++) {
            int id = mListProductTypes.get(i).getProductTypeID();
            mListProductTypes.get(i).setListChidel(dataHome.getListProductTypeChildren(id));
        }
    }

    @Override
    public int getGroupCount() {
        return mListProductTypes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mListProductTypes.get(groupPosition).getListChidel().size() != 0) {
            return 1;
        } else return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListProductTypes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListProductTypes.get(groupPosition).getListChidel().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mListProductTypes.get(groupPosition).getProductTypeID();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mListProductTypes.get(groupPosition).getListChidel().get(childPosition).getProductTypeID();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_parent, parent, false);
            viewHD = new ViewHD();
            viewHD.tv = convertView.findViewById(R.id.tv_product_type_name);
            viewHD.im = convertView.findViewById(R.id.im_ep_menu);

            convertView.setTag(viewHD);
        } else viewHD = (ViewHD) convertView.getTag();

        int _i = mListProductTypes.get(groupPosition).getListChidel().size();
        if (_i > 0) {
            viewHD.im.setVisibility(View.VISIBLE);
        } else viewHD.im.setVisibility(View.INVISIBLE);

        if (isExpanded) {
            viewHD.im.setImageResource(R.drawable.ic_remove_black);
            convertView.setBackgroundResource(R.color.colorOrange);
        } else {
            viewHD.im.setImageResource(R.drawable.ic_add_black);
            convertView.setBackgroundResource(R.color.colorWhite);
        }

        viewHD.tv.setText(mListProductTypes.get(groupPosition).getProductTypeName());

        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "ID: " + mListProductTypes.get(groupPosition).getProductTypeID() + "\nTen: "
                        + mListProductTypes.get(groupPosition).getProductTypeName());
                return false;
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_children, parent, false);
        HomeExpandAdapter homeExpandAdapter = new HomeExpandAdapter(parent.getContext(), mListProductTypes.get(groupPosition)
                .getListChidel());

        SecondExpanable secondExpanable = new SecondExpanable(mContext);
        secondExpanable.setGroupIndicator(null);

        secondExpanable.setAdapter(homeExpandAdapter);

        homeExpandAdapter.notifyDataSetChanged();

        return secondExpanable;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public class SecondExpanable extends ExpandableListView {
        public SecondExpanable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;

//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width,MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    class ViewHD {
        TextView tv;
        ImageView im;
    }
}
