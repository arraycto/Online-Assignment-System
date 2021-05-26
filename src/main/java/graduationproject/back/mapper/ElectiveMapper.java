package graduationproject.back.mapper;

import graduationproject.back.model.entity.Elective;
import org.apache.ibatis.annotations.Param;

public interface ElectiveMapper {
    int joinCourse(@Param("userId")String userId, @Param("courseId")Integer courseId);

    Elective selectByUserIdAndCourseId(@Param("userId")String userId, @Param("courseId")Integer courseId);

    void addElectiveByExcelBatchImport(@Param("userId")String userId, @Param("courseId")Integer courseId);
}