/*
 * The MIT License
 *
 *  Copyright (c) 2015, Delta Star Team
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package com.deltastar.task7.core.service;

import com.deltastar.task7.core.repository.domain.Employee;
import com.deltastar.task7.core.service.api.EmployeeService;
import com.deltastart.task7.core.constants.CCConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/application-context.xml"})
public class EmployeeServiceTest {

    //FIXME should add these two configuration into properties file.
    public static String TEST_EMAIL = "test@google.com";
    public static String TEST_PASSWORD = "123456";
    public static String TEST_USER_NAME = "TonyThompsonTest";
    @Autowired
    private EmployeeService employeeService;

    @Before
    public void setUp() throws Exception {
        log("setUp");
        Employee employee = employeeService.getEmployeeByUserName(TEST_EMAIL);
        if (employee != null) {
            employeeService.remove(employee);
        }
    }

    private void log(String log) {
        System.out.println(log);
    }

    @After
    public void tearDown() throws Exception {
        log("tearDown");

        Employee employee = employeeService.getEmployeeByUserName(TEST_EMAIL);
        if (employee != null) {
            employeeService.remove(employee);
        }
    }

    @Test
    public void testLoginEmployee() {
        testCreate();
        assertTrue(employeeService.login(TEST_EMAIL, TEST_PASSWORD));
    }

    @Test
    public void testLoginSuperAdmin() {
        assertTrue(employeeService.login(CCConstants.EMPLOYEE_SUPER_ADMIN_USER_NAME, "teamnine"));
    }


    @Test
    public void testCreate() {

        Employee employee = employeeService.getEmployeeByUserName(TEST_EMAIL);
        assertTrue(employee == null);
        employee = new Employee("TonyThompson", TEST_PASSWORD, "Tony", "Thompson");
        employeeService.create(employee);
        assertTrue(employee.getId() > 0);
        employee = employeeService.getEmployeeByUserName(TEST_EMAIL);
        assertTrue(employee != null);
        assertTrue(employeeService.login(TEST_EMAIL, TEST_PASSWORD));

    }

    @Test
    public void testGetEmployeeByEmail() {
        testCreate();
        Employee employee = employeeService.getEmployeeByUserName(TEST_EMAIL);
        assertTrue(employee != null);
    }


    @Test
    public void testRemoveEmployee() {
        Employee employee = employeeService.getEmployeeByUserName(TEST_EMAIL);
        if (employee != null) {
            employeeService.remove(employee);
            assertTrue(null == employeeService.getEmployeeByUserName(TEST_EMAIL));
        } else {
            System.out.println("the test account has already been removed.");
        }
    }

    @Test
    public void testEmployeeUpdateCustomerProfile() {

    }

}
