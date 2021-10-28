package com.revature.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.ClaimController;
import com.revature.controller.EmployeeController;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App {

	

	public static void main(String[] args) {
		
		Logger log= LoggerFactory.getLogger(App.class);
		// TODO Auto-generated method stub
		Javalin app = Javalin.create(config -> {
		    config.addStaticFiles("/public", Location.CLASSPATH); 
		    config.enableCorsForAllOrigins();})   
			.start(7000);
		log.info("App has started");
		//creates endpoints in javalin
		
		//app.get("welcome", ctx->ctx.html("<h1>Welcome to Javalin</h1>"));
		
		//app.get("somedata/{name}", ctx->ctx.html("<h1>Welcome " + ctx.pathParam("name") +" to Javalin.</h1>"));
		app.get("/users/{userName}", EmployeeController.fetchByUsername);
		app.get("/users", EmployeeController.fetchAllUsernames);
		
		app.get("/claims", ClaimController.fetchAllClaims);
		app.get("/claims/{userName}", ClaimController.fetchByUserName);
		app.post("/new-claim", ClaimController.addClaim);
		app.post("/check-credentials", EmployeeController.checkCredentials);
		app.post("/update-claim", ClaimController.updateClaim);
		
	}

}
