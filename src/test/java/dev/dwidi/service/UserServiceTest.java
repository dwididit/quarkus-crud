package dev.dwidi.service;

import dev.dwidi.dto.PublicResponseDTO;
import dev.dwidi.dto.user.UserRequestDTO;
import dev.dwidi.dto.user.UserResponseDTO;
import dev.dwidi.entity.User;
import dev.dwidi.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    private UserService userServiceUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testCreateUser() {
        // Setup
        final UserRequestDTO userRequestDTO = new UserRequestDTO("username", "email", "phoneNumber");
        final UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(null); // Change this to null
        userResponseDTO.setUsername("username");
        userResponseDTO.setEmail("email");
        userResponseDTO.setPhoneNumber("phoneNumber");
        final PublicResponseDTO<UserResponseDTO> expectedResult = new PublicResponseDTO<>(201, "User created successfully",
                userResponseDTO);

        // Run the test
        final PublicResponseDTO<UserResponseDTO> result = userServiceUnderTest.createUser(userRequestDTO);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockUserRepository).persist(any(User.class));
    }

    @Test
    void testGetUserById() {
        // Setup
        final UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(0L);
        userResponseDTO.setUsername("username");
        userResponseDTO.setEmail("email");
        userResponseDTO.setPhoneNumber("phoneNumber");
        final PublicResponseDTO<UserResponseDTO> expectedResult = new PublicResponseDTO<>(200, "User retrieved successfully",
                userResponseDTO);

        // Configure UserRepository.findByUserId(...).
        final User user = new User();
        user.setUserId(0L);
        user.setUsername("username");
        user.setEmail("email");
        user.setPhoneNumber("phoneNumber");
        when(mockUserRepository.findByUserId(0L)).thenReturn(user);

        // Run the test
        final PublicResponseDTO<UserResponseDTO> result = userServiceUnderTest.getUserById(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetUserById_UserRepositoryReturnsNull() {
        // Setup
        final PublicResponseDTO<UserResponseDTO> expectedResult = new PublicResponseDTO<>(404, "User not found", null);
        when(mockUserRepository.findByUserId(0L)).thenReturn(null);

        // Run the test
        final PublicResponseDTO<UserResponseDTO> result = userServiceUnderTest.getUserById(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testUpdateUser() {
        // Setup
        final UserRequestDTO userRequestDTO = new UserRequestDTO("username", "email", "phoneNumber");
        final UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(0L);
        userResponseDTO.setUsername("username");
        userResponseDTO.setEmail("email");
        userResponseDTO.setPhoneNumber("phoneNumber");
        final PublicResponseDTO<UserResponseDTO> expectedResult = new PublicResponseDTO<>(200, "User updated successfully",
                userResponseDTO);

        // Configure UserRepository.findById(...).
        final User user = new User();
        user.setUserId(0L);
        user.setUsername("username");
        user.setEmail("email");
        user.setPhoneNumber("phoneNumber");
        when(mockUserRepository.findById(0L)).thenReturn(user);

        // Run the test
        final PublicResponseDTO<UserResponseDTO> result = userServiceUnderTest.updateUser(0L, userRequestDTO);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockUserRepository).persist(any(User.class));
    }

    @Test
    void testUpdateUser_UserRepositoryFindByIdReturnsNull() {
        // Setup
        final UserRequestDTO userRequestDTO = new UserRequestDTO("username", "email", "phoneNumber");
        final PublicResponseDTO<UserResponseDTO> expectedResult = new PublicResponseDTO<>(404, "User not found", null);
        when(mockUserRepository.findById(0L)).thenReturn(null);

        // Run the test
        final PublicResponseDTO<UserResponseDTO> result = userServiceUnderTest.updateUser(0L, userRequestDTO);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testDeleteUser() {
        // Setup
        final PublicResponseDTO<UserResponseDTO> expectedResult = new PublicResponseDTO<>(200, "User deleted successfully", null);

        // Configure UserRepository.findById(...).
        final User user = new User();
        user.setUserId(0L);
        user.setUsername("username");
        user.setEmail("email");
        user.setPhoneNumber("phoneNumber");
        when(mockUserRepository.findById(0L)).thenReturn(user);

        // Run the test
        final PublicResponseDTO<UserResponseDTO> result = userServiceUnderTest.deleteUser(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockUserRepository).deleteById(0L);
    }

    @Test
    void testDeleteUser_UserRepositoryFindByIdReturnsNull() {
        // Setup
        final PublicResponseDTO<UserResponseDTO> expectedResult = new PublicResponseDTO<>(404, "User not found", null);
        when(mockUserRepository.findById(0L)).thenReturn(null);

        // Run the test
        final PublicResponseDTO<UserResponseDTO> result = userServiceUnderTest.deleteUser(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}