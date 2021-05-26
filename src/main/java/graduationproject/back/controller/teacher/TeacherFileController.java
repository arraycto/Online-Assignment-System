package graduationproject.back.controller.teacher;

import graduationproject.back.model.vo.AllStudentUploadFileVo;
import graduationproject.back.service.CourseService;
import graduationproject.back.service.FileService;
import graduationproject.back.service.UserService;
import graduationproject.back.utils.FileUtil;
import graduationproject.back.utils.JsonData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author Pan
 * @Date 2020/12/13 22:32
 */
@RestController
@RequestMapping("api/teacher/file")
public class TeacherFileController {

    private final UserService userService;
    private final FileService fileService;
    private final CourseService courseService;

    public TeacherFileController(FileService fileService, CourseService courseService, UserService userService) {
        this.fileService = fileService;
        this.courseService = courseService;
        this.userService = userService;
    }

    /**
     * Excel文件导入
     * @param file
     * @param courseId
     * @return
     */
    @PostMapping("excelImport")
    public JsonData addUser(@RequestParam("file") MultipartFile file, @RequestParam(value = "courseId",required = true)Integer courseId) {

        boolean result = false;
        String fileName = file.getOriginalFilename();
        result = courseService.batchImportExcel(fileName, file ,courseId);
        return result ? JsonData.buildSuccess("插入成功") : JsonData.buildError("插入失败");
    }

    /**
     * 文件下载
     * @param request
     * @param response
     * @param fileId
     * @return
     * @throws IOException
     */
    @GetMapping("/downloadfile")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "fileId",required = true)Integer fileId) {

        return fileService.downloadFile(response,fileId);
    }

    /**
     * 获得当前课程中学生的文件列表
     * @param userId
     * @return
     */
    @GetMapping("getStudentFileList")
    public JsonData getStudentFileList(@RequestParam(value = "userId",required = true)String userId,@RequestParam(value = "courseId",required = true)Integer courseId){

        List<AllStudentUploadFileVo> list = fileService.getStudentFileListByUserId(userId.toUpperCase(),courseId);
        return list != null ? JsonData.buildSuccess(list,"获取成功") : JsonData.buildError("获取失败");
    }

    @GetMapping("readFile")
    public JsonData readFile(@RequestParam(value = "filePath",required = true)String filePath){


        return JsonData.buildSuccess(FileUtil.readFile(filePath));
    }

    /**
     * 浏览office（word，ppt，excel）
     * @param fileName
     * @param filePath
     * @param res
     * @throws Exception
     */
    @RequestMapping("readOfficeFile")
    public void readOfficeFile(@RequestParam(value = "fileName",required = true)String fileName , @RequestParam(value = "filePath",required = true)String filePath,HttpServletResponse res)throws Exception{
        InputStream in = null;
        OutputStream out = null;

        filePath =FileUtil.getUploadPath()+filePath;

        //判断是pdf还是word还是excel
        //若是pdf直接读 否则转pdf 再读  //
        System.out.println(filePath);
        String Path =  FileUtil.fileHandler(fileName,filePath);
        try{
            if(Path != null){
                in = new FileInputStream(Path);
            }
            res.setContentType("application/pdf");
            //保存时的文件名
            res.setHeader("Content-Disposition",
                    "inline; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            out = res.getOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while((len = in.read(b)) != -1){
                out.write(b);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(in != null){
                in.close();
            }
            if(out != null){
                out.close();
            }
        }
    }
}
