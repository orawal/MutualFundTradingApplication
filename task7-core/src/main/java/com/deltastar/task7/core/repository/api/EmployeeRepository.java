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

package com.deltastar.task7.core.repository.api;

import com.deltastar.task7.core.repository.domain.Employee;

import java.util.List;

/**
 * Interface for employee repository.
 *
 * Delta Star Team
 */
public interface EmployeeRepository {

    /**
     * Get employee by user name.
     *
     * @param userName the employee's user name
     * @return the employee with the given user name or null if no such employee
     */
    Employee getEmployeeByUserName(final String userName);


    Employee getEmployeeById(final int id);


    /**
     * Create a employee.
     *
     * @param employee the employee to create
     * @return the created employee
     */
    Employee create(final Employee employee);

    /**
     * Update a employee.
     *
     * @param employee the employee to update.
     * @return the updated employee
     */
    Employee update(Employee employee);

    /**
     * Remove a employee.
     *
     * @param employee the employee to remove
     */
    void remove(final Employee employee);

    List<Employee> getEmployeeList();
}
