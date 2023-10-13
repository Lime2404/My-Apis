package SimpleApis;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    private String username;


    @JsonProperty("password")
    public String getPassword(){
        return password;
    }
    private String password;


    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    @JsonProperty("username")
    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }
}
