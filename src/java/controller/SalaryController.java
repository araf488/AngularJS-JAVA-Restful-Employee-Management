/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Salary;
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
import model.SalaryService;

/**
 *
 * @author B14
 */
@Path("/salary")
public class SalaryController {
    Salary salary = new Salary();
    SalaryService salaryService = new SalaryService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> getSalarys() {

        List<Map<String, String>> listOfSalarys = salaryService.getAllSalarys();
        return listOfSalarys;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Salary getSalaryById(@PathParam("id") int id) {
        return salaryService.getSalary(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Salary addProduct(Salary salary) {
        return salaryService.addSalary(salary);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Salary updateProduct(Salary salary) {
        return salaryService.updateSalary(salary);

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteSalary(@PathParam("id") int id) {
        salaryService.deleteSalary(id);

    }

}
