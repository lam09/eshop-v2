package com.lam.eshopv2.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by a.lam.tuan on 22. 6. 2018.
 */
public class ImageForm {
    private MultipartFile[] fileData; //photo gallery

    public MultipartFile[] getFileData() {
        return fileData;
    }

    public void setFileData(MultipartFile[] fileData) {
        this.fileData = fileData;
    }

    public ImageForm() {
    }
}
