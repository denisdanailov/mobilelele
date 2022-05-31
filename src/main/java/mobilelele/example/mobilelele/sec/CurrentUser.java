package mobilelele.example.mobilelele.sec;

import mobilelele.example.mobilelele.model.entity.UserRole;
import mobilelele.example.mobilelele.model.entity.enums.RoleEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.HashSet;
import java.util.Set;

@Component
@SessionScope
public class CurrentUser {

    private Long id;
    private String username;
    private boolean isLoggedIn;
    private Set<RoleEnum> roles = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public CurrentUser() {
    }

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public CurrentUser setUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public CurrentUser addRole(RoleEnum role) {
        roles.add(role);
        return this;
    }

    public boolean isAdmin() {
        return roles.contains(RoleEnum.ADMIN);
    }

    public CurrentUser clearRoles() {
        roles.clear();
        return this;
    }

    public void clean() {
        setLoggedIn(false)
                .setUsername(null)
                .setId(null)
                .clearRoles();
    }
}
