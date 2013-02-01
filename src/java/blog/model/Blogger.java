package blog.model;

import com.sun.istack.Nullable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Walkman
 */
public class Blogger {
    @Size(min=3,max=20,message=
                "Username must be between 3 and 20 characters long.")
    @Pattern(regexp="^[a-zA-Z0-9]+$",
                message="Username must be alphanumeric with no spaces")
    private String login;
    
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
            message = "Invalid email address.")
    private String email;
    
    @Size(min=6,max=20,
            message="The password must be at least 6 characters long")
    private String password;
    
    private String imgURL;
    
    public Blogger() {
    }
    
    public Blogger(String login) {
        this.login = login;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    } 
}
