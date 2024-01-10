package pers.xyj.modules.market.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateGoodStateDto {
    @ApiModelProperty(value = "id")
    Long gId;
    @ApiModelProperty(value = "改变到的状态")
    String state;
}
