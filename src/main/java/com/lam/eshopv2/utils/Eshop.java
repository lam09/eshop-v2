package com.lam.eshopv2.utils;

/**
 * Created by a.lam.tuan on 11. 6. 2018.
 */
public class Eshop {

    //roles
    public static final String ADMIN_ROLE= "ADMIN";
    public static final String EMPLOYEE_ROLE= "EMPLOYEE";

    //image types
    public static final String IMAGE_TYPE_LOGO= "LOGO_IMAGE";
    public static final String IMAGE_TYPE_ICON= "ICON_IMAGE";
    public static final String IMAGE_TYPE_GALLERY= "GALLERY_IMAGE";

    public static enum OrderState {
        RECIEVED,PREPARED,SHIPPED,COMPLETED
    }
}
