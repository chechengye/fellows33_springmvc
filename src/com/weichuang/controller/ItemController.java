package com.weichuang.controller;

import com.weichuang.pojo.Item;
import com.weichuang.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class ItemController {

   /* @Autowired
    private JdbcTemplate jt;*/
   @Autowired
   private ItemService itemService;

    @RequestMapping("/itemList.do")
    public ModelAndView getItemList(){

       /*
       String sql = "select * from items";
       List<Item> itemList = jt.query(sql, new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet rs, int i) throws SQLException {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setDetail(rs.getString("detail"));
                item.setCreatetime(rs.getDate("createtime"));
                return item;
            }
        });*/
        List<Item> itemList = itemService.getAllItem();
        ModelAndView mav = new ModelAndView();
        mav.addObject("itemList" , itemList);
        mav.setViewName("itemList");
        return mav;
    }

    @RequestMapping("itemEdit.do")
    public String itemEdit(@RequestParam(value = "id" , defaultValue = "1" , required = false) Integer dsdds , Boolean status, HttpServletRequest request , HttpServletResponse response , HttpSession session , Model model){
        Item item = itemService.getItemById(dsdds);
        System.out.println("status= " + status);
       /* ModelAndView mav = new ModelAndView();
        mav.addObject("item" , item);
        mav.setViewName("editItem");*/
        model.addAttribute("item" , item);
        return "editItem";
    }

    @RequestMapping("/updateItem.do")
    public ModelAndView updateItem(Item item , HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        itemService.updateItem(item);
        List<Item> itemList = itemService.getAllItem();
        ModelAndView mav = new ModelAndView();
        mav.addObject("itemList" , itemList);
        mav.setViewName("itemList");
        return mav;
    }

    @RequestMapping("/deleteItems.do")
    public ModelAndView deleteItems(String[] ids){
        System.out.println(ids);
        itemService.deleteItems(ids);
        List<Item> itemList = itemService.getAllItem();
        ModelAndView mav = new ModelAndView();
        mav.addObject("itemList" , itemList);
        mav.setViewName("itemList");
        return mav;
    }
}
