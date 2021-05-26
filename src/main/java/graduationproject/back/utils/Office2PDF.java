package graduationproject.back.utils;

import com.github.pagehelper.util.StringUtil;
import org.jodconverter.OfficeDocumentConverter;
import org.jodconverter.office.DefaultOfficeManagerBuilder;
import org.jodconverter.office.OfficeException;
import org.jodconverter.office.OfficeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.regex.Pattern;

/**
 * doc docx ex.. ex..x ppt pptx
 */
public final class Office2PDF {
    private static final Logger logger = LoggerFactory.getLogger(Office2PDF.class);
    private Office2PDF(){}

    /**
     * 将office格式的文件转为pdf
     * @param sourceFilePath 源文件路径
     * @return
     */
    public static File openOfficeToPDF(String sourceFilePath){
        return office2pdf(sourceFilePath);
    }

    /**
     * 将office文档转换为pdf文档
     * @param sourceFilePath 原文件路径
     * @return
     */
    public static File office2pdf(String sourceFilePath){
        OfficeManager officeManager = null;
        try{
            if(StringUtil.isEmpty(sourceFilePath))
            {
                //打印日志...
                logger.error("输入文件地址为空，转换终止!");
                return null;
            }
            //如果该pdf已存在，直接返回
            String after_convert_file_path = getAfterConverFilePath(sourceFilePath);
            File outputFile = new File(after_convert_file_path);
            if(outputFile.exists()){
                return outputFile;
            }
            File sourceFile = new File(sourceFilePath);
            if(!sourceFile.exists())
            {
                //打印日志...
                logger.error("输入文件不存在，转换终止!");
                return null;
            }
            //启动openOffice
            officeManager = getOfficeManager();
            OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
            return convertFile(sourceFile,outputFile,sourceFilePath,converter);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("转换异常:"+e.getMessage());
        }finally {
            if(officeManager != null){
                try {
                    officeManager.stop();
                } catch (OfficeException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 转换文件
     * @param sourceFile 原文件
     * @param outputFile 转换后文件
     * @param sourceFilePath 原文件路径
     * @param converter 转换器
     * @return
     */
    public static File convertFile(File sourceFile,
                                   File outputFile,String sourceFilePath,OfficeDocumentConverter converter) throws OfficeException {
        if(!outputFile.getParentFile().exists()){
            //如果上级目录不存在则创建一个
            outputFile.getParentFile().mkdirs();
        }
        converter.convert(sourceFile,outputFile);
        return outputFile;
    }

    public static OfficeManager getOfficeManager(){
        DefaultOfficeManagerBuilder builder = new DefaultOfficeManagerBuilder();
        builder.setOfficeHome(getOfficeHome());
        OfficeManager officeManager = builder.build();
        try {
            officeManager.start();
        } catch (OfficeException e) {
            //打印日志
            logger.error("start openOffice Fail!");
            e.printStackTrace();
        }
        return officeManager;
    }

    /**
     * 获取转换后文件存放的路径
     * @param sourceFilePath 源文件
     * @return
     */
    public static String getAfterConverFilePath(String sourceFilePath){
        //截取源文件文件名
        String sourceFileName = sourceFilePath.replaceAll("." + getPostfix(sourceFilePath), ".pdf");
        return sourceFileName;
    }
    /**
     * 获取inputFilePath的后缀名，如："e:/test.pptx"的后缀名为："pptx"<br>
     *
     * @param inputFilePath
     * @return
     */
    public static String getPostfix(String inputFilePath) {
        return inputFilePath.substring(inputFilePath.lastIndexOf(".") + 1);
    }


    /**
     * 获取openOffice的安装目录
     * @return
     */
    public static String getOfficeHome(){
        String osName = System.getProperty("os.name");
        if (Pattern.matches("Linux.*", osName)) {
            return "/opt/openoffice4";
        } else if (Pattern.matches("CentOS.*", osName)) {
            return "/opt/openoffice4";
        }else if (Pattern.matches("Windows.*", osName)) {
            return "C:\\Program Files (x86)\\OpenOffice 4";
        } else if (Pattern.matches("Mac.*", osName)) {
            return "/Applications/OpenOffice.org.app/Contents/";
        }
        return null;
    }
    /**
     * 获取后缀
     * @param fileName 文件名
     * @return 后缀名
     */
    public static String getFileSuffix(String fileName){
        if(StringUtil.isEmpty(fileName) || fileName.lastIndexOf(".")<0 ){
            logger.error("文件名为空或后缀名为空");
            return "error";
        }
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }
}