package graduationproject.back.mapper;

import graduationproject.back.model.entity.Files;
import graduationproject.back.model.vo.AllStudentUploadFileVo;
import graduationproject.back.model.vo.UploadFileVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilesMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(Files record);

    int insertSelective(Files record);

    Files selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(Files record);

    int updateByPrimaryKey(Files record);

    int saveUploadFile(Files files);

    Files selectFilePathByFileld(Integer fileId);

    List<UploadFileVo> stuGetUploadFileByUserIdAndCourseId(@Param("userId") String userId, @Param("courseId") Integer courseId);

    List<AllStudentUploadFileVo> getStudentFileListByUserId(String userId, Integer courseId);
}