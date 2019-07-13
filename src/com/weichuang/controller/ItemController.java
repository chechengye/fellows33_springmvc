package com.weichuang.controller;

import com.weichuang.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private JdbcTemplate jt;
    @RequestMapping("/itemList.do")
    public ModelAndView getItemList(){
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
        });
        ModelAndView mav = new ModelAndView();
        mav.addObject("itemList" , itemList);
        mav.setViewName("itemList");
        return mav;
    }
}
