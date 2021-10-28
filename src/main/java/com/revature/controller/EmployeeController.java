package com.revature.controller;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.util.log.Log;

import com.google.gson.Gson;
import com.revature.dao.EmployeeDAO;
import com.revature.model.Employee;

import io.javalin.http.Handler;

public class EmployeeController {
private static Gson gson = new Gson();
	
	public static Handler fetchAllUsernames = ctx -> {
		EmployeeDAO dao = EmployeeDAO.instance();
		Iterable<String> allUsers = dao.getAllNames();
		ctx.json(allUsers);
	};

	public static Handler fetchByUsername = ctx -> {
		String userName = Objects.requireNonNull(ctx.pathParam("userName"));
		EmployeeDAO dao = EmployeeDAO.instance();
		Employee employee = null;
		try {
		employee = dao.getEmployeeByUsername(userName).get();
		
		}
		catch(NoSuchElementException e) {
			
		}
		
		if (employee == null) {
			ctx.html("Not Found");
		} else {
			ctx.json(employee);
		}
	};
	
	public static Handler addEmployee = ctx -> {
		String body = ctx.body();
		EmployeeDAO dao = EmployeeDAO.instance();
		try {
			Employee employee = gson.fromJson(body, Employee.class);
			if (employee != null) {
				Employee returned = dao.addEmployee(employee);
				ctx.result(gson.toJson(returned));
				ctx.status(200);
			} else
				ctx.status(404);

		} catch (Exception e) {
			ctx.status(404);
			e.printStackTrace();
		}
	};
	public static Handler checkCredentials = ctx -> {
		String userName = ctx.formParam("name");
		String password = ctx.formParam("password");
		
		EmployeeDAO dao = EmployeeDAO.instance();
		Employee employee = null;
		try {
		employee = dao.getEmployeeByUsername(userName).get();
		if(employee.getPassword().contentEquals(password)) {
			ctx.html("You have successfully logged in.");
			TimeUnit.SECONDS.sleep(1);
			//ctx.sessionAttribute("user_name", userName);	
			ctx.clearCookieStore();
			ctx.cookie("user_name", userName);
			
			if(!employee.getAdmin()) {
				ctx.redirect("employee.html");
			}else {
				ctx.redirect("manager.html");
			}
			
		}
		}
		catch(NoSuchElementException e) {
			
		}
		
		if (employee == null || !employee.getPassword().contentEquals(password)) {
			ctx.html("Not Found");
		} else {
			ctx.html("You have successfully logged in.");
		}
		
	};

}
