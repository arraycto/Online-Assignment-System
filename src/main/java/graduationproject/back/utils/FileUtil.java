package graduationproject.back.utils;

import graduationproject.back.exception.BusinessException;
import graduationproject.back.exception.enums.CommonEnum;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Author Pan
 * @Date 2020/12/11 11:55
 */
public class FileUtil {

    public static String getUploadPath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()) path = new File("");
        File upload = new File(path.getAbsolutePath(), "/upload/");
        if (!upload.exists()) upload.mkdirs();
        return upload.getAbsolutePath();
    }

    public static String downloadFile(HttpServletResponse response, String filePath) {
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        // 设置文件路径
        File file = new File(getUploadPath() + filePath);
        // File file = new File(realPath , fileName);
        if (file.exists()) {
            response.setContentType("application/force-download;charset=UTF-8");// 设置强制下载不打开

            /**
             * URLEncoder.encode(fileName,"UTF-8")为了解决中文名称乱码
             */
            try {
                response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName,"UTF-8"));// 设置文件名
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int len = -1;
                byte[] buf = new byte[1024];
                while ((len = bis.read(buf)) != -1) {
                    os.write(buf, 0, len);
                    os.flush();
                }
                System.out.println("下载[" + fileName + "]成功");
                return "";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        throw new BusinessException(CommonEnum.TEST_ONLY_ERROR);
    }


    /**
     * 读取文件内容
     *
     * @param path 文件地址
     * @return String类型
     */
    public static String readFile(String filePath) {
        StringBuffer stringBuffer = new StringBuffer();
        // 设置文件路径
        File file = new File(getUploadPath() + "/" + filePath);
        try {
            int lineNumber = 0;
            InputStream stream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream, "gbk"));
            String line;

            while ((line = br.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(stringBuffer);
    }


    public static String uploadFile(String userId,Integer assignmentId, MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 设置文件存储路径
            String filePath = getUploadPath();

            String path = filePath + "/" + userId + "/" + assignmentId + "/" + fileName;

            if (FileZipUtil.verify(fileName)) {
                path = filePath + "/" + userId + "/" + "ZipFile" + "/" + fileName;
            }

            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(CommonEnum.FILE_SAVE_ERROR);
        }
    }

    public static String batchUploadFile(String userId, Integer assignmentId, MultipartFile file, int i) {
        try {
            BufferedOutputStream stream = null;
            String fileName = file.getOriginalFilename();

            // 设置文件存储路径
            String filePath = getUploadPath();

            String path = filePath + "/" + userId + "/" + assignmentId + "/" + fileName;

            if (FileZipUtil.verify(fileName)) {
                path = filePath + "/" + userId + "/" + "ZipFile" + "/" + fileName;
            }

            File dest = new File(path);

            //处理重名文件
            int index = 2;
            boolean first = true;
            String duplicateFileName = fileName;
            while (true) {
                //如果fileName文件不存在重名，直接创建保存
                if (!dest.exists()) {
                    // 检测是否存在目录
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();// 新建文件夹
                    }
                    file.transferTo(dest);// 文件写入
                    break;
                }
                //否则用数字作为前缀，但不能一直加前缀，会造成名字太长，
                // 因此，从第二次进入该分支开始，都需要去除已经加上去的前缀，
                //然后重新加前缀，同时给数字下标前缀做自增
                else {
                    assert fileName != null;
                    duplicateFileName = fileName;
                    String replaceName = duplicateFileName.substring(0, fileName.indexOf("."));
                    duplicateFileName = duplicateFileName.replace(replaceName,replaceName+ "(" + index + ")") ;
                    index++;
                    String duplicatePath = filePath + "/" + userId + "/" + assignmentId + "/" + duplicateFileName;
                    if (FileZipUtil.verify(duplicateFileName)) {
                        duplicatePath = filePath + "/" + userId + "/" + "ZipFile" + "/" + duplicateFileName;
                    }
                    dest = new File(duplicatePath);
                }
            }
            return duplicateFileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(CommonEnum.FILE_SAVE_ERROR);
        }
//        // 检测是否存在目录
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();// 新建文件夹
//        }

//        if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//                stream = new BufferedOutputStream(
//                        new FileOutputStream(new File(path)));// 设置文件路径及名字
//                stream.write(bytes);// 写入
//                stream.close();
//            } catch (Exception e) {
//                stream = null;
//                throw new BusinessException("第 " + i + " 个文件上传失败 ==> " + e.getMessage());
//            }
//        } else {
//            throw new BusinessException("第 " + i + " 个文件上传失败因为文件为空");
//        }
    }

    /**
     * 文件处理
     * @param fileName
     * @return
     */
    public static String fileHandler(String fileName, String filePath){
        String fileSuffix = Office2PDF.getFileSuffix(fileName);
        System.out.println(fileSuffix);
        if("pdf".equals(fileSuffix))
        {
            return filePath;
        }
        else
        {
            //生成的pdf储存路径，BASE_PATH + fileName
            return Office2PDF.openOfficeToPDF(filePath).getAbsolutePath();
        }

    }
}
