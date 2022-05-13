package pers.xyj.order;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.xyj.domain.vo.OrderVo;
import pers.xyj.mapper.OrderMapper;

@SpringBootTest
public class OrderTest {
    @Autowired
    OrderMapper orderMapper;
    @Test
    public void test1(){
        IPage<OrderVo> page = new Page<>(1,1);
        orderMapper.getPage(page,8L,0);
        System.out.println(page.getRecords());
    }
}
