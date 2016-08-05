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

package com.deltastar.task7.core.service.api.impl;

import com.deltastar.task7.core.repository.api.*;
import com.deltastar.task7.core.repository.domain.*;
import com.deltastar.task7.core.service.api.EmployeeService;
import com.deltastar.task7.core.service.exception.CfsException;
import com.deltastart.task7.core.constants.CCConstants;
import com.deltastart.task7.core.constants.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Implementation of the {@link EmployeeService}.
 * <p>
 * Delta Star Team
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FundRepository fundRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private TransitionRepository transitionRepository;
    @Autowired
    private TransitionViewRepository transitionViewRepository;
    @Autowired
    private FundPriceHistoryRepository fundPriceHistoryRepository;
    @Autowired
    private FundPriceHistoryViewRepository fundPriceHistoryViewRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Employee create(final Employee employee) {
        return employeeRepository.create(employee);
    }


    /**
     * {@inheritDoc}
     */
    @Transactional
    public void updatePassword(int employeeId, String password) {

        Employee employee = employeeRepository.getEmployeeById(employeeId);
        employee.setPassword(password);
        employee.hashPassword();
        employeeRepository.update(employee);

    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void updatePassword(String employeeIdAsString, String password) throws CfsException {
        int employeeId = Util.formatToInteger(employeeIdAsString);
        updatePassword(employeeId, password);

    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void remove(final Employee employee) {
        employeeRepository.remove(employee);
    }

    @Override
    public List<Employee> getEmployeeList() {
        return employeeRepository.getEmployeeList();
    }


    private void updateLastPriceForFund(Integer fundId, long price) {
        Fund fund = fundRepository.getFundById(fundId);
        fund.setLastPrice(price);
        fundRepository.update(fund);
    }

    private void doRequestCheck(Transition transition) {

        transition.setType(CCConstants.TRAN_TYPE_REQUEST_CHECK);
        transition.setStatus(CCConstants.TRAN_STATUS_DONE);
//        transition.setExecuteDate(new Timestamp(System.currentTimeMillis()));
        transitionRepository.update(transition);


        Customer customer = customerRepository.findCustomerById(transition.getCustomerId());
        customer.setCashToBeChecked(customer.getCashToBeChecked() - transition.getAmount());
        customerRepository.update(customer);
    }

    private void doBuyFund(Transition transition, long price) {
        long shares = Util.getShares(transition.getAmount(), price);
        //FIXME overflow decimal three digit.
        Position possessedPosition = positionRepository.getPossessedPositionByCustomerIdAndFundId(transition.getCustomerId(), transition.getFundId());
        if (possessedPosition == null) {
            possessedPosition = positionRepository.getPositionById(transition.getPositionId());
            possessedPosition.setStatus(CCConstants.POSITION_STATUS_IN_POSSESSION);
        } else {
            Position pendingPosition = positionRepository.getPositionById(transition.getPositionId());
            positionRepository.remove(pendingPosition);
        }

        possessedPosition.setShares(possessedPosition.getShares() + shares);
        positionRepository.update(possessedPosition);

        transition.setStatus(CCConstants.TRAN_STATUS_DONE);
//        transition.setExecuteDate(new Timestamp(System.currentTimeMillis()));
        transitionRepository.update(transition);

    }

    private void doSellFund(Transition transition, long price) {

        long cash = Util.formatCash(price, transition.getShares());


        Customer customer = customerRepository.findCustomerById(transition.getCustomerId());
        customer.setCash(customer.getCash() + cash);
        customerRepository.update(customer);


        Position pendingPosition = positionRepository.getPositionById(transition.getPositionId());
        pendingPosition.setStatus(CCConstants.POSITION_STATUS_SOLD);
        positionRepository.update(pendingPosition);

        transition.setAmount(cash);
        transition.setStatus(CCConstants.TRAN_STATUS_DONE);
//        transition.setExecuteDate(new Timestamp(System.currentTimeMillis()));
        transitionRepository.update(transition);

    }

    private void doDeposit(Transition transition) {

        Customer customer = customerRepository.findCustomerById(transition.getCustomerId());
        customer.setCash(customer.getCash() + transition.getAmount());
        customer.setCashToBeDeposited(customer.getCashToBeDeposited() - transition.getAmount());
        customerRepository.update(customer);


        transition.setStatus(CCConstants.TRAN_STATUS_DONE);
//        transition.setExecuteDate(new Timestamp(System.currentTimeMillis()));
        transitionRepository.update(transition);

    }

    @Override
    @Transactional
    public void depositCheck(String customerIdAsString, String amountAsString) throws CfsException {

        int customerId = Util.formatToInteger(customerIdAsString);
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new CfsException(CfsException.CODE_INVALID_CUSTOMER);
        }
        depositCheck(customer, amountAsString);

    }


    @Override
    @Transactional
    public void depositCheck(Customer customer, String amountAsString) throws CfsException {
        long amount = Util.formatToLong(amountAsString);

        if (!Util.isValidTransactionAmount(amount)) {
            throw new CfsException(CfsException.CODE_MAX_DEPOSITION);
        }
        Transition transition = new Transition();
        transition.setAmount(amount);
        transition.setCustomerId(customer.getId());
        transition.setType(CCConstants.TRAN_TYPE_DEPOSIT_CHECK);
        transition.setStatus(CCConstants.TRAN_STATUS_PENDING);
        transitionRepository.create(transition);

        customer.setCashToBeDeposited(customer.getCashToBeDeposited() + amount);
        customerRepository.update(customer);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public Employee getEmployeeByUserName(final String userName) {
        return employeeRepository.getEmployeeByUserName(userName);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public boolean login(final String email, final String password) {

        Employee employee = employeeRepository.getEmployeeByUserName(email);
        if (employee == null) {
            return false;
        } else if (CCConstants.EMPLOYEE_TYPE_SUPER_ADMIN == employee.getType()) {
            return password.equals(employee.getPassword());
        } else {
            return employee.checkPassword(password);
        }

    }

    @Override
    public void createCustomer(Customer customer) throws CfsException {
        validationParameterCustomer(customer);
        validationExtraBusinessRestraintsCustomer(customer.getUserName());
        Customer customerCopy = new Customer();
        customerCopy.setUserName(customer.getUserName().trim());
        customerCopy.setPassword(customer.getPassword());
        customerCopy.setFirstName(customer.getFirstName());
        customerCopy.setLastName(customer.getLastName());
        customerCopy.setAddressLine1(customer.getAddressLine1());
        customerCopy.setAddressLine2(customer.getAddressLine2());
        customerCopy.setCity(customer.getCity());
        customerCopy.setState(customer.getState());
        customerCopy.setZipcode(customer.getZipcode());
        customerRepository.create(customerCopy);

    }

    @Override
    public void createEmployee(String userName, String password, String firstName, String lastName) throws CfsException {
        validationParameterEmployee(userName, password, firstName, lastName);
        validationExtraBusinessRestraintsEmployee(userName);
        Employee employee = new Employee(userName.trim(), password, firstName.trim(), lastName.trim());
        employeeRepository.create(employee);

    }

    @Override
    public List<Customer> getCustomerList() {
        return customerRepository.findAllCustomer();
    }

    @Override
    public void updateCustomerPassword(String customerIdAsString, String password) throws CfsException {
        int customerId = Util.formatToInteger(customerIdAsString);
        Customer customer = customerRepository.findCustomerById(customerId);
        validationParameterEmployeeUpdateCustomerPassword(password);
        customer.setPassword(password);
        customer.hashPassword();
        customerRepository.update(customer);
    }

    @Override
    public void updateCustomerProfile(String customerIdAsString, String userName, String firstName, String lastName, String addressLine1, String addressLine2, String city, String state, String zipcode) throws CfsException {

        int customerId = Util.formatToInteger(customerIdAsString);
        Customer customer = customerRepository.findCustomerById(customerId);
        validationParameterCustomer(customer);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddressLine1(addressLine1);
        customer.setAddressLine2(addressLine2);
        customer.setCity(city);
        customer.setState(state);
        customer.setZipcode(zipcode);
        customerRepository.update(customer);
    }

    @Override
    public Customer getCustomerById(String customerIdAsString) throws CfsException {

        int customerId = Util.formatToInteger(customerIdAsString);

        Customer result = customerRepository.findCustomerById(customerId);

        if (result == null) {
            throw new CfsException(CfsException.CODE_INVALID_CUSTOMER_ID);
        }

        return result;

    }

    @Override
    public List<TransitionView> getTransitionViewList() {
        return transitionViewRepository.getTransitionList();
    }

    @Override
    public List<TransitionView> getTransitionViewList(String customerIdAsString) throws CfsException {
        int customerId = Util.formatToInteger(customerIdAsString);
        return transitionViewRepository.getTransitionListByCustomerId(customerId);
    }

    @Override
    public List<Fund> getFundList() {
        return fundRepository.findAllFund();
    }

    public Fund getFundById(String fundIdAsString) throws CfsException {

        int fundId = Util.formatToInteger(fundIdAsString);
        Fund result = fundRepository.getFundById(fundId);

        if (result == null) {
            throw new CfsException(CfsException.CODE_INVALID_FUND_ID);
        }

        return result;
    }

    @Override
    public List<FundPriceHistoryView> getFundPriceHistoryViewList(String fundIdAsString) throws CfsException {
        int fundId = Util.formatToInteger(fundIdAsString);
        return fundPriceHistoryViewRepository.getFundPriceHistoryViewListById(fundId);
    }

    @Override
    public List<List<?>> search(String keywords) throws CfsException {

        if (keywords == null || keywords.replaceAll(" ", "").equals("")) {
            throw new CfsException(CfsException.CODE_INVALID_KEYWORDS);
        }
        List<List<?>> result = new ArrayList<>();
        List<Customer> customerList = customerRepository.findCustomerByUserNameOrFirstNameOrLastName(keywords, keywords, keywords);
        List<Fund> fundList = fundRepository.findFundByFundNameOrSymbol(keywords, keywords);

        result.add(customerList);
        result.add(fundList);

        return result;
    }

    @Transactional
    @Override
    public void executeTransitionDay(String[] priceArray, String[] fundIdArray, String executionDay) throws CfsException {


        for (int i = 0; i < priceArray.length; i++) {

            if (!Util.isEmpty(priceArray[i])) {

                long price = Util.formatToLong(priceArray[i]);

                if (!Util.isValidTransactionAmount(price)) {
                    throw new CfsException(CfsException.CODE_MAX_DEPOSITION);
                }

                int fundId = Util.formatToInteger(fundIdArray[i]);
                Timestamp timestamp = Util.formatTimeStamp(executionDay);

                Fund fund = fundRepository.getFundById(fundId);
                if (fund.getLastTransitionDay() != null && !fund.getLastTransitionDay().before(timestamp)) {
                    throw new CfsException(CfsException.CODE_INVALID_EXECUTION_DATE,
                            MessageFormat.format(ResourceBundle.getBundle("cfs").getString("invalid.execution.date"),
                                    Util.formatTime(fund.getLastTransitionDay())));
                }

                fund.setLastTransitionDay(timestamp);
                fundRepository.update(fund);

                FundPriceHistory fundPriceHistory = new FundPriceHistory();
                fundPriceHistory.setFundId(fundId);
                fundPriceHistory.setPrice(price);
                fundPriceHistory.setPriceDate(timestamp);
                fundPriceHistoryRepository.create(fundPriceHistory);
                updateLastPriceForFund(fundId, price);


                List<Transition> pendingTransitionList = transitionRepository.getPendingTransitionList();

                for (Transition transition : pendingTransitionList) {
                    transition.setExecuteDate(timestamp);
                    switch (transition.getType()) {
                        case CCConstants.TRAN_TYPE_DEPOSIT_CHECK:
                            doDeposit(transition);
                            break;
                        case CCConstants.TRAN_TYPE_SELL_FUND:
                            if (fundId == transition.getFundId()) {
                                doSellFund(transition, price);
                            }
                            break;
                        case CCConstants.TRAN_TYPE_BUY_FUND:
                            if (fundId == transition.getFundId()) {
                                doBuyFund(transition, price);
                            }
                            break;
                        case CCConstants.TRAN_TYPE_REQUEST_CHECK:
                            doRequestCheck(transition);
                            break;
                        default:
                            break;
                    }

                }


            } else {
                System.out.println("fundList :" + fundIdArray[i]);
            }
        }


    }

    @Override
    public void createFundExample(String fundName, String symbol, String comment) throws CfsException {
        validationParameter(fundName, symbol, comment);
        validateExtraBusinessRestraint(fundName, symbol);

        Fund fund = new Fund(fundName.trim(), symbol.trim(), comment.trim());
        try {
            fundRepository.create(fund);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CfsException(e.getMessage());
        }

    }


    /**
     * Check whether there is any duplicated fund name or symbol or not. If there is, throw exceptioin.
     * This extra validation is done based on our own logic. For example, if you are withdrawing some money,
     * you should check the balance. If you are creating customer, you should check the existence of user name.
     * In any case, anything that may produce an error should be pre-checked in this step.
     *
     * @throws CfsException
     */
    private void validateExtraBusinessRestraint(String fundName, String symbol) throws CfsException {
        if (fundRepository.getFundByName(fundName) != null) {
            throw new CfsException(CfsException.CODE_EXISTED_FUND_NAME);

        }

        if (fundRepository.getFundBySymbol(symbol) != null) {
            throw new CfsException(CfsException.CODE_EXISTED_SYMBOL);

        }

    }

    /**
     * check the fund name and symbol is null or not first. If it is null, throw exception.
     * Every method in service should check the validation of the parameter as user's input is evil.
     *
     * @throws CfsException
     */
    private void validationParameter(String fundName, String symbol, String comment) throws CfsException {
        if (fundName == null || fundName.replaceAll(" ", "").equals("")) {
            throw new CfsException(CfsException.CODE_INVALID_FUND_NAME);
        }

        if (symbol == null || symbol.replaceAll(" ", "").equals("")) {
            throw new CfsException(CfsException.CODE_INVALID_SYMBOL);
        }
        Util.validScript(fundName);
        Util.validScript(symbol);
        Util.validScript(comment);
    }

    private void validationParameterEmployee(String userName, String password, String firstName, String lastName) throws CfsException {
        if (userName == null || userName.replaceAll("  ", " ").trim().length() == 0) {
            throw new CfsException(CfsException.CODE_INVALID_EMPLOYEE_USERNAME);
        }
        if (password == null || password.length() < 6) {
            throw new CfsException(CfsException.CODE_INVALID_EMPLOYEE_PASSWORD);
        }
        if (firstName == null || firstName.replaceAll("  ", " ").trim().length() == 0) {
            throw new CfsException(CfsException.CODE_INVALID_EMPLOYEE_FIRST_NAME);
        }
        if (lastName == null || lastName.replaceAll("  ", " ").trim().length() == 0) {
            throw new CfsException(CfsException.CODE_INVALID_EMPLOYEE_LAST_NAME);
        }
        Util.validScript(userName);
        Util.validScript(password);
        Util.validScript(firstName);
        Util.validScript(lastName);

    }

    private void validationParameterEmployeeUpdateCustomerPassword(String newPassword) throws CfsException {
        if (newPassword == null || newPassword.length() < 6) {
            throw new CfsException(CfsException.CODE_INVALID_EMPLOYEE_UPDATE_CUSTOMER_PASSWORD);
        }
    }


    private void validationParameterCustomer(Customer customer) throws CfsException {
        if (customer.getUserName() == null || customer.getUserName().length() == 0) {
            throw new CfsException(CfsException.CODE_INVALID_CUSTOMER_USERNAME);
        }

        if (customer.getPassword() == null || customer.getPassword().length() < 6) {
            throw new CfsException(CfsException.CODE_INVALID_CUSTOMER_PASSWORD);
        }

        if (customer.getFirstName() == null || customer.getFirstName().length() == 0) {
            throw new CfsException(CfsException.CODE_INVALID_CUSTOMER_FIRST_NAME);
        }
        if (customer.getLastName() == null || customer.getLastName().length() == 0) {
            throw new CfsException(CfsException.CODE_INVALID_CUSTOMER_LAST_NAME);
        }
        if (customer.getAddressLine1() == null || customer.getAddressLine1().length() == 0) {
            throw new CfsException(CfsException.CODE_INVALID_CUSTOMER_ADDRESS_LINE1);
        }
        if (customer.getCity() == null || customer.getCity().length() == 0) {
            throw new CfsException(CfsException.CODE_INVALID_CUSTOMER_CITY);
        }
        if (customer.getState() == null || customer.getState().length() == 0) {
            throw new CfsException(CfsException.CODE_INVALID_CUSTOMER_STATE);
        }
        if (customer.getZipcode() == null || customer.getZipcode().length() == 0) {
            throw new CfsException(CfsException.CODE_INVALID_CUSTOMER_ZIPCODE);
        }
        Util.validScript(customer.getUserName());
        Util.validScript(customer.getPassword());
        Util.validScript(customer.getFirstName());
        Util.validScript(customer.getLastName());
        Util.validScript(customer.getAddressLine1());
        Util.validScript(customer.getAddressLine2());
        Util.validScript(customer.getCity());
        Util.validScript(customer.getState());
        Util.validScript(customer.getZipcode());
    }

    private void validationExtraBusinessRestraintsCustomer(String userName) throws CfsException {
        if (customerRepository.getCustomerByUserName(userName) != null) {
            throw new CfsException(CfsException.CODE_INVALID_CUSTOMER);
        }
    }

    private void validationExtraBusinessRestraintsEmployee(String userName) throws CfsException {
        if (employeeRepository.getEmployeeByUserName(userName) != null) {
            throw new CfsException(CfsException.CODE_INVALID_EMPLOYEE);
        }
    }


}
