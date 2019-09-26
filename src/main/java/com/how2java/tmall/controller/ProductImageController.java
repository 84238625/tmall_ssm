package com.how2java.tmall.controller;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductImageController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;

    @RequestMapping("admin_productImage_list")
    public String list(Model model, int pid) {
        Product p = productService.get(pid);
        List pisSingle = productImageService.list(pid, ProductImageService.type_single);
        List pisDetail = productImageService.list(pid, ProductImageService.type_detail);
        model.addAttribute("p", p);
        model.addAttribute("pisSingle", pisSingle);
        model.addAttribute("pisDetail", pisDetail);
        return "admin/listProductImage";
    }

    @RequestMapping("admin_ProductImage_add")
    public String add(ProductImage pi, HttpSession session, UploadedImageFile uploadedImageFile) {
        productImageService.add(pi);
        String fileName = pi.getId() + ".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;
        if (ProductImageService.type_single.equals(pi.getType())) {
            imageFolder = session.getServletContext().getRealPath("img/ProductSingle");
            imageFolder_small = session.getServletContext().getRealPath("img/ProductSingle_small");
            imageFolder_middle = session.getServletContext().getRealPath("img/ProductSingle_middle");
        } else {
            imageFolder = session.getServletContext().getRealPath("img/ProductDetail");
        }
        File file = new File(imageFolder, fileName);

            file.getParentFile().mkdirs();
        try {
            uploadedImageFile.getImage().transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
            if (ProductImageService.type_single.equals(pi.getType())) {
                File f_small = new File(imageFolder_small, fileName);
                File f_middle = new File(imageFolder_middle, fileName);

                ImageUtil.resizeImage(file, 56, 56, f_small);
                ImageUtil.resizeImage(file, 217, 190, f_middle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:admin_productImage_list?pid=" + pi.getPid();

    }
}