package graduationproject.back.model.entity;

public class UserStatus {
    private Integer id;

    private String userId;

    private String name;

    private String accountType;

    private String accountDisabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public String getAccountDisabled() {
        return accountDisabled;
    }

    public void setAccountDisabled(String accountDisabled) {
        this.accountDisabled = accountDisabled == null ? null : accountDisabled.trim();
    }
}