package mobilelele.example.mobilelele.service.impl;

import mobilelele.example.mobilelele.model.entity.UserEntity;
import mobilelele.example.mobilelele.model.entity.UserRole;
import mobilelele.example.mobilelele.model.entity.enums.RoleEnum;
import mobilelele.example.mobilelele.model.service.UserLoginServiceModel;
import mobilelele.example.mobilelele.model.service.UserRegistrationServiceModel;
import mobilelele.example.mobilelele.repository.RoleRepository;
import mobilelele.example.mobilelele.repository.UserRepository;
import mobilelele.example.mobilelele.sec.CurrentUser;
import mobilelele.example.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;

    }

    @Override
    public void register(UserRegistrationServiceModel userRegistrationServiceModel) {

        UserEntity newUser = new UserEntity();
        UserRole userRole = roleRepository.findByRole(RoleEnum.USER);

        newUser
                .setUsername(userRegistrationServiceModel.getUsername())
                .setFirstName(userRegistrationServiceModel.getFirstName())
                .setLastName(userRegistrationServiceModel.getLastName())
                .setPassword(userRegistrationServiceModel.getPassword())
                .setConfirmPassword(userRegistrationServiceModel.getConfirmPassword())
                .setActive(true)
                .setUserRoles(Set.of(userRole));

        newUser = userRepository.save(newUser);
    }

    @Override
    public void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRole adminRole = roleRepository.findByRole(RoleEnum.ADMIN);
            UserRole userRole = roleRepository.findByRole(RoleEnum.USER);

            UserEntity admin = new UserEntity();
           admin.setActive(true)
                   .setFirstName("Admin")
                   .setLastName("Adminov")
                   .setUsername("admin")
                   .setImageUrl("https://d2gg9evh47fn9z.cloudfront.net/1600px_COLOURBOX9896883.jpg")
                   .setPassword("test")
                   .setConfirmPassword("test")
                           .setUserRoles(Set.of(userRole, adminRole));

           userRepository.save(admin);

            UserEntity peter = new UserEntity();
            peter.setActive(true)
                    .setFirstName("Peter")
                    .setLastName("Petrov")
                    .setUsername("pesho")
                    .setImageUrl("https://d2gg9evh47fn9z.cloudfront.net/1600px_COLOURBOX9896883.jpg")
                    .setPassword("test")
                    .setConfirmPassword("test")
                    .setUserRoles(Set.of(userRole));

            userRepository.save(peter);
        }

    }

    @Override
    public void initializeRoles() {
        if (roleRepository.count() == 0) {
            UserRole adminRole = new UserRole();
            adminRole.setRole(RoleEnum.ADMIN);

            UserRole userRole = new UserRole();
            userRole.setRole(RoleEnum.USER);

            roleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    @Override
    public UserLoginServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository
                .findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserLoginServiceModel.class))
                .orElse(null);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void login(Long id, String username) {

        Optional<UserEntity> userEntityOpt = userRepository.findByUsername(username);

        UserEntity loggedInUser = userEntityOpt.get();
        currentUser
                .setId(id)
                .setUsername(username)
                .setLoggedIn(true);

            loggedInUser.getUserRoles()
                    .forEach(r -> currentUser.addRole(r.getRole()));
    }

    @Override
    public void logout() {
        currentUser.clean();
    }
}
