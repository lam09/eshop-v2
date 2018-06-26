package com.lam.eshopv2.form;

import com.lam.eshopv2.entity.Product;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by a.lam.tuan on 22. 6. 2018.
 */
public class ImageForm {
    private MultipartFile[] fileData; //photo gallery

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private Product product;

    public MultipartFile[] getFileData() {
        return fileData;
    }

    public void setFileData(MultipartFile[] fileData) {
        this.fileData = fileData;
    }

    public ImageForm() {
    }
}
