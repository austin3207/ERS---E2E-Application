package com.revature.model;

import java.util.List;


public class Employee {
	
		private String userName;
		private String password;
		private String name;
		private boolean admin;
		List<Claim> employee_claims;
		
		public Employee(String username, String name, boolean admin, List<Claim> employee_claims) {
			super();
			this.userName = username;
			this.name = name;
			this.admin = admin;
			this.employee_claims = employee_claims;
		}
		public Employee(String username, String name, boolean admin) {
			super();
			this.userName = username;
			this.name = name;
			this.admin = admin;
		}
		public Employee(String username) {
			super();
			this.userName = username;
		}

		public String getUsername() {
			return userName;
		}

		public void setUsername(String username) {
			this.userName = username;
		}

		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean getAdmin() {
			return admin;
		}

		public void setAdmin(boolean admin) {
			this.admin = admin;
		}

		public List<Claim> getEmployee_claims() {
			return employee_claims;
		}

		public void setEmployee_claims(List<Claim> employee_claims) {
			this.employee_claims = employee_claims;
		}
		
		
		
		

}
