/*
 * The MIT License
 *
 * Copyright (c) 2015, Delta Star Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
        validationExtraBusinessRestraintsCustomer(customer.getUserName());
 * THE SOFTWARE.
 */

package com.deltastar.task7.core.service.api;

import com.deltastar.task7.core.repository.domain.*;
import com.deltastar.task7.core.service.exception.CfsException;

import java.util.List;

/**
 * Business interface for employee service.
 * <p>
 * Delta Star Team
 */
public interface EmployeeService {

    /**
     * Get employee by user name.
     *
     * @param userName the employee's user name
     * @return the employee with the given user name or null if no such employee
     */
    Employee getEmployeeByUserName(final String userName);

    /**
     * Check employee's user name and password.
     *
     * @param userName the employee's user name
     * @param password the employee's password
     * @return true if the credentials match, false else
     */
    boolean login(final String userName, final String password);

    /**
     * Create a employee.
     *
     * @param employee the employee to create
     * @return the created employee
     */
    Employee create(final Employee employee);

    /**
     * Remove a employee.
     *
     * @param employee the employee to remove
     */
    void remove(final Employee employee);


    List<Employee> getEmployeeList();

    void depositCheck(String customerId, String amount) throws CfsException;

    void depositCheck(Customer customer, String amount) throws CfsException;

    void createCustomer(Customer customer) throws CfsException;

    void updatePassword(String employeeIdAsString, String password) throws CfsException;

    void updatePassword(int employeeId, String password);

    List<Customer> getCustomerList();

    void updateCustomerPassword(String customerId, String password) throws CfsException;

    void updateCustomerProfile(String customerId, String userName, String firstName,
                               String lastName, String addressLine1, String addressLine2,
                               String city, String state, String zipcode) throws CfsException;

    Customer getCustomerById(String customerId) throws CfsException;

    List<TransitionView> getTransitionViewList();

    List<TransitionView> getTransitionViewList(String customerId) throws CfsException;

    List<Fund> getFundList();

    Fund getFundById(String fundID) throws CfsException;

    List<FundPriceHistoryView> getFundPriceHistoryViewList(String fundId) throws CfsException;

    List<List<?>> search(String keywords) throws CfsException;


    void createEmployee(String userName, String password, String firstName, String lastName) throws CfsException;

    void createFundExample(String fundName, String symbol, String comment) throws CfsException;


    void executeTransitionDay(String[] priceArray, String[] fundIdArray, String executionDay) throws CfsException;
}
