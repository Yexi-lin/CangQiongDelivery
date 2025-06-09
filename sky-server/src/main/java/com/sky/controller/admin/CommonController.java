package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.exception.OSSUploadException;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 *
 * @Author:Yexi_lin
 * @Date: 2025/06/06 14:38
 * @Description:
 */
@Api(tags = "通用接口 CommonController")
@RestController
@RequestMapping("/admin/common")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @ApiOperation("文件上传 uploadFile")
    @PostMapping("/upload")
    public Result uploadFile(@ApiParam("传入的文件") MultipartFile file) {
        //生成文件名称
        String originalFilename = file.getOriginalFilename();
        String endName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String startName = UUID.randomUUID().toString().replace("-", "");
        String fileName = startName + endName;
        //上传文件 并获取文件路径
        try {
            String fileUrl = aliOssUtil.upload(file.getBytes(), fileName);
            return Result.success(fileUrl);
        } catch (OSSUploadException e) {
            //捕获文件上传过程中的异常,并使用全局异常处理器处理
            throw new BaseException(e.getMessage());
        } catch (Exception e) {
            //捕获其他异常情况
            throw new BaseException(MessageConstant.UPLOAD_FAILED);
        }
            //直接返回错误信息
            //return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}