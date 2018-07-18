package com.lam.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.lam.tuan on 17. 7. 2018.
 */
@Controller
public class MainController {
    @RequestMapping("/admin/dennemenu")
    public String dennemenu(){
        return "/admin-dashboard/dennemenu";
    }

    @RequestMapping(value = "/admin/saveMenu",method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    public @ResponseBody
    List<Menu> getGroup(@RequestBody List<Menu> menuList){
      //  menuList.stream().forEach(menu -> System.out.println(menu.toString()));
        return menuList;
    }

    private class Group{
        Integer id; String text;
        public Group(Integer id, String text){this.id=id;this.text=text;}

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
    @RequestMapping(value = "/groups",method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    public @ResponseBody List<Group> groups(){
        List<Group> groups = new ArrayList<>();
        groups.add(new Group(1,"shoup"));
        groups.add(new Group(2,"main food"));
        groups.add(new Group(3,"desert"));
        groups.add(new Group(4,"drink"));
        return groups;
    }

}
