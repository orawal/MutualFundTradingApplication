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

import com.deltastar.task7.core.repository.api.FundPriceHistoryRepository;
import com.deltastar.task7.core.repository.api.FundPriceHistoryViewRepository;
import com.deltastar.task7.core.repository.domain.FundPriceHistoryView;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of {@link FundPriceHistoryRepository} using JPA.
 * <p>
 * Delta Star Team
 */
@Repository
public class FundPriceHistoryViewRepositoryImpl implements FundPriceHistoryViewRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FundPriceHistoryView> getFundPriceHistoryViewListById(int fundId) {

        TypedQuery<FundPriceHistoryView> query = entityManager.createNamedQuery("findFundPriceHistoryByFundId", FundPriceHistoryView.class);
        query.setParameter("p_fundId", fundId);
        return query.getResultList();

    }
}
