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
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.deltastar.task7.core.service.api.impl;

import com.deltastar.task7.core.repository.api.*;
import com.deltastar.task7.core.repository.domain.*;
import com.deltastar.task7.core.service.api.CustomerService;
import com.deltastar.task7.core.service.exception.CfsException;
import com.deltastart.task7.core.constants.CCConstants;
import com.deltastart.task7.core.constants.Util;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the {@link CustomerService}.
 * <p>
 * Delta Star Team
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FundRepository fundRepository;
    @Autowired
    private FundPriceHistoryViewRepository fundPriceHistoryViewRepository;
    @Autowired
    private PositionViewRepository positionViewRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private TransitionRepository transitionRepository;
    @Autowired
    private TransitionViewRepository transitionViewRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Customer create(final Customer customer) {
        return customerRepository.create(customer);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Customer update(Customer customer) {
        return customerRepository.update(customer);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void remove(final Customer customer) {
        customerRepository.remove(customer);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public Customer getCustomerByUserName(final String userName) {
        return customerRepository.getCustomerByUserName(userName);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public boolean login(final String userName, final String password) {
        Customer customer = customerRepository.getCustomerByUserName(userName);
        return customer != null && customer.checkPassword(password);

    }

    public List<Customer> getCustomerList() {
        return customerRepository.findAllCustomer();
    }

    @Override
    public Customer getCustomerById(String customerId) throws CfsException {

        Integer id = Integer.valueOf(customerId);
        return getCustomerById(id);

    }//why there is String customer Id

    @Override
    public Customer getCustomerById(int customerId) throws CfsException {
        return customerRepository.findCustomerById(customerId);
    }

    @Override
    @Transactional
    public void buyFund(Customer customer, String fundIdAsString, String amountAsString) throws CfsException {

        customer = customerRepository.getCustomerByUserName(customer.getUserName());

        int fundId;
        long amount;
        try {
            fundId = Integer.valueOf(fundIdAsString);
            amount = Util.formatToLong(amountAsString);
        } catch (NumberFormatException e) {
            throw new CfsException(CfsException.CODE_INVALID_INPUT_DATA);
        }

        if (!Util.isValidTransactionAmount(amount)) {
            throw new CfsException(CfsException.CODE_MAX_DEPOSITION);
        }

        if (customer.getCash() < amount) {
            throw new CfsException(CfsException.CODE_INSUFFICIENT_BALANCE);

        }

        customer.setCash(customer.getCash() - amount);
        customerRepository.update(customer);

        Fund fund = fundRepository.getFundById(fundId);
        if (fund == null) {
            throw new CfsException(CfsException.CODE_INVALID_FUND_NAME);
        }


        Position position = new Position(fund.getId(), customer.getId(), CCConstants.POSITION_STATUS_TO_BE_BOUGHT);
        positionRepository.create(position);

        Transition transition = new Transition();
        transition.setPositionId(position.getId());
        transition.setAmount(amount);
        transition.setCustomerId(customer.getId());
        transition.setFundId(fund.getId());
        transition.setType(CCConstants.TRAN_TYPE_BUY_FUND);
        transition.setStatus(CCConstants.TRAN_STATUS_PENDING);
        transitionRepository.create(transition);


    }

    @Override
    @Transactional
    public void sellFund(Customer customer, String fundIdAsString, String sharesAsString) throws CfsException {
        customer = customerRepository.getCustomerByUserName(customer.getUserName());

        int fundId;
        long shares;
        try {
            fundId = Integer.valueOf(fundIdAsString);
            shares = Util.formatToLong(sharesAsString);
        } catch (NumberFormatException e) {
            throw new CfsException(CfsException.CODE_INVALID_INPUT_DATA);
        }

        if (!Util.isValidTransactionAmount(shares)) {
            throw new CfsException(CfsException.CODE_MAX_DEPOSITION);
        }

        Fund fund = fundRepository.getFundById(fundId);
        if (fund == null) {
            throw new CfsException(CfsException.CODE_INVALID_FUND_ID);
        }


        Position position = positionRepository.getPossessedPositionByCustomerIdAndFundId(customer.getId(), fund.getId());
        if (position == null || position.getShares() < shares) {
            throw new CfsException(CfsException.CODE_INSUFFICIENT_SHARES);
        }

        //update the current position.
        position.setShares(position.getShares() - shares);
        positionRepository.update(position);


        //create the pending position.
        Position pendingPosition = new Position(fund.getId(), customer.getId(), CCConstants.POSITION_STATUS_TO_BE_SOLD);
        pendingPosition.setShares(shares);
        positionRepository.create(pendingPosition);


        //create the pending transition.
        Transition transition = new Transition();
        transition.setShares(shares);
        transition.setCustomerId(customer.getId());
        transition.setFundId(fund.getId());
        transition.setPositionId(pendingPosition.getId());
        transition.setType(CCConstants.TRAN_TYPE_SELL_FUND);
        transition.setStatus(CCConstants.TRAN_STATUS_PENDING);
        transitionRepository.create(transition);
    }

    @Override
    @Transactional
    public void requestCheck(Customer customer, String amountAsString) throws CfsException {

        long amount;
        try {
            amount = Util.formatToLong(amountAsString);
        } catch (NumberFormatException e) {
            throw new CfsException(CfsException.CODE_INVALID_INPUT_DATA);
        }

        if (!Util.isValidTransactionAmount(amount)) {
            throw new CfsException(CfsException.CODE_MAX_DEPOSITION);
        }
        if (customer.getCash() < amount) {
            throw new CfsException(CfsException.CODE_INSUFFICIENT_BALANCE);

        }

        Transition transition = new Transition();
        transition.setAmount(amount);
        transition.setCustomerId(customer.getId());
        transition.setType(CCConstants.TRAN_TYPE_REQUEST_CHECK);
        transition.setStatus(CCConstants.TRAN_STATUS_PENDING);
        transitionRepository.create(transition);

        customer.setCashToBeChecked(customer.getCashToBeChecked() + amount);
        customer.setCash(customer.getCash() - amount);
        customerRepository.update(customer);

    }

    @Override
    public List<TransitionView> getTransitionViewListByCustomerId(String customerIdAsString) throws CfsException {
        int customerId = Util.formatToInteger(customerIdAsString);
        return getTransitionViewListByCustomerId(customerId);
    }

    @Override
    public List<TransitionView> getTransitionViewListByCustomerId(int customerId) throws CfsException {
        return transitionViewRepository.getTransitionListByCustomerId(customerId);
    }

    @Override
    public List<PositionView> getPositionViewListByCustomerIdAndStatus(String customerIdAsString, String positionStatusAsString) throws CfsException {
        int customerId = Util.formatToInteger(customerIdAsString);
        byte positionStatus = Util.formatToByte(positionStatusAsString);
        return getPositionViewListByCustomerIdAndStatus(customerId, positionStatus);
    }

    @Override
    public List<PositionView> getPositionViewListByCustomerIdAndStatus(int customerId, byte positionStatus) throws CfsException {
        return positionViewRepository.getPositionViewListByCustomerId(customerId, positionStatus);
    }

    @Override
    public List<Fund> getFundList() {
        return fundRepository.findAllFund();
    }

    @Override
    public Fund getFundById(String fundIdAsString) throws CfsException {
        int fundId = Util.formatToInteger(fundIdAsString);
        Fund fund = fundRepository.getFundById(fundId);
        if (fund == null) {
            throw new CfsException(CfsException.CODE_INVALID_FUND_ID);
        }
        return fund;
    }

    @Override
    public List<Fund> search(String keywords) throws CfsException {
        if (keywords == null || keywords.replaceAll(" ", "").equals("")) {
            throw new CfsException(CfsException.CODE_INVALID_KEYWORDS);
        }
        return fundRepository.findFundByFundNameOrSymbol(keywords, keywords);
    }

    @Override
    public void updatePassword(int customerId, String newPassword) {

        Customer customer = customerRepository.findCustomerById(customerId);
        customer.setPassword(newPassword);
        customer.hashPassword();
        customerRepository.update(customer);
    }

    @Override
    public void updatePassword(String customerIdAsPassword, String newPassword) throws CfsException {
        int employeeId = Util.formatToInteger(customerIdAsPassword);
        updatePassword(employeeId, newPassword);
    }


    @Override
    public String generateBarChartData(String fundIdAsString) throws CfsException {
        int fundId = Util.formatToInteger(fundIdAsString);
        List<FundPriceHistoryView> fundPriceHistoryViewList = fundPriceHistoryViewRepository.getFundPriceHistoryViewListById(fundId);

        if (Util.isEmptyList(fundPriceHistoryViewList)) {
            return null;
        }
        String[] labelArray = new String[fundPriceHistoryViewList.size()];
        for (int i = 0; i < fundPriceHistoryViewList.size(); i++) {
            labelArray[i] = Util.formatTime(fundPriceHistoryViewList.get(i).getPriceDate());
        }


        DataSet[] dataSetArray = new DataSet[1];
        dataSetArray[0] = constructDataSetForFundPrice(fundPriceHistoryViewList);


        BarChartData barChartData = new BarChartData();
        barChartData.setDatasets(dataSetArray);
        barChartData.setLabels(labelArray);

        String result = new Gson().toJson(barChartData);
        System.out.println("generateBarChartData:" + result);
        return result;
    }

    @Override
    public String generateBarChartData() {

        List<Fund> fundList = fundRepository.findAllFund();

        if (Util.isEmptyList(fundList)) {
            return null;
        }
        String[] labelArray = new String[fundList.size()];
        for (int i = 0; i < fundList.size(); i++) {
            labelArray[i] = fundList.get(i).getFundName();
        }


        DataSet[] dataSetArray = new DataSet[1];
        dataSetArray[0] = constructDataSet(fundList);


        BarChartData barChartData = new BarChartData();
        barChartData.setDatasets(dataSetArray);
        barChartData.setLabels(labelArray);

        String result = new Gson().toJson(barChartData);
        System.out.println("generateBarChartData:" + result);
        return result;
    }

    private DataSet constructDataSet(List<Fund> fundList) {
        DataSet dataSet = new DataSet();
        double[] data = new double[fundList.size()];
        for (int i = 0; i < fundList.size(); i++) {
            data[i] = fundList.get(i).getLastPrice() / 1000.0;
        }
        dataSet.setData(data);
        return dataSet;
    }

    private DataSet constructDataSetForFundPrice(List<FundPriceHistoryView>  fundList) {
        DataSet dataSet = new DataSet();
        double[] data = new double[fundList.size()];
        for (int i = 0; i < fundList.size(); i++) {
            data[i] = fundList.get(i).getPrice() / 1000.0;
        }
        dataSet.setData(data);
        return dataSet;
    }
}
