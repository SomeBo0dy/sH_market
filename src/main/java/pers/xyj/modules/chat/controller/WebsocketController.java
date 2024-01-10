package pers.xyj.modules.chat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pers.xyj.modules.common.annotation.SystemLog;
import pers.xyj.modules.market.domain.ResponseResult;

import java.io.IOException;

@Api(value = "WebSocketControllerApi",tags={"私聊操作接口"})
@RestController
public class WebsocketController {
//    @GetMapping("index")
//    public ResponseEntity<String> index(){
//        return ResponseEntity.ok("请求成功");
//    }




    @GetMapping("/connect/{toUserId}")
    @ApiOperation(value = "建立websocket链接")
    @SystemLog(businessName = "建立websocket链接")
    public ResponseResult<String> connect(@PathVariable String toUserId){
        return ResponseResult.okResult("请求成功");
    }
    //
//    @GetMapping("page")
//    public ModelAndView page(){
//        return new ModelAndView("websocket");
//    }
//
//    @RequestMapping("/push/{toUserId}")
//    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {
//        WebSocketServer.sendInfo(message,toUserId);
//        return ResponseEntity.ok("MSG SEND SUCCESS");
//    }
    @PostMapping("/push/{toUserId}")
    @ApiOperation(value = "向指定用户id发送私聊信息")
    @SystemLog(businessName = "向指定用户id发送私聊信息")
    public ResponseResult<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {
//    WebSocketServer.sendInfo(message,toUserId);
    return ResponseResult.okResult("MSG SEND SUCCESS");
    }
    @GetMapping("/connect/users")
    @ApiOperation(value = "获取私聊用户列表")
    @SystemLog(businessName = "获取私聊用户列表")
    public ResponseResult getUserList(){
        return ResponseResult.okResult("请求成功");
    }
    @GetMapping("/connect/message/{toUserId}")
    @ApiOperation(value = "查看与该用户的历史聊天记录")
    @SystemLog(businessName = "查看与该用户的历史聊天记录")
    public ResponseResult getUserMessage( @PathVariable String toUserId){
        return ResponseResult.okResult("请求成功");
    }

}
