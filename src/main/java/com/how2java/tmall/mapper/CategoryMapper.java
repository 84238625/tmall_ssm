package com.how2java.tmall.mapper;
 
import com.how2java.tmall.pojo.Category;
 import com.how2java.tmall.util.Page;
import java.util.List;
 
public interface CategoryMapper {
     List<Category> list();  //分页查询方法
    /* int total(); //查询总数方法*/
     void add(Category category);//增加分类
     void delete(int id);//删除分类
     Category get(int id);//编辑中得到该分类信息
     void update(Category category);//更改分类信息
}