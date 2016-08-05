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
 * THE SOFTWARE.
 */

package com.deltastar.task7.core.service.api;

import com.deltastar.task7.core.repository.domain.*;
import com.deltastar.task7.core.service.exception.CfsException;

import java.util.List;

/**
 * Business interface for customer service.
 * <p>
 * Delta Star Team
 */
public interface CustomerService {

    /**
     * Get customer by user name.
     *
     * @param userName the customer's user name
     * @return the customer with the given user name or null if no such customer
     */
    Customer getCustomerByUserName(final String userName);

    /**
     * Check customer's user name and password.
     *
     * @param userName the customer's user name
     * @param password the customer's password
     * @return true if the credentials match, false else
     */
    boolean login(final String userName, final String password);

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

    List<Customer> getCustomerList();

    Customer getCustomerById(String customerIdAsString) throws CfsException;

    Customer getCustomerById(int customerId) throws CfsException;

    void buyFund(Customer customer, String fundId, String amount) throws CfsException;

    void sellFund(Customer customer, String fundId, String shares) throws CfsException;

    void requestCheck(Customer customer, String amount) throws CfsException;


    List<PositionView> getPositionViewListByCustomerIdAndStatus(String customerId, String positionStatus) throws CfsException;

    List<PositionView> getPositionViewListByCustomerIdAndStatus(int customerId, byte positionStatus) throws CfsException;


    List<Fund> getFundList();

    Fund getFundById(String fundId) throws CfsException;

    List<TransitionView> getTransitionViewListByCustomerId(String customerIdAsString) throws CfsException;

    List<TransitionView> getTransitionViewListByCustomerId(int customerId) throws CfsException;

    List<Fund> search(String keywords) throws CfsException;

    void updatePassword(int customerId, String newPassword);

    void updatePassword(String customerIdAsPassword, String newPassword) throws CfsException;

    String generateBarChartData();

    String generateBarChartData(String fundId) throws CfsException;
}
