package graduationproject.back.utils;

import graduationproject.back.exception.BusinessException;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @Author Pan
 * @Date 2020/12/6 13:32
 */
public class FileZipUtil {

    /**
     * 压缩文件后缀
     */
    private static final String[] FORMAT = {"ZIP", "ZIPX", "RAR", "7Z", "ISO", "IMG", "ISZ", "CAB", "ARJ", "ACE", "ALZ", "UUE", "TAR", "GZ",
            "GZIP", "TGZ", "TPZ", "BZIP2", "BZ2", "BZ", "TBZ", "TBZ2", "XZ", "TXZ", "LZH", "LHA", "Z", "TAZ", "XPI", "JAR", "WIM", "SWM", "RPM", "XAR", "DEB",
            "DMG", "HFS", "CPIO", "LZMA", "LZMA86", "SPLIT", "001"};


        /**
          * 判断输出文件后缀是否为压缩文件后缀
          *
          * @param path
          * @return
          */
    public static boolean verify(String path) {
        boolean flag = false;
        try {
            String suffix = path.substring(path.lastIndexOf(".") + 1, path.length());
            if (suffix != null) {
                for (String str : FORMAT) {
                    if (str.equalsIgnoreCase(suffix)) {
                        flag = true;
                        break;
                    }
                }
            }
            return flag;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<FileHeader> unZip(String zipFilePath, String filePath) {
        try {
            ZipFile zipFile = new ZipFile(zipFilePath);
            zipFile.setFileNameCharset("GBK");//在GBK系统中需要设置
            zipFile.extractAll(filePath);
            return zipFile.getFileHeaders();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("解压失败");
        }
    }


    /**
     * zip解压
     *
     * @param inputFile   待解压文件名
     * @param destDirPath 解压路径
     */

    public static void ZipUncompress(String inputFile, String destDirPath) {
        File srcFile = new File(inputFile);//获取当前压缩文件
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new BusinessException(srcFile.getPath() + "所指文件不存在");
        }
        if (!verify(inputFile)) {
            throw new BusinessException(srcFile.getPath() + "所指文件格式错误");
        }
        try {
            //开始解压
            //构建解压输入流
            ZipInputStream zIn = new ZipInputStream(new FileInputStream(srcFile));
            ZipEntry entry = null;
            File file = null;
            while ((entry = zIn.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    file = new File(destDirPath, entry.getName());
                    if (!file.exists()) {
                        new File(file.getParent()).mkdirs();//创建此文件的上级目录
                    }
                    OutputStream out = new FileOutputStream(file);
                    BufferedOutputStream bos = new BufferedOutputStream(out);
                    int len = -1;
                    byte[] buf = new byte[1024];
                    while ((len = zIn.read(buf)) != -1) {
                        bos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    bos.close();
                    out.close();
                }
            }
        } catch (Exception e) {
            throw new BusinessException("解压失败！");
        }

    }


    /**
     * 测试用主程序
     */
//    public static void main(String[] args) {
//        try {
//            ZipCompress("C:\\Users\\A7103\\Desktop\\test", "C:\\Users\\A7103\\Desktop\\test\\Test.zip");
//            //ZipUncompress("C:\\Users\\A7103\\Desktop\\test\\Test.zip","C:\\Users\\A7103\\Desktop\\test2");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
