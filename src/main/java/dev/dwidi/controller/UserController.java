package dev.dwidi.controller;

import dev.dwidi.dto.PublicResponseDTO;
import dev.dwidi.dto.user.UserRequestDTO;
import dev.dwidi.dto.user.UserResponseDTO;
import dev.dwidi.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/api")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/users/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PublicResponseDTO<UserResponseDTO> createUser(UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }

    @GET
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PublicResponseDTO<UserResponseDTO> getUserById(@PathParam("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @PUT
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PublicResponseDTO<UserResponseDTO> updateUser(@PathParam("userId") Long userId, UserRequestDTO userRequestDTO) {
        return userService.updateUser(userId, userRequestDTO);
    }

    @DELETE
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PublicResponseDTO<UserResponseDTO> deleteUser(@PathParam("userId") Long userId) {
        return userService.deleteUser(userId);
    }
}
