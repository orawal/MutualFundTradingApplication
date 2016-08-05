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

import com.deltastar.task7.core.repository.domain.Fund;
import com.deltastar.task7.core.repository.api.FundRepository;
import com.deltastar.task7.core.service.api.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the {@link FundService}.
 * <p>
 * Delta Star Team
 */
@Service
@Transactional
public class FundServiceImpl implements FundService {

    @Autowired
    private FundRepository fundRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Fund create(final Fund fund) {
        return fundRepository.create(fund);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Fund update(Fund fund) {
        return fundRepository.update(fund);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void remove(final Fund fund) {
        fundRepository.remove(fund);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public Fund getFundById(final int id) {
        return fundRepository.getFundById(id);
    }

    public List<Fund> getFundList() {
        return fundRepository.findAllFund();
    }

}
