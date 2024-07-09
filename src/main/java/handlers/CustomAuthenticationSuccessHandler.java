package handlers;


import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @SuppressWarnings("rawtypes")
	@Override
    public void onAuthenticationSuccess(AbstractMultipartHttpServletRequest request, HttpResponse response, Authentication authentication) throws IOException, ServletException {
        // Add message to the session
        request.getSession().setAttribute("loginSuccess", "Bienvenido(a) a vWallet Beta!");
        response.sendRedirect("/dashboard");
    }
}