package demoqa.project.api.dtos.requests;

import java.util.Map;

public class RequestUser {

    private String userName;
    private String password;

    public RequestUser() {
    }

    public RequestUser(Map<String, String> user) {
        this.userName = user.get("userName");
        this.password = user.get("password");
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
