package mobilelele.example.mobilelele.web;

import mobilelele.example.mobilelele.model.binding.UserRegistrationBindingModel;
import mobilelele.example.mobilelele.model.service.UserRegistrationServiceModel;
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
public class UserRegistrationController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegistrationController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute(value = "userRegisterModel")
    public UserRegistrationBindingModel userRegisterModel() {
        return new UserRegistrationBindingModel();
    }

    @GetMapping("users/register")
    public String register(Model model) {

        if(!model.containsAttribute("userRegisterModel")) {
            model.addAttribute("userRegisterModel",userRegisterModel());
        }
        return "auth-register";
    }

    @PostMapping("users/register")
    public String userRegister(@Valid UserRegistrationBindingModel userRegisterModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userModel", userRegisterModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterModel", bindingResult);

            return "redirect:/register";
        }

        UserRegistrationServiceModel serviceModel = modelMapper.map(userRegisterModel, UserRegistrationServiceModel.class);
        userService.register(serviceModel);

        return "redirect:/users/login";
    }

    //    @InitBinder
//    public void InitBinder(WebDataBinder dataBinder) {
//        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
//        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
//    }
}
