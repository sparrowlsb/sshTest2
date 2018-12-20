package com.world.ico.util;

//import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hay on 2017/1/16.
 */
public class UploadUtil {
    /**
     * 图片上传工具类
     * 方法名：upLoadImage
     * 时间：2016年4月15日-下午2:05:20
     *
     * @param request 需要上传的request对象
     * @param image   上传的图片数组
     * @param path1   路径
     * @param path2   文件夹名称
     * @return String   返回的数据
     */
    public String upLoadImage(HttpServletRequest request, MultipartFile image, String path1, String path2) {
        List<String> fileTypes = new ArrayList<String>();
        path1 += path2;  //拼接固定路径
        String path3 = new SimpleDateFormat("yyyyMMdd").format(new Date());//创建文件夹路径
        String savePath = "";
        fileTypes.add("jpg");
        fileTypes.add("png");
        // 保存文件
        if (!(image.getOriginalFilename() == null || "".equals(image.getOriginalFilename()))) {
                    /*
                     * 下面调用的方法，主要是用来检测上传的文件是否属于允许上传的类型范围内，及根据传入的路径名
                     * 自动创建文件夹和文件名，返回的File文件我们可以用来做其它的使用，如得到保存后的文件名路径等这里我就先不做多的介绍。
                     */
            File file = this.getFile(image, path1, path3, fileTypes);
            savePath = file.getName();
        }

        return "/" + path2 + "/" + path3 + "/" + savePath;
    }

    private File getFile(MultipartFile imgFile, String typeName, String brandName, List fileTypes) {
        String nowTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + new Random().nextInt(10); //重命名
        String fileName = imgFile.getOriginalFilename();
        //获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        //对扩展名进行小写转换
        ext = ext.toLowerCase();

        File file = null;
        if (fileTypes.contains(ext)) {                      //如果扩展名属于允许上传的类型，则创建文件
            fileName = nowTime + '.' + ext;
            file = new File(fileName);
            try {
                imgFile.transferTo(file);                   //保存上传的文件
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
