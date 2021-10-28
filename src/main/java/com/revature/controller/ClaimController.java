package com.revature.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.revature.dao.ClaimDAO;
import com.revature.dao.EmployeeDAO;
import com.revature.model.Claim;
import com.revature.model.Employee;

import io.javalin.http.Handler;

public class ClaimController {
private static Gson gson = new Gson();
	
	public static Handler fetchAllClaims = ctx -> {
		ClaimDAO dao = ClaimDAO.instance();
		List<Claim> allClaims = dao.getAllClaims();
		ctx.json(allClaims);
	};

	public static Handler fetchByUserName = ctx -> {
		String userName = Objects.requireNonNull(ctx.pathParam("userName"));
		ClaimDAO dao = ClaimDAO.instance();
		List<Claim> claim = new ArrayList<Claim>();
		try {
		claim = dao.getClaimByUserName(userName);
		
		}
		catch(NoSuchElementException e) {
			
		}
		
		if (claim == null) {
			ctx.html("Not Found");
		} else {
			ctx.json(claim);
		}
	};
	
	public static Handler addClaim = ctx -> {
		String body = ctx.body();
		ClaimDAO dao = ClaimDAO.instance();
		try {
			String claim_reason = ctx.formParam("claim_reason");
			Double claim_amount = Double.parseDouble(ctx.formParam("claim_amount"));
			String username = ctx.cookie("user_name");
			
			Claim claim = new Claim(username, claim_amount, claim_reason);
			if (claim != null) {
				Claim returned = dao.addClaim(claim);
				ctx.result(gson.toJson(returned));
				ctx.status(200);
				TimeUnit.SECONDS.sleep(1);
				ctx.redirect("employee.html");
			} else
				ctx.status(404);

		} catch (Exception e) {
			ctx.status(404);
			e.printStackTrace();
		}
	};
	public static Handler updateClaim = ctx -> {
		String claim_id = ctx.formParam("claim_id");
		String approval = ctx.formParam("approval");
		ClaimDAO dao = ClaimDAO.instance();
		try {
			dao.approveClaim(claim_id, approval);
			TimeUnit.SECONDS.sleep(1);
			ctx.redirect("manager.html");
		} 
		catch(Exception e) {
			System.out.println("Failure");
		}
		
	};
}
