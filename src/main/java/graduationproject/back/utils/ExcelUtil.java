package graduationproject.back.utils;

import graduationproject.back.exception.BusinessException;
import graduationproject.back.exception.enums.CommonEnum;
import graduationproject.back.model.entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Pan
 * @Date 2020/12/8 21:35
 */
public class ExcelUtil {

    public List<User> getExcelData(String fileName, MultipartFile file) throws IOException {
            List<User> userList = new ArrayList<>();
            if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
                throw new BusinessException(CommonEnum.EXCEL_UPLOAD_FORMAT_ERROR);
            }
            boolean isExcel2003 = true;
            if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
                isExcel2003 = false;
            }
            InputStream is = file.getInputStream();
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
            Sheet sheet = wb.getSheetAt(0);
            if (sheet == null) {
                return null;
            }
            User user;
            for (int r = 3; r <= (sheet != null ? sheet.getLastRowNum() : 0); r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }

                user = new User();

                /**
                 * 读取姓名
                 */
                if (row.getCell(2).getCellTypeEnum() != CellType.STRING) {
                    throw new BusinessException("导入失败(第" + (r + 1) + "行,姓名请设为文本格式)");
                }
                String name = row.getCell(2).getStringCellValue();
                if (name == null || name.isEmpty()) {
                    throw new BusinessException("导入失败(第" + (r + 1) + "行,姓名未填写)");
                }

                /**
                 * 设置学号一列为字符串
                 */
                row.getCell(1).setCellType(CellType.STRING);
                String userId = row.getCell(1).getStringCellValue();

                if (userId == null || userId.isEmpty()) {
                    throw new BusinessException("导入失败(第" + (r + 1) + "行,电话未填写)");
                }
                user.setName(name);
                user.setUserId(userId);
                user.setPwd(CommonUtils.MD5(userId));
                user.setCreateTime(new Date());
                user.setAccountType("0");
                userList.add(user);
            }
       return userList;
    }
}
