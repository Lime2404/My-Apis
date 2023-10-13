package SimpleApis;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {
    private String token;

    //геттеры и сеттеры

    @JsonProperty
    public String getToken() {
        return token;
    }
    public void setToken(String token){
        this.token = token;
    }
}
