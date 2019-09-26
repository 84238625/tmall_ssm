package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.ProductMapper;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductExample;
import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import  com.how2java.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);//会对字段进行判断再更新(如果为Null就忽略更新)，如果你只想更新某一字段，可以用这个方法。
                                                           //updateByPrimaryKey() 对注入的字段全部更新

    }

    @Override
    public Product get(int id) {
        return productMapper.selectByPrimaryKey(id);
      }

    @Override
    public List<Product> list(int cid) {
        ProductExample example=new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");

     return  productMapper.selectByExample(example);
       /* setCategory(result);
        return result;*/
    }

    @Override
    public void setFirstProductImage(Product p) {

        List<ProductImage> pis=productImageService.list(p.getId(),ProductImageService.type_single);
        if(!pis.isEmpty()){
            ProductImage pi=pis.get(0);
            p.setFirstProductImage(pi);
        }



    }
    public void setFirstProductImage(List<Product> ps) {
        for (Product p : ps) {
            setFirstProductImage(p);
        }
    }







  /*  private void setCategory(List <Product> ps) {
        for (Product p : ps)
            setCategory(p);
    }*/
   /* public void setCategory(Product p){
        int cid = p.getCid();
        Category c = categoryService.get(cid);
        p.setCategory(c);
    }*/
}
