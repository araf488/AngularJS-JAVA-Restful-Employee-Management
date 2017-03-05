/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.User;
import java.util.List;
import java.util.Map;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.UserService;

/**
 *
 * @author B14
 */
@Path("/user")
public class UserController {
    User user = new User();
    UserService userService = new UserService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> getUser() {

        List<Map<String, String>> listOfUsers = userService.getAllUsers();
        return listOfUsers;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("id") int id) {
        return userService.getUser(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(User user) {
        return userService.addUser(user);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(User user) {
        return userService.updateUser(user);

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUser(@PathParam("id") int id) {
        userService.deleteUser(id);

    }

}
