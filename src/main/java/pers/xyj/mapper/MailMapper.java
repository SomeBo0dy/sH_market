package pers.xyj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.xyj.domain.entity.Mail;

/**
 * 邮箱(Mail)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-07 09:03:29
 */
@Mapper
public interface MailMapper extends BaseMapper<Mail> {

}

