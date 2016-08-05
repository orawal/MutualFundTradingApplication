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

import com.deltastar.task7.core.repository.domain.Position;

import java.util.List;

/**
 * Business interface for position service.
 * <p>
 * Delta Star Team
 */
public interface PositionService {


    Position getPositionById(final int id);

    /**
     * Create a position.
     *
     * @param position the position to create
     * @return the created position
     */
    Position create(final Position position);

    /**
     * Update a position.
     *
     * @param position the position to update.
     * @return the updated position
     */
    Position update(Position position);

    /**
     * Remove a position.
     *
     * @param position the position to remove
     */
    void remove(final Position position);

    List<Position> getPositionListByCustomerId(final int customerId);
}
