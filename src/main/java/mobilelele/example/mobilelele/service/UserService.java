package mobilelele.example.mobilelele.service;

import mobilelele.example.mobilelele.model.entity.UserEntity;
import mobilelele.example.mobilelele.model.service.UserLoginServiceModel;
import mobilelele.example.mobilelele.model.service.UserRegistrationServiceModel;

import java.util.Optional;

public interface UserService {

    void initializeUsers();

    void initializeRoles();

    UserLoginServiceModel findByUsernameAndPassword(String username, String password);

    Optional<UserEntity> findByUsername(String username);

    void register(UserRegistrationServiceModel userRegistrationServiceModel);

    void login(Long id, String username);

    void logout();
}
