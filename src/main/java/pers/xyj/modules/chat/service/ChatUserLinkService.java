package pers.xyj.modules.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.chat.domain.dto.AddChatUserLinkDto;
import pers.xyj.modules.chat.domain.entity.ChatUserLink;
import pers.xyj.modules.market.domain.ResponseResult;

public interface ChatUserLinkService  extends IService<ChatUserLink> {
    ResponseResult getChatUserLinks();

    ResponseResult addLink(AddChatUserLinkDto linkDto);

}
