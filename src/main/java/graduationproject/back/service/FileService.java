package graduationproject.back.service;

import graduationproject.back.model.vo.AllStudentUploadFileVo;
import graduationproject.back.model.vo.UploadFileVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author Pan
 * @Date 2020/12/12 11:36
 */
public interface FileService {
    int uploadFile(String userId, MultipartFile file, Integer courseId, Integer assignmentId);

    int batchUploadFile(String userId, HttpServletRequest request, Integer courseId, Integer assignmentId);

    String downloadFile(HttpServletResponse response, Integer fileId);

    List<UploadFileVo> stuGetUploadFileByUserIdAndCourseId(String userId, Integer courseId);

    List<AllStudentUploadFileVo> getStudentFileListByUserId(String userId, Integer courseId);
}
