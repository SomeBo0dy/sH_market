package pers.xyj.modules.market.service;

import org.springframework.web.multipart.MultipartFile;
import pers.xyj.modules.market.domain.ResponseResult;

public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
