package serviceInterface;

import org.springframework.stereotype.Service;

import model.User;
import repository.UserRepository;
import service.UserService;

import java.util.List;

@Service
public class IUserService implements UserService {

    private final UserRepository userRepository;

    public IUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(int id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(int id) {

    }
}