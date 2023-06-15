package edu.czjt.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.czjt.reggie.dto.DishDto;
import edu.czjt.reggie.entity.Dish;

/**
 * 齐龙玉
 */
public interface DishService extends IService<Dish> {
    public DishDto getByIdWithFlavor(Long id);

    public void saveWithFlavor(DishDto dishDto);
}

