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

import com.deltastar.task7.core.repository.domain.Transition;
import com.deltastart.task7.core.constants.CCConstants;
import com.deltastar.task7.core.repository.api.TransitionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of {@link TransitionRepository} using JPA.
 * <p>
 * Delta Star Team
 */
@Repository
public class TransitionRepositoryImpl implements TransitionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    public Transition create(final Transition transition) {
        entityManager.persist(transition);
        return transition;
    }

    /**
     * {@inheritDoc}
     */
    public Transition update(Transition position) {
        return entityManager.merge(position);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(final Transition position) {
        Transition u = entityManager.find(Transition.class, position.getId());
        entityManager.remove(u);
    }

    @Override
    public List<Transition> getPendingTransitionList() {
        TypedQuery<Transition> query = entityManager.createNamedQuery("findTransitionByStatus", Transition.class);
        query.setParameter("p_status", CCConstants.TRAN_STATUS_PENDING);
        return query.getResultList();
    }

    @Override
    public List<Transition> getTransitionListByCustomerId(int customerId) {
        TypedQuery<Transition> query = entityManager.createNamedQuery("findTransitionByCustomerId", Transition.class);
        query.setParameter("p_customerId", customerId);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    public Transition getTransitionById(final int id) {

        return entityManager.find(Transition.class, id);
    }

    @Override
    public List<Transition> getTransitionList() {
        TypedQuery<Transition> query = entityManager.createNamedQuery("findAllTransition", Transition.class);
        return query.getResultList();

    }
}
