package com.otziv.krya.Controllers;

import com.otziv.krya.service.UserService;
import com.otziv.krya.domain.entity.ApplicationUser;
import com.otziv.krya.repository.UserRepository;
//import com.otziv.krya.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @GetMapping("/login")
    public String getLoginPage() {

        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }
    private final UserRepository userRepository;
    @GetMapping(path="/all")
    public @ResponseBody Iterable<ApplicationUser> getAllUsers() {
        // This returns a JSON or XML with the users

        return userRepository.findAll();
    }
    @GetMapping("/register")
    public String register(final Model model){
        model.addAttribute("ApplicationUser", new ApplicationUser());
        return "/register";
    }

    @PostMapping("/register")
    public String userRegistration(final ApplicationUser userData, final BindingResult bindingResult,final Model model){

        model.addAttribute("ApplicationUser", userData);

        try {
            userService.register(userData);
        }catch (Exception e){
            bindingResult.rejectValue("email", "userData.email","An account already exists for this email.");
            model.addAttribute("registrationForm", userData);
            return "/register";
        }
        return "/success";
    }
}
