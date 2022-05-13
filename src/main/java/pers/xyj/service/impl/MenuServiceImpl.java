package pers.xyj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xyj.domain.entity.Menu;
import pers.xyj.mapper.MenuMapper;
import pers.xyj.service.MenuService;

/**
 * 菜单表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2022-04-03 10:50:49
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
