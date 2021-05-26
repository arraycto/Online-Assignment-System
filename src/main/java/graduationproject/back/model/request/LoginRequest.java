package graduationproject.back.model.request;

/**
 * @Author Pan
 * @Date 2020/11/28 20:22
 */
public class LoginRequest {
    private String studentId;
    private String pwd;
    private int accountType;

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public LoginRequest(String studentId, String pwd) {
        this.studentId = studentId;
        this.pwd = pwd;
    }
}
