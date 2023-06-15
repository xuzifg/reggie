package edu.czjt.reggie.dto;

import edu.czjt.reggie.entity.Dish;
import edu.czjt.reggie.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者 齐龙玉 
 * 接受页面提交的flavors数据:数据的结构比较复杂为数组
 */
@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

}

