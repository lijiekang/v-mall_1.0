package com.vmall.vutil;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.BufferedOutputStream;
import java.io.IOException;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 9:34 2019-05-14
 * @Modifyied By:
 */
public class FastDFS {

    private TrackerClient trackerClient; //追踪者服客户端
    private TrackerServer trackerServer; //追踪者服务
    private StorageServer storageServer; //储存服务
    private StorageClient1 storageClient; //储存客户端


    /**
     * 初始化tracker和storage
     * @throws Exception
     */
    public FastDFS() throws Exception{
        ClientGlobal.init("application_fastdfs.properties");
        trackerClient=new TrackerClient();
        trackerServer=trackerClient.getConnection();
        storageClient=new StorageClient1(trackerServer,storageServer);
    }


    /**
     * 上传文件
     * @param fileName 文件名
     * @param extName 扩展名
     * @param metas  文件扩展信息
     * @return
     */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas){
        String result=null;

        try {
            result=storageClient.upload_file1(fileName,extName,metas);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 上传文件操作
     * @param fileName 文件名
     * @return
     */
    public String uploadFile(String fileName){
        return uploadFile(fileName,null,null);
    }


    /**
     * 上传文件
     * @param fileName 文件名
     * @param extName 扩展名
     * @return
     */
    public String uploadFile(String fileName,String extName){
        return uploadFile(fileName,extName,null);
    }


    /**
     * 上传文件
     * @param fileContent
     * @param extName
     * @param metas
     * @return
     */
    public String uploadFile(byte[] fileContent,String extName,NameValuePair[] metas){
        String result=null;

        try {
            result=storageClient.upload_file1(fileContent,extName,metas);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 文件下载到磁盘
     * @param path
     * @param outputStream
     * @return
     */
    public int download_file(String path, BufferedOutputStream outputStream){
        int result=-1;

        try {
            byte[] b=storageClient.download_file1(path);
            if(b!=null){
                outputStream.write(b);
                result=0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }finally{
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    /**
     * 获取文件数组
     * @param path
     * @return
     */
    public byte[] download_bytes(String path){
        byte[] b=null;
        try {
            b=storageClient.download_file1(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return b;
    }


    /**
     * 删除文件
     * @param group
     * @param storagePath
     * @return
     */
    public Integer delete_file(String group,String storagePath){
        int result=-1;

        try {
            result=storageClient.delete_file(group,storagePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return result;
    }



    public Integer delete_file(String storagePath){
        int result=-1;

        try {
            result=storageClient.delete_file1(storagePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取远程仓库上的资源文件信息
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public FileInfo getFile(String groupName,String remoteFileName){

        try {
            return storageClient.get_file_info(groupName,remoteFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String uploadFile(byte[] fileContent){
        return uploadFile(fileContent,null,null);
    }

    public String uploadFile(byte[] fileContent,String extName){
        return uploadFile(fileContent,extName,null);
    }



    public TrackerClient getTrackerClient() {
        return trackerClient;
    }

    public void setTrackerClient(TrackerClient trackerClient) {
        this.trackerClient = trackerClient;
    }

    public TrackerServer getTrackerServer() {
        return trackerServer;
    }

    public void setTrackerServer(TrackerServer trackerServer) {
        this.trackerServer = trackerServer;
    }

    public StorageServer getStorageServer() {
        return storageServer;
    }

    public void setStorageServer(StorageServer storageServer) {
        this.storageServer = storageServer;
    }

    public StorageClient1 getStorageClient() {
        return storageClient;
    }

    public void setStorageClient(StorageClient1 storageClient) {
        this.storageClient = storageClient;
    }
}
