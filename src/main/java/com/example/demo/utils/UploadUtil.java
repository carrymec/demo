package com.example.demo.utils;

import com.example.demo.common.ServerResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @CLASSNAME UploadUtil
 * @DESCRIPTION 描述
 * @AUTHOR chen
 * @DATE 2018-12-04 14:52
 **/
public class UploadUtil {

    /**
     * 单文件上传
     *
     * @param attch
     * @param paths
     * @return
     */
    public static ServerResponse upload(MultipartFile attch, String paths) {
        if (!attch.isEmpty()) {
            String fileName = "";//文件名
            // 获取保存路径
            String path = paths;
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            // 原文件名
            String oldFileName = attch.getOriginalFilename();
            // 文件后缀名
            String prefix = FilenameUtils.getExtension(oldFileName);
            // 文件大小
            long fileSize = 102400000L;
            // 判断文件大小是否符合要求
            if (attch.getSize() > fileSize) {
                return ServerResponse.createByErrorMsg("上传文件过大，请重新上传");
            } else if (prefix.equalsIgnoreCase("jpg") | prefix.equalsIgnoreCase("jpeg") | prefix.equalsIgnoreCase("gif") | prefix.equalsIgnoreCase("png") | prefix.equalsIgnoreCase("mp4")) {// 上传图片格式是否正确
                // 设置新文件名
                fileName = System.currentTimeMillis() + new Random().nextInt(1000000) + "." + prefix;
                // 创建文件对象
                File targetFile = new File(path, fileName);
                // 保存
                try {
                    attch.transferTo(targetFile);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return ServerResponse.createBySuccess("https://api.zhenyangzixun.com/kb/image/" + fileName);//上传文件成功以后返回文件保存的路径
//                return ServerResponse.createBySuccess("/image/" + fileName);//上传文件成功以后返回文件保存的路径
            } else {
                return ServerResponse.createByErrorMsg("文件格式不正确");//
            }
        } else {
            return ServerResponse.createByErrorMsg("上传的文件为空");
        }
    }


    /**
     * 单文件上传
     *
     * @param attch
     * @param paths
     * @return
     */
    public static ServerResponse uploadFile(MultipartFile attch, String paths) {
        if (!attch.isEmpty()) {
            String fileName = "";//文件名
            // 获取保存路径
            String path = paths;
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            // 原文件名
            String oldFileName = attch.getOriginalFilename();
            // 文件后缀名
            String prefix = FilenameUtils.getExtension(oldFileName);
            // 文件大小
            long fileSize = 102400000L;
            // 判断文件大小是否符合要求
            if (attch.getSize() > fileSize) {
                return ServerResponse.createByErrorMsg("上传文件过大，请重新上传");
            }
            // 设置新文件名
            fileName = System.currentTimeMillis() + new Random().nextInt(1000000) + "." + prefix;
            // 创建文件对象
            File targetFile = new File(path, fileName);
            // 保存
            try {
                attch.transferTo(targetFile);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ServerResponse.createBySuccess("https://api.zhenyangzixun.com/kb/image/" + fileName);//上传文件成功以后返回文件保存的路径
//            return ServerResponse.createBySuccess("/image/" + fileName);//上传文件成功以后返回文件保存的路径

        } else {
            return ServerResponse.createByErrorMsg("上传的文件为空");
        }
    }

}
