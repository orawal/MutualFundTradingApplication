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

package com.deltastar.task7.core.repository.api.impl;

import com.deltastar.task7.core.repository.api.EmployeeRepository;
import com.deltastar.task7.core.repository.domain.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of {@link EmployeeRepository} using JPA.
 * <p>
 * Delta Star Team
 */
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    public Employee create(final Employee employee) {
        employee.hashPassword();
        entityManager.persist(employee);
        return employee;
    }

    /**
     * {@inheritDoc}
     */
    public Employee update(Employee employee) {
        return entityManager.merge(employee);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(final Employee employee) {
        Employee u = entityManager.find(Employee.class, employee.getId());
        entityManager.remove(u);
    }

    @Override
    public List<Employee> getEmployeeList() {
        TypedQuery<Employee> query = entityManager.createNamedQuery("findAllEmployee", Employee.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    public Employee getEmployeeByUserName(final String userName) {
        TypedQuery<Employee> query = entityManager.createNamedQuery("findEmployeeByUserName", Employee.class);
        query.setParameter("p_userName", userName);
        List<Employee> employees = query.getResultList();
        return (employees != null && !employees.isEmpty()) ? employees.get(0) : null;
    }


    @Override
    public Employee getEmployeeById(int employeeId) {
        return entityManager.find(Employee.class, employeeId);
    }
}
