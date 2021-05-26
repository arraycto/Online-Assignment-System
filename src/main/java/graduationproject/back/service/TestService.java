package graduationproject.back.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Pan
 * @Date 2020/12/7 23:05
 */
public interface TestService {

    boolean batchImportExcel(String fileName, MultipartFile file ,Integer courseId);
}
