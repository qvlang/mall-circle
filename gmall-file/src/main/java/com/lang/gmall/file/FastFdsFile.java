package com.lang.gmall.file;

import lombok.Data;

@Data
public class FastFdsFile {
    //文件名称
    private String name;
    //文件内容字节数组
    private byte[] content;
    //文件扩展名
    private String ext;
    //文件md5摘要
    private String md5;
    //文件作者
    private String author;
}
