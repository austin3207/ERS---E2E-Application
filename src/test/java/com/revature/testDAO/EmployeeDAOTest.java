package com.revature.testDAO;

import com.revature.dao.EmployeeDAO;
import com.revature.model.Employee;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class EmployeeDAOTest extends TestCase{
	
	/**
	 * 
	 * @param testName
	 */
	public EmployeeDAOTest( String testName )
    {
        super( testName );
    }
	/**
	 * @return 
	 */
	public static Test suite() {
		return new TestSuite(EmployeeDAOTest.class);
	}
	
	EmployeeDAO dao = EmployeeDAO.instance();
	
	public void testGetEmployeeByUsername() throws Exception {
		String username = "not-a-username";
		assertTrue(dao.getEmployeeByUsername(username).isEmpty());
		
		username = "abc";
		assertTrue(dao.getEmployeeByUsername(username).isPresent());
		
		Employee employee = dao.getEmployeeByUsername(username).get();
		assertTrue(employee.getUsername().contentEquals(username));
		assertFalse(employee.getUsername().contentEquals("random-string-failure-testcase"));
	}
	
	public void testGetAllNames() throws Exception {
		//Test DB has 3 employee objects
		Iterable<String> allNames = dao.getAllNames();
		String[] str = new String[5];
		int numberOfNames = 0;
		for (@SuppressWarnings("unused") String string : allNames) {
			numberOfNames++;	
		}
		assertTrue(numberOfNames == 3);			
	}
}
