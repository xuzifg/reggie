package edu.czjt.reggie.service.impl;
// 购物车业务层接口类，许子枫
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.czjt.reggie.entity.ShoppingCart;
import edu.czjt.reggie.mapper.ShoppingCartMapper;
import edu.czjt.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}

