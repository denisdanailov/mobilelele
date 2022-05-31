package mobilelele.example.mobilelele.web;

import mobilelele.example.mobilelele.model.binding.UserLoginBindingModel;
import mobilelele.example.mobilelele.model.binding.UserRegistrationBindingModel;
import mobilelele.example.mobilelele.model.service.UserLoginServiceModel;
import mobilelele.example.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class UserLoginController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserLoginController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute(value = "userModel")
    public UserRegistrationBindingModel userModel() {
        return new UserRegistrationBindingModel();
    }

    @GetMapping("/users/login")
    public String login(Model model) {

        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }

        return "auth-login";
    }

    @PostMapping("/users/login")
    public String loginUser(@Valid UserLoginBindingModel userModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:login";
        }

        UserLoginServiceModel userLoginServiceModel = userService.findByUsernameAndPassword(userModel.getUsername(), userModel.getPassword());

        if (userLoginServiceModel == null) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("isFound", false);

            return "redirect:login";
        }

        userService.login(userLoginServiceModel.getId(), userLoginServiceModel.getUsername());

        return "redirect:/";
    }

    @GetMapping("/users/logout")
    public String logout() {

        userService.logout();
        return "redirect:/";
    }

}
