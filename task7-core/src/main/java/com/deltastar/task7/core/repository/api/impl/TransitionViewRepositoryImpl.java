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

import com.deltastar.task7.core.repository.api.TransitionViewRepository;
import com.deltastar.task7.core.repository.domain.TransitionView;
import com.deltastart.task7.core.constants.CCConstants;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of {@link TransitionViewRepository} using JPA.
 * <p>
 * Delta Star Team
 */
@Repository
public class TransitionViewRepositoryImpl implements TransitionViewRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<TransitionView> getPendingTransitionList() {
        TypedQuery<TransitionView> query = entityManager.createNamedQuery("findTransitionViewByStatus", TransitionView.class);
        query.setParameter("p_status", CCConstants.TRAN_STATUS_PENDING);
        return query.getResultList();
    }

    @Override
    public List<TransitionView> getTransitionListByCustomerId(int customerId) {
        TypedQuery<TransitionView> query = entityManager.createNamedQuery("findTransitionViewByCustomerId", TransitionView.class);
        query.setParameter("p_customerId", customerId);
        return query.getResultList();
    }

    @Override
    public TransitionView getTransitionById(final int id) {

        return entityManager.find(TransitionView.class, id);
    }

    @Override
    public List<TransitionView> getTransitionList() {
        TypedQuery<TransitionView> query = entityManager.createNamedQuery("findAllTransitionView", TransitionView.class);
        return query.getResultList();

    }
}
