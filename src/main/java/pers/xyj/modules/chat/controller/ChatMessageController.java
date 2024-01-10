package pers.xyj.modules.chat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xyj.modules.chat.service.ChatMessageService;
import pers.xyj.modules.common.annotation.SystemLog;
import pers.xyj.modules.market.domain.ResponseResult;

@Api(value = "ChatMessageController",tags={"ChatMessageController操作接口"})
@RestController
@RequestMapping("/chatMessage")
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;

    @ApiOperation(value="获取聊天列表")
    @SystemLog(businessName = "获取聊天列表")
    @GetMapping("/{toUser}")
    public ResponseResult getMessage(@PathVariable("toUser") Long toUser){
        return chatMessageService.getMessage(toUser);
    }

}
