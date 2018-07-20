package com.lam.restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.lam.tuan on 19. 7. 2018.
 */
public class Ultil {
    private static List<Menu> menuList;
    public static void setMenuList(List<Menu> menuList){
        Ultil.menuList=menuList;
    }

    public static List<Menu> getMenuList()
    {
        return Ultil.menuList;
    }

}
