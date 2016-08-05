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

import com.deltastart.task7.core.constants.CCConstants;
import com.deltastar.task7.core.repository.domain.Position;
import com.deltastar.task7.core.repository.api.PositionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of {@link PositionRepository} using JPA.
 * <p>
 * Delta Star Team
 */
@Repository
public class PositionRepositoryImpl implements PositionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    public Position create(final Position position) {
        entityManager.persist(position);
        return position;
    }

    /**
     * {@inheritDoc}
     */
    public Position update(Position position) {
        return entityManager.merge(position);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(final Position position) {
        Position u = entityManager.find(Position.class, position.getId());
        entityManager.remove(u);
    }

    @Override
    public List<Position> getPositionListByCustomerId(int customerId) {
        TypedQuery<Position> query = entityManager.createNamedQuery("findPositionByCustomerId", Position.class);
        query.setParameter("p_customerId", customerId);

        return query.getResultList();
    }

    @Override
    public Position getPossessedPositionByCustomerIdAndFundId(int customerId, int fundId) {
        TypedQuery<Position> query = entityManager.createNamedQuery("findPositionByCustomerIdAndFundId", Position.class);
        query.setParameter("p_customerId", customerId);
        query.setParameter("p_fundId", fundId);
        query.setParameter("p_status", CCConstants.POSITION_STATUS_IN_POSSESSION);
        List<Position> tempResultList = query.getResultList();
        return tempResultList != null && tempResultList.size() > 0 ? tempResultList.get(0) : null;
    }

    /**
     * {@inheritDoc}
     */
    public Position getPositionById(final int id) {

        return entityManager.find(Position.class, id);
    }


}
