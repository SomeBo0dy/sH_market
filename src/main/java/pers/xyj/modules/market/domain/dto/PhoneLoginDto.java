package pers.xyj.modules.market.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PhoneLoginDto {
    @ApiModelProperty(value = "手机")
    private String phone;
    @ApiModelProperty(value = "用户类型：0代表普通用户 1代表第三方工作人员，2代表管理员")
    private String type;
}
