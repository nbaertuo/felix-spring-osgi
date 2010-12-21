package org.ertuo.taoplugin.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 对象处理
 * 
 * @author mo.duanm
 * 
 */
public class ObjectUtil {

    // 压缩对象,把对象压缩成字节(如果压缩的是字符串,压缩成的字节不能直接用new String来转换)
    public static byte[] writeCompressObject(Object object_) {
        byte[] data_ = null;
        ByteArrayOutputStream o = null;
        GZIPOutputStream gzout = null;
        ObjectOutputStream out = null;
        try {
            // 建立字节数组输出流
            o = new ByteArrayOutputStream();
            // 建立gzip压缩输出流
            gzout = new GZIPOutputStream(o);
            // 建立对象序列化输出流
            out = new ObjectOutputStream(gzout);
            out.writeObject(object_);
            out.flush();
            out.close();
            gzout.close();
            // 返回压缩字节流
            data_ = o.toByteArray();
            o.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return (data_);
    }

    // 将压缩字节数组还原为相应类型数据对象
    public static Object readCompressObject(byte[] data_) {
        Object object_ = null;
        try {
            // 建立字节数组输入流
            ByteArrayInputStream i = new ByteArrayInputStream(data_);
            // 建立gzip解压输入流
            GZIPInputStream gzin = new GZIPInputStream(i);
            // 建立对象序列化输入流
            ObjectInputStream in = new ObjectInputStream(gzin);
            // 按制定类型还原对象
            object_ = in.readObject();
            i.close();
            gzin.close();
            in.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);

        }
        return (object_);
    }
}
