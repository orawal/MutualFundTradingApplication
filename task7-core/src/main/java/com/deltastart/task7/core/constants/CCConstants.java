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

package com.deltastart.task7.core.constants;

/**
 * User entity.
 * <p>
 * Delta Star Team
 */
public class CCConstants {

    public static final byte TRAN_TYPE_DEPOSIT_CHECK = 1;
    public static final byte TRAN_TYPE_SELL_FUND = 2;
    public static final byte TRAN_TYPE_BUY_FUND = 3;
    public static final byte TRAN_TYPE_REQUEST_CHECK = 4;


    public static final byte TRAN_STATUS_DONE = 0;
    public static final byte TRAN_STATUS_PENDING = 1;
    public static final byte TRAN_STATUS_DONE_WITH_ERROR = 2;


    public static final byte POSITION_STATUS_IN_POSSESSION = 0;
    public static final byte POSITION_STATUS_TO_BE_BOUGHT = 1;
    public static final byte POSITION_STATUS_TO_BE_SOLD = 2;
    public static final byte POSITION_STATUS_SOLD = 3;

    public static final String EMPLOYEE_SUPER_ADMIN_USER_NAME = "jeff";
    public static final String HINT_SUCCESS = "success";
    public static final int EMPLOYEE_TYPE_SUPER_ADMIN = 1;
    public static final int EMPLOYEE_TYPE_EMPLOYEE = 0;
    public static final String INVALID_NUMBER = "Invalid number";
    public static final long MAX_DEPOSITION_AMOUNT = 1000000000 * 1000L;
    public static final long MIN_DEPOSITION_AMOUNT = 1;
}
