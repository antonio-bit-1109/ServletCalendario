package dto;

public class UtenteDTO {

    private String id;
    private String Username;
    private String Password;

    public void setId(int id) {
        this.id = Integer.toString(id);
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setUsername(String username) {
        Username = username;
    }


    //costrutt
    public UtenteDTO() {
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }
}
