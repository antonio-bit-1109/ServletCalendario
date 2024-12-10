package dto;

public class UtenteDTO {

    private String Username;
    private String Password;

    public void setPassword(String password) {
        Password = password;
    }

    public void setUsername(String username) {
        Username = username;
    }


    //costrutt
    public UtenteDTO() {
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }
}
