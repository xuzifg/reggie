package edu.czjt.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.czjt.reggie.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by chenzhituo on 2023/6/10
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
