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
    public String upLoadImage(HttpServletRequest request, MultipartFile image, String path1, String path2,Integer userId) {
        List<String> fileTypes = new ArrayList<String>();
        path1 += path2;
        String path3 = new SimpleDateFormat("yyyyMMdd").format(new Date());//创建文件夹路径
        String savePath = "";
        fileTypes.add("gif");
        fileTypes.add("jpeg");
        fileTypes.add("svg");
        fileTypes.add("jpg");
        fileTypes.add("png");
        // 保存文件
        if (!(image.getOriginalFilename() == null || "".equals(image.getOriginalFilename()))) {
                    /*
                     * 下面调用的方法，主要是用来检测上传的文件是否属于允许上传的类型范围内，及根据传入的路径名
                     * 自动创建文件夹和文件名，返回的File文件我们可以用来做其它的使用，如得到保存后的文件名路径等这里我就先不做多的介绍。
                     */
            File file = this.getFile(image, path1, path3,userId, fileTypes);
            savePath = file.getName();
        }

        return path1 + "/" + path2 + "/" + path3 + "/" +userId+"/"+ savePath;
    }

    private File getFile(MultipartFile imgFile, String typeName, String brandName,Integer userId, List fileTypes) {
        String nowTime = new SimpleDateFormat("yyyy-MM-dd-HHmmssSSS").format(new Date()) + new Random().nextInt(10); //重命名
        String fileName = imgFile.getOriginalFilename();
        //获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        //对扩展名进行小写转换
        ext = ext.toLowerCase();

        File file = null;
        if (fileTypes.contains(ext)) {                      //如果扩展名属于允许上传的类型，则创建文件
            fileName = userId+"-"+nowTime + '.' + ext;
            file = this.creatFolder(typeName, brandName, fileName,userId);
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

    private File creatFolder(String typeName, String brandName, String fileName,Integer userId) {
        File file = null;
                 //去掉"/"
        typeName+="/"+brandName+"/";
        typeName = typeName.replaceAll(" ", "");               //替换半角空格
          //替换全角空格

        File firstFolder = new File(typeName);
        //一级文件夹
        if (firstFolder.exists()) {                             //如果一级文件夹存在，则检测二级文件夹
            File secondFolder = new File(firstFolder, String.valueOf(userId));
            if (secondFolder.exists()) {                        //如果二级文件夹也存在，则创建文件
                file = new File(secondFolder, fileName);
            } else {                                            //如果二级文件夹不存在，则创建二级文件夹
                secondFolder.mkdir();
                file = new File(secondFolder, fileName);        //创建完二级文件夹后，再合建文件
            }
        } else {                                                //如果一级不存在，则创建一级文件夹
            firstFolder.mkdir();
            File secondFolder = new File(firstFolder, String.valueOf(userId));
            if (secondFolder.exists()) {                        //如果二级文件夹也存在，则创建文件
                file = new File(secondFolder, fileName);
            } else {                                            //如果二级文件夹不存在，则创建二级文件夹
                secondFolder.mkdir();
                file = new File(secondFolder, fileName);
            }
        }
        return file;
    }
}
