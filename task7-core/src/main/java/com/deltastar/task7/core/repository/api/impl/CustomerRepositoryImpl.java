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

import com.deltastar.task7.core.repository.domain.Customer;
import com.deltastar.task7.core.repository.api.CustomerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of {@link CustomerRepository} using JPA.
 * <p>
 * Delta Star Team
 */
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    public Customer create(final Customer customer) {
        customer.hashPassword();
        entityManager.persist(customer);
        return customer;
    }

    /**
     * {@inheritDoc}
     */
    public Customer update(Customer customer) {
        return entityManager.merge(customer);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(final Customer customer) {
        Customer u = entityManager.find(Customer.class, customer.getId());
        entityManager.remove(u);
    }

    @Override
    public List<Customer> findAllCustomer() {
        TypedQuery<Customer> query = entityManager.createNamedQuery("findAllCustomer", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findCustomerById(int customerId) {
        return entityManager.find(Customer.class, customerId);

    }

    /**
     * {@inheritDoc}
     */
    public Customer getCustomerByUserName(final String userName) {
        TypedQuery<Customer> query = entityManager.createNamedQuery("findCustomerByUserName", Customer.class);
        query.setParameter("p_userName", userName);
        List<Customer> customers = query.getResultList();
        return (customers != null && !customers.isEmpty()) ? customers.get(0) : null;
    }

    @Override
    public List<Customer> findCustomerByUserNameOrFirstNameOrLastName(String userName, String firstName, String lastName) {

        TypedQuery<Customer> query = entityManager.createNamedQuery("findCustomerByKeyWords", Customer.class);
        query.setParameter("p_userName", "%" + userName + "%");
        query.setParameter("p_firstName", "%" + firstName + "%");
        query.setParameter("p_lastName", "%" + lastName + "%");
        return query.getResultList();
    }
}
