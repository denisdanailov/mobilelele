package mobilelele.example.mobilelele.model.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegistrationBindingModel {

    @NotEmpty
    @Size(min = 5, max = 30)
    private String firstName;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String lastName;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String username;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String password;

    @NotEmpty
    @Size(min = 5, max = 30)
    private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
