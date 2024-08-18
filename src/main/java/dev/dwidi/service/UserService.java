package dev.dwidi.service;

import dev.dwidi.dto.PublicResponseDTO;
import dev.dwidi.dto.user.UserRequestDTO;
import dev.dwidi.dto.user.UserResponseDTO;
import dev.dwidi.entity.User;
import dev.dwidi.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public PublicResponseDTO<UserResponseDTO> createUser(UserRequestDTO userRequestDTO) {
        try {
            User user = new User();
            user.setUsername(userRequestDTO.getUsername());
            user.setEmail(userRequestDTO.getEmail());
            user.setPhoneNumber(userRequestDTO.getPhoneNumber());
            userRepository.persist(user);
            return new PublicResponseDTO<>(201, "User created successfully", new UserResponseDTO(user));
        } catch (jakarta.persistence.PersistenceException e) {
            return new PublicResponseDTO<>(400, "User already exist", null);
        }
    }

    @Transactional
    public PublicResponseDTO<UserResponseDTO> getUserById(Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return new PublicResponseDTO<>(404, "User not found", null);
        }
        return new PublicResponseDTO<>(200, "User retrieved successfully", new UserResponseDTO(user));
    }

    @Transactional
    public PublicResponseDTO<UserResponseDTO> updateUser(Long userId, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(userId);
        if (user == null) {
            return new PublicResponseDTO<>(404, "User not found", null);
        }
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        userRepository.persist(user);
        return new PublicResponseDTO<>(200, "User updated successfully", new UserResponseDTO(user));
    }

    @Transactional
    public PublicResponseDTO<UserResponseDTO> deleteUser(Long userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            return new PublicResponseDTO<>(404, "User not found", null);
        }
        userRepository.deleteById(userId);
        return new PublicResponseDTO<>(200, "User deleted successfully", null);
    }
}
