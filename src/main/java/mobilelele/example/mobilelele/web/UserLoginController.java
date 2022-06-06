package mobilelele.example.mobilelele.web;

import mobilelele.example.mobilelele.model.binding.UserLoginBindingModel;
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

    @ModelAttribute(value = "userLoginModel")
    public UserLoginBindingModel userLoginModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/users/login")
    public String login(Model model) {

        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
            model.addAttribute("userLoginModel", userLoginModel());
        }

        return "auth-login";
    }

    @PostMapping("/users/login")
    public String loginUser(@Valid UserLoginBindingModel userLoginModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        UserLoginServiceModel userLoginServiceModel = userService.findByUsernameAndPassword(userLoginModel.getUsername(), userLoginModel.getPassword());

        if (bindingResult.hasErrors() || userLoginServiceModel == null) {

            redirectAttributes.addFlashAttribute("userLoginModel", userLoginModel);
            redirectAttributes.addFlashAttribute("isFound", false);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginModel", bindingResult);

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
