package com.how2java.tmall.mapper;
 
import com.how2java.tmall.pojo.Category;
 import com.how2java.tmall.util.Page;
import java.util.List;
 
public interface CategoryMapper {
     List<Category> list(Page page);  //分页查询方法
     int total(); //查询总数方法
     void add(Category category);//增加分类
}