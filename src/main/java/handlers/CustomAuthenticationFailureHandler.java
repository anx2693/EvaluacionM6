package handlers;



import java.io.IOException;
import java.net.http.HttpResponse;

import javax.naming.AuthenticationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.util.Instantiator.FailureHandler;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

public class CustomAuthenticationFailureHandler implements FailureHandler{

    // Logger
    private static final Logger logger = LogManager.getLogger(CustomAuthenticationFailureHandler.class);

    @SuppressWarnings("rawtypes")
	public void onAuthenticationFailure(AbstractMultipartHttpServletRequest request, HttpResponse response, AuthenticationException exception) throws IOException {
        logger.error("Error authenticating user onAuthenticationFailure", exception);
        request.get().setAttribute("loginError", "Credenciales inválidas. Intente de nuevo.");
        response.sslSession(); //response.sendRedirect request).getSession
    }

	@Override
	public void handleFailure(Class<?> type, String implementationName, Throwable failure) {
		// TODO Auto-generated method stub
		
	}
}
