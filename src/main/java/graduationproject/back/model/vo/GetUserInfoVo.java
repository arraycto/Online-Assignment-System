package graduationproject.back.model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Pan
 * @Date 2021/5/5 16:12
 */
public class GetUserInfoVo {

    private String userId;

    private String name;

    private String accountType;

    private String accountDisabled;

    private Date createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountDisabled() {
        return accountDisabled;
    }

    public void setAccountDisabled(String accountDisabled) {
        this.accountDisabled = accountDisabled;
    }

    public String getCreateTime() {
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        return sdf.format(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
