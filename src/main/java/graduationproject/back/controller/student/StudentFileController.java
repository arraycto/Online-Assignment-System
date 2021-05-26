package graduationproject.back.controller.student;

import graduationproject.back.model.vo.UploadFileVo;
import graduationproject.back.service.FileService;
import graduationproject.back.utils.JsonData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Pan
 * @Date 2020/12/13 22:31
 */
@RestController
@RequestMapping("api/student/file")
public class StudentFileController {

    private final FileService fileService;

    public StudentFileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * TODO 好像没用到 😓 待排查
     * @param request
     * @param courseId
     * @return
     */
    @GetMapping("getUploadFile")
    public JsonData getUploadFile(HttpServletRequest request,@RequestParam(value = "courseId",required = true)Integer courseId)
    {
        String userId = (String) request.getAttribute("userId");

        List<UploadFileVo> list = fileService.stuGetUploadFileByUserIdAndCourseId(userId ,courseId);

        return list != null ? JsonData.buildSuccess(list,"获取成功") : JsonData.buildError("获取失败");
    }


    /**
     * 单文件上传
     * @param request
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    public JsonData upload(HttpServletRequest request, @RequestParam("file") MultipartFile file , @RequestParam(value = "courseId",required = true)Integer courseId,@RequestParam(value = "assignmentId",required = true)Integer assignmentId) {
        String userId = (String) request.getAttribute("userId");
        //String errorMsg = FileUtil.uploadFile(userId,file);
        int result = fileService.uploadFile(userId,file,courseId,assignmentId);
        return result == 1? JsonData.buildSuccess("上传成功") : JsonData.buildError("上传失败");
    }

    /**
     * 多文件上传
     * @param request
     * @return
     */
    // 文件批量上传
    @PostMapping("/batchUploadFile")
    public JsonData handleFileUpload(HttpServletRequest request,@RequestParam(value = "courseId",required = true)Integer courseId,@RequestParam(value = "assignmentId",required = true)Integer assignmentId) {
        String userId = (String) request.getAttribute("userId");

        //
        // String errorMsg = FileUtil.batchUploadFile(userId,request);
        int result = fileService.batchUploadFile(userId,request,courseId,assignmentId);
        return result ==1 ? JsonData.buildSuccess("上传成功") : JsonData.buildError("上传失败");
    }
}
