package pers.xyj.service;

import org.springframework.web.multipart.MultipartFile;
import pers.xyj.domain.ResponseResult;

public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
