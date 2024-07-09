package controller;

import java.lang.System.Logger;
import java.util.logging.LogManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;


    private static final Logger logger = LogManager.getLogger(AuthController.class);

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String email, @RequestParam String password, RedirectAttributes redirect) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            redirect.addFlashAttribute("loginSuccess", "<i class=\"bi bi-stars\"></i> Bienveido(a) " + email + "!");
            return "redirect:/dashboard";
        } catch (BadCredentialsException e) {
            logger.error("Error authenticating user", e);
            redirect.addFlashAttribute("loginError", e.getMessage());
            return "redirect:/auth/login?error=true";
        }

    }

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request, Authentication authentication, RedirectAttributes redirectAttributes) {

        if (authentication != null && authentication.isAuthenticated()) {
            redirectAttributes.addFlashAttribute("message", "Ya estás autenticado");
            logger.info("User already authenticated");
            return "redirect:/dashboard";
        }

        if (request.getSession().getAttribute("loginError") != null) {
            model.addAttribute("loginError", request.getSession().getAttribute("loginError"));
            request.getSession().removeAttribute("loginError");
        }

        return "views/public/login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            new CookieClearingLogoutHandler("JSESSIONID").logout(request, response, authentication);

        }
        logger.info("User logged out");
        return "redirect:/";
    }

}

