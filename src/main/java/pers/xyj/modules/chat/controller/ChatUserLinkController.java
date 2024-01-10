package pers.xyj.modules.chat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xyj.modules.chat.domain.dto.AddChatUserLinkDto;
import pers.xyj.modules.chat.service.ChatUserLinkService;
import pers.xyj.modules.common.annotation.SystemLog;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.domain.entity.Good;

@Api(value = "ChatUserLinkController",tags={"ChatUserLinkController操作接口"})
@RestController
@RequestMapping("/chatUserLink")
public class ChatUserLinkController {
    @Autowired
    private ChatUserLinkService chatUserLinkService;

    @ApiOperation(value="获取聊天列表")
    @SystemLog(businessName = "获取聊天列表")
    @GetMapping
    public ResponseResult getChatUserLinks(){
        return chatUserLinkService.getChatUserLinks();
    }

    @ApiOperation(value="添加连接信息")
    @SystemLog(businessName = "添加连接信息")
    @PostMapping
    public ResponseResult addLink(@RequestBody AddChatUserLinkDto linkDto){
        return chatUserLinkService.addLink(linkDto);
    }
}
