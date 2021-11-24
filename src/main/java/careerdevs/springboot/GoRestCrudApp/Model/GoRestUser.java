package careerdevs.springboot.GoRestCrudApp.Model;

public class GoRestUser {
    private Object meta;
    private Object data;
    private Long Id;
    private String name;
    private String email;
    private String gender;
    private String status;



    public GoRestUser(String name, String email, String gender, String status) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    public GoRestUser() {
    }

    public Long getId() {
        return Id;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
