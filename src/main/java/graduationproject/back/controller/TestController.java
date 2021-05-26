package graduationproject.back.controller;

import graduationproject.back.exception.BusinessException;
import graduationproject.back.exception.enums.CommonEnum;
import graduationproject.back.service.TestService;
import graduationproject.back.utils.FileUtil;
import graduationproject.back.utils.JsonData;
import graduationproject.back.utils.Office2PDF;
import org.apache.tomcat.util.http.fileupload.IOUtils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * @Author
 * @Date 2020/11/7 22:24
 */
@RequestMapping("api")
@RestController
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;

    }

    //测试自定义业务异常
    @GetMapping("test")
    public Object test() {

        throw new BusinessException(CommonEnum.TEST_ONLY_ERROR);

    }

    //    测试空指针异常
    @GetMapping("test2")
    public Object test2() {
        String str = null;
        str.equals("123");
        return 1;
    }

    @PostMapping("import")
    public JsonData addUser(@RequestParam("file") MultipartFile file, @RequestParam(value = "courseId", required = true) Integer courseId) {

        boolean result = false;

        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        result = testService.batchImportExcel(fileName, file, courseId);
        return result ? JsonData.buildSuccess("插入成功") : JsonData.buildError("插入失败");
    }

    // 单个文件上传
    @PostMapping(value = "/uploadFile")
    public JsonData upload(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
        String userId = (String) request.getAttribute("userId");
        //String errorMsg = FileUtil.uploadFile(userId,file);
        String errorMsg = "123";
        return errorMsg.isEmpty() ? JsonData.buildSuccess("上传成功") : JsonData.buildError(errorMsg);
    }

//    // 文件批量上传
//    @PostMapping("/batchUploadFile")
//    public JsonData handleFileUpload(HttpServletRequest request) {
//        String userId = (String) request.getAttribute("userId");
//
//        //String errorMsg = FileUtil.batchUploadFile(userId,request);
//
//        return errorMsg.isEmpty() ? JsonData.buildSuccess("上传成功") : JsonData.buildError(errorMsg);
//    }


    // 文件下载
    @GetMapping("/downloadfile")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "fileName",required = true)String fileName) throws IOException {

        return FileUtil.downloadFile(response,fileName);
    }



        //base路径
        private final String BASE_PATH = "C:\\Users\\A7103\\Desktop\\test\\";


        /**
         *
         * @param res 响应对象
         * @throws Exception
         */
//        @RequestMapping("/read")
//        public void readFile(HttpServletResponse res) throws Exception{
//            String fileName="3.docx";
//            InputStream in = null;
//            OutputStream out = null;
//            //判断是pdf还是word还是excel
//            //若是pdf直接读 否则转pdf 再读  //
//            String filePath =  fileHandler(fileName);
//            try{
//                if(filePath != null){
//                    in = new FileInputStream(filePath);
//                }
//                res.setContentType("application/pdf");
//                //保存时的文件名
//                res.setHeader("Content-Disposition",
//                        "inline; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
//                out = res.getOutputStream();
//                byte[] b = new byte[1024];
//                int len = 0;
//                while((len = in.read(b)) != -1){
//                    out.write(b);
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }finally {
//                if(in != null){
//                    in.close();
//                }
//                if(out != null){
//                    out.close();
//                }
//            }
//        }
//
//        /**
//         * 文件处理
//         * @param fileName
//         * @return
//         */
//        private String fileHandler(String fileName){
//            String fileSuffix = Office2PDF.getFileSuffix(fileName);
//            System.out.println(fileSuffix);
//            if("pdf".equals(fileSuffix))
//            {
//                return BASE_PATH + fileName;
//            }
//            else
//            {
//                //生成的pdf储存路径，BASE_PATH + fileName
//                return Office2PDF.openOfficeToPDF(BASE_PATH + fileName).getAbsolutePath();
//            }
//
//        }


}


