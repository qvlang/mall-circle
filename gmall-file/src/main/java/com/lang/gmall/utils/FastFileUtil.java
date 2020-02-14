package com.lang.gmall.utils;

import com.lang.gmall.file.FastFdsFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * 文件上传工具类
 */
public class FastFileUtil {

    //读取配置文件
    static {
        String path = new ClassPathResource("fdfs_client.conf").getPath();
        try {
            ClientGlobal.init(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //上传文件
    public static String[] FileUpload(FastFdsFile fdsFile) throws Exception {
        //创建tracker客户端
        TrackerClient trackerClient = new TrackerClient();
        //通过trackerClient获取server
        TrackerServer server = trackerClient.getConnection();
        //将sercer传给storage
        StorageClient storageClient = new StorageClient(server, null);
        //创建文件描述对象
        NameValuePair[] pairs = new NameValuePair[1];
        pairs[0] = new NameValuePair("作者", fdsFile.getAuthor());
        //通过storageclient来操作文件
        String[] strings = storageClient.upload_file(fdsFile.getContent(), fdsFile.getExt(), pairs);
        return strings;
    }
}
