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

import com.deltastar.task7.core.repository.api.FundRepository;
import com.deltastar.task7.core.repository.domain.Fund;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of {@link FundRepository} using JPA.
 * <p>
 * Delta Star Team
 */
@Repository
public class FundRepositoryImpl implements FundRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    public Fund create(final Fund fund) {
        entityManager.persist(fund);
        return fund;
    }

    /**
     * {@inheritDoc}
     */
    public Fund update(Fund fund) {
        return entityManager.merge(fund);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(final Fund fund) {
        Fund u = entityManager.find(Fund.class, fund.getId());
        entityManager.remove(u);
    }

    @Override
    public Fund getFundByName(String fundName) {
        TypedQuery<Fund> query = entityManager.createNamedQuery("findFundByFundName", Fund.class);
        query.setParameter("p_fundName", fundName);
        List<Fund> fundList = query.getResultList();
        return (fundList != null && !fundList.isEmpty()) ? fundList.get(0) : null;
    }

    @Override
    public Fund getFundBySymbol(String symbol) {
        TypedQuery<Fund> query = entityManager.createNamedQuery("findFundBySymbol", Fund.class);
        query.setParameter("p_symbol", symbol);
        List<Fund> fundList = query.getResultList();
        return (fundList != null && !fundList.isEmpty()) ? fundList.get(0) : null;
    }

    @Override
    public List<Fund> findAllFund() {
        TypedQuery<Fund> query = entityManager.createNamedQuery("findAllFund", Fund.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    public Fund getFundById(final int id) {
        return entityManager.find(Fund.class, id);
    }

    @Override
    public List<Fund> findFundByFundNameOrSymbol( String fundName, String symbol) {
        TypedQuery<Fund> query = entityManager.createNamedQuery("findFundByFundNameOrSymbol", Fund.class);
        query.setParameter("p_symbol", "%" + symbol + "%");
        query.setParameter("p_fundName", "%" + fundName + "%");
        return query.getResultList();
    }
}
