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

import com.deltastar.task7.core.repository.domain.Customer;

import java.util.List;

/**
 * Interface for customer repository.
 * <p>
 * Delta Star Team
 */
public interface CustomerRepository {

    /**
     * Get customer by userName.
     *
     * @param userName the customer's userName
     * @return the customer with the given userName or null if no such customer
     */
    Customer getCustomerByUserName(final String userName);

    /**
     * Create a customer.
     *
     * @param customer the customer to create
     * @return the created customer
     */
    Customer create(final Customer customer);

    /**
     * Update a customer.
     *
     * @param customer the customer to update.
     * @return the updated customer
     */
    Customer update(Customer customer);

    /**
     * Remove a customer.
     *
     * @param customer the customer to remove
     */
    void remove(final Customer customer);

    /**
     * find all customer
     *
     * @return all customer
     */
    List<Customer> findAllCustomer();

    List<Customer> findCustomerByUserNameOrFirstNameOrLastName(String userName, String firstName, String lastName);

    Customer findCustomerById(int customerId);
}
