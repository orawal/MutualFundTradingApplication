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

import com.deltastar.task7.core.repository.domain.Position;
import com.deltastar.task7.core.repository.api.PositionRepository;
import com.deltastar.task7.core.service.api.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the {@link PositionService}.
 * <p>
 * Delta Star Team
 */
@Service
@Transactional
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;


    /**
     * {@inheritDoc}
     */
    @Transactional
    public Position create(final Position position) {
        return positionRepository.create(position);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Position update(Position position) {
        return positionRepository.update(position);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void remove(final Position position) {
        positionRepository.remove(position);
    }


    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public Position getPositionById(final int id) {
        return positionRepository.getPositionById(id);
    }


    public List<Position> getPositionListByCustomerId(final int customerId) {
        return positionRepository.getPositionListByCustomerId(customerId);
    }


}
