package com.vmall.vproducts.service.vproduct;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 9:44 2019-05-13
 * @Modifyied By:
 */
@Service
public class UploadService {

    static Logger log= LoggerFactory.getLogger(UploadService.class);
    public String uploadFile(byte[] byteFile, String ext_file, String file_Name) {

        // 拼接服务区的文件路径
        StringBuffer sbPath = new StringBuffer();
        sbPath.append("http://203.195.221.127:9870/uploads/");
        try {
            // 初始化文件资源
            ClientGlobal.initByProperties("application.properties");
            // 链接FastDFS服务器，创建tracker和Stroage
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();

            String storageServerIp = getStorageServerIp(trackerClient, trackerServer);
            StorageServer storageServer = getStorageServer(storageServerIp);
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            //利用字节流上传文件
//            NameValuePair[] nvps = new NameValuePair[1];
//            nvps[0] = new NameValuePair(file_Name, ext_file);
            String[] strings = storageClient.upload_file(byteFile, ext_file, null);

            sbPath.append(StringUtils.join(strings,"/"));
            log.info("上传路径=" + sbPath.toString());

        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return sbPath.toString();
    }

    /**
     * 得到Storage服务
     * @param storageIp
     * @return 返回Storage服务
     */
    private static StorageServer getStorageServer(String storageIp) {
        StorageServer storageServer = null;
        if (storageIp != null && !("").equals(storageIp)) {
            try {
                // ip port store_path下标
                storageServer = new StorageServer(storageIp, 23000, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.debug("——storage server生成");
        return storageServer;
    }

    /**
     * 获得可用的storage IP
     *
     * @param trackerClient
     * @param trackerServer
     * @return 返回storage IP
     */
    private static String getStorageServerIp(TrackerClient trackerClient, TrackerServer trackerServer) {
        String storageIp = null;
        if (trackerClient != null && trackerServer != null) {
            try {
                StorageServer storageServer = trackerClient.getStoreStorage(trackerServer, "source");
                storageIp = storageServer.getSocket().getInetAddress().getHostAddress();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.debug("——获取组中可用的storage IP——" + storageIp);
        return storageIp;
    }
}
