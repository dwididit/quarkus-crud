package dev.dwidi.controller;

import dev.dwidi.dto.PublicResponseDTO;
import dev.dwidi.dto.user.UserRequestDTO;
import dev.dwidi.dto.user.UserResponseDTO;
import dev.dwidi.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UserControllerTest {

    @Mock
    private UserService mockUserService;

    private UserController userControllerUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        userControllerUnderTest = new UserController(mockUserService);
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
        userResponseDTO.setUserId(0L);
        userResponseDTO.setUsername("username");
        userResponseDTO.setEmail("email");
        userResponseDTO.setPhoneNumber("phoneNumber");
        final PublicResponseDTO<UserResponseDTO> expectedResult = new PublicResponseDTO<>(0, "message",
                userResponseDTO);

        // Configure UserService.createUser(...).
        final UserResponseDTO userResponseDTO1 = new UserResponseDTO();
        userResponseDTO1.setUserId(0L);
        userResponseDTO1.setUsername("username");
        userResponseDTO1.setEmail("email");
        userResponseDTO1.setPhoneNumber("phoneNumber");
        final PublicResponseDTO<UserResponseDTO> userResponseDTOPublicResponseDTO = new PublicResponseDTO<>(0,
                "message", userResponseDTO1);
        when(mockUserService.createUser(new UserRequestDTO("username", "email", "phoneNumber")))
                .thenReturn(userResponseDTOPublicResponseDTO);

        // Run the test
        final PublicResponseDTO<UserResponseDTO> result = userControllerUnderTest.createUser(userRequestDTO);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetUserById() {
        // Setup
        final UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(0L);
        userResponseDTO.setUsername("username");
        userResponseDTO.setEmail("email");
        userResponseDTO.setPhoneNumber("phoneNumber");
        final PublicResponseDTO<UserResponseDTO> expectedResult = new PublicResponseDTO<>(0, "message",
                userResponseDTO);

        // Configure UserService.getUserById(...).
        final UserResponseDTO userResponseDTO1 = new UserResponseDTO();
        userResponseDTO1.setUserId(0L);
        userResponseDTO1.setUsername("username");
        userResponseDTO1.setEmail("email");
        userResponseDTO1.setPhoneNumber("phoneNumber");
        final PublicResponseDTO<UserResponseDTO> userResponseDTOPublicResponseDTO = new PublicResponseDTO<>(0,
                "message", userResponseDTO1);
        when(mockUserService.getUserById(0L)).thenReturn(userResponseDTOPublicResponseDTO);

        // Run the test
        final PublicResponseDTO<UserResponseDTO> result = userControllerUnderTest.getUserById(0L);

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
        final PublicResponseDTO<UserResponseDTO> expectedResult = new PublicResponseDTO<>(0, "message",
                userResponseDTO);

        // Configure UserService.updateUser(...).
        final UserResponseDTO userResponseDTO1 = new UserResponseDTO();
        userResponseDTO1.setUserId(0L);
        userResponseDTO1.setUsername("username");
        userResponseDTO1.setEmail("email");
        userResponseDTO1.setPhoneNumber("phoneNumber");
        final PublicResponseDTO<UserResponseDTO> userResponseDTOPublicResponseDTO = new PublicResponseDTO<>(0,
                "message", userResponseDTO1);
        when(mockUserService.updateUser(0L, new UserRequestDTO("username", "email", "phoneNumber")))
                .thenReturn(userResponseDTOPublicResponseDTO);

        // Run the test
        final PublicResponseDTO<UserResponseDTO> result = userControllerUnderTest.updateUser(0L, userRequestDTO);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testDeleteUser() {
        // Setup
        final UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(0L);
        userResponseDTO.setUsername("username");
        userResponseDTO.setEmail("email");
        userResponseDTO.setPhoneNumber("phoneNumber");
        final PublicResponseDTO<UserResponseDTO> expectedResult = new PublicResponseDTO<>(0, "message",
                userResponseDTO);

        // Configure UserService.deleteUser(...).
        final UserResponseDTO userResponseDTO1 = new UserResponseDTO();
        userResponseDTO1.setUserId(0L);
        userResponseDTO1.setUsername("username");
        userResponseDTO1.setEmail("email");
        userResponseDTO1.setPhoneNumber("phoneNumber");
        final PublicResponseDTO<UserResponseDTO> userResponseDTOPublicResponseDTO = new PublicResponseDTO<>(0,
                "message", userResponseDTO1);
        when(mockUserService.deleteUser(0L)).thenReturn(userResponseDTOPublicResponseDTO);

        // Run the test
        final PublicResponseDTO<UserResponseDTO> result = userControllerUnderTest.deleteUser(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
