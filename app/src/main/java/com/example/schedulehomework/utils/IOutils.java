package com.example.schedulehomework.utils;
import android.content.Context;

import com.alibaba.fastjson.JSON;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class IOutils {
    // 向指定的文件中写入指定的数据
    public static void writeFileData(Context conText,String filename, Object obj) throws Exception{

            FileOutputStream fos = conText.openFileOutput(filename, MODE_PRIVATE);//获得FileOutputStream
            String jsonStr=JSON.toJSONString(obj);
            //将要写入的字符串转换为byte数组
            byte[] bytes =jsonStr.getBytes();
            fos.write(bytes);//将byte数组写入文件
            fos.close();//关闭文件输出流

    }

//打开指定文件，读取其数据，返回字符串对象
    public static <T> List<T> readFileData(Context conText, String fileName,Class<T> cla) throws Exception{
            String result = "";
            FileInputStream fis = conText.openFileInput(fileName);
//获取文件长度
            int lenght = fis.available();
            byte[] buffer = new byte[lenght];
            fis.read(buffer);
//将byte数组转换成指定格式的字符串
            result = new String(buffer, "UTF-8");
            return JSON.parseArray(result.toString(),cla);
    }

//如果文件不存在则创建
    public static void CreateFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
    }
}
