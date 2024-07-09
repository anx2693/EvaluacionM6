package serviceInterface;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.User;
import repository.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class IUserDetailsService implements UserDetailsService {

    private final Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);
    private final UserRepository userRepository;

    public IUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        logger.info("User found: {}", user.getEmail());
        return new CustomUserDetails(user);

    }
}
