/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Employee;
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
import model.EmployeeService;

/**
 *
 * @author B14
 */
@Path("/employee")
public class EmployeeController {
    Employee employee = new Employee();
    EmployeeService employeeService = new EmployeeService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> getEmployees() {

        List<Map<String, String>> listOfEmployees = employeeService.getAllEmployees();
        return listOfEmployees;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployeeById(@PathParam("id") int id) {
        return employeeService.getEmployee(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Employee addProduct(Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Employee updateProduct(Employee Employee) {
        return employeeService.updateEmployee(employee);

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteEmployee(@PathParam("id") int id) {
        employeeService.deleteEmployee(id);

    }

}
