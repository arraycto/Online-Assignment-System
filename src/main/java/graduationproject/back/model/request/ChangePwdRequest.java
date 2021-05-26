package graduationproject.back.model.request;

/**
 * @Author Pan
 * @Date 2020/11/30 19:15
 */
public class ChangePwdRequest {

    private String oldPwd;

    private String newPwd;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public boolean checkJson(){
        if(oldPwd == null||newPwd == null){
            return false;
        }
        return true;
    }
}
