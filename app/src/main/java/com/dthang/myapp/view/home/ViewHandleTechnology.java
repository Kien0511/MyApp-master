package com.dthang.myapp.view.home;

import com.dthang.myapp.model.objectclass.Product;
import com.dthang.myapp.model.objectclass.Technology;
import com.dthang.myapp.model.objectclass.TradeMark;

import java.util.List;

public interface ViewHandleTechnology {
   void showTechnology(List<Technology> listTechnology,List<TradeMark> listLogoTrademark,List<Product> listProductNew);
}
