package com.yang.jk.controller;

import com.yang.jk.common.R;
import com.yang.jk.utils.Uploads;
import com.yang.jk.utils.Yproperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @auther yhjStart
 * @create 2022-04-03 22:00
 */
@RestController("upload")
@Api(tags = "上传测试")
public class uploadController {
    @Autowired
    private Yproperties yproperties;
    @Autowired
    private Uploads uploads;
    @ApiOperation("上传图片")
    @PostMapping(value = "/uploadImg",consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public R  uploadImg(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        String dir = yproperties.getUpload().getImageRelativePath();
        String s = uploads.uploadFile(multipartFile, dir);
        return R.ok().data("path",s);
    }
}
