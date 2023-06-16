package edu.czjt.reggie.mapper;
// 购物车接口，许子枫
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.czjt.reggie.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}

