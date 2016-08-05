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

import com.deltastar.task7.core.repository.domain.Fund;

import java.util.List;

/**
 * Business interface for fund service.
 * <p>
 * Delta Star Team
 */
public interface FundService {


    Fund getFundById(final int id);

    /**
     * Create a fund.
     *
     * @param fund the fund to create
     * @return the created fund
     */
    Fund create(final Fund fund);

    /**
     * Update a fund.
     *
     * @param fund the fund to update.
     * @return the updated fund
     */
    Fund update(Fund fund);

    /**
     * Remove a fund.
     *
     * @param fund the fund to remove
     */
    void remove(final Fund fund);

    List<Fund> getFundList();
}
