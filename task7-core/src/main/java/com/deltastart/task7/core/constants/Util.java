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

import com.deltastar.task7.core.repository.domain.Customer;
import com.deltastar.task7.core.service.exception.CfsException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * User entity.
 * <p>
 * Delta Star Team
 */
public class Util {


    private static final SimpleDateFormat monthDayYearformatter = new SimpleDateFormat(
            "MMMMM dd, yyyy");

    public static String getDisplayName(Customer customer) {
        if (customer == null) {
            return "";
        }
        return customer.getFirstName() + " " + customer.getLastName();
    }

    public static boolean isEmptyList(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static boolean isEmpty(String src) {
        return src == null || src.trim().equals("");
    }

    public static String formatTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            return monthDayYearformatter.format(timestamp);
        }
    }

    public static String formatTime(Date date) {
        if (date == null) {
            return null;
        } else {
            return monthDayYearformatter.format(date);
        }
    }

    public static String getDisplayStatusForPosition(byte status) {
        if (status == CCConstants.POSITION_STATUS_IN_POSSESSION) {
            return "In possession";
        } else if (status == CCConstants.POSITION_STATUS_SOLD) {
            return "Sold";
        } else if (status == CCConstants.POSITION_STATUS_TO_BE_BOUGHT) {
            return "To be bought ";
        } else {
            return "To be sold";
        }
    }

    public static String formatCash(long cash) {
        return formatCash(cash, false);

    }

    public static String formatCash(long cash, boolean multiplied) {
        return String.format("%1$,.3f", cash / (1000.0 * (multiplied ? 1000 : 1)));

    }

    public static String cashFormatForDisplay(long cash) {
        return String.format("%1$,.2f", cash / (1000.0));

    }

    public static long formatToLong(String amountAsString) throws CfsException {
        try {
            return Math.round(Double.valueOf(amountAsString) * 1000L);
        } catch (NumberFormatException e) {
            throw new CfsException(CfsException.CODE_INVALID_INPUT_DATA);
        }
    }

    public static long formatCash(long shares, long price) {
        return Math.round(shares * price / 1000.0);
    }


    public static int formatToInteger(String idAsString) throws CfsException {
        try {
            return Integer.valueOf(idAsString);
        } catch (NumberFormatException e) {
            throw new CfsException(CfsException.CODE_INVALID_INPUT_DATA);
        }
    }

    public static byte formatToByte(String statusAsString) throws CfsException {
        try {
            return Byte.valueOf(statusAsString);
        } catch (NumberFormatException e) {
            throw new CfsException(CfsException.CODE_INVALID_INPUT_DATA);
        }
    }

    public static long getShares(long amount, long price) {
        return Math.round(amount * 1000.0 / price);
    }

    public static Timestamp formatTimeStamp(String executionDay) throws CfsException {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date parsedDate = dateFormat.parse(executionDay);
            return new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            throw new CfsException(CfsException.CODE_INVALID_DATE);
        }
    }

    public static String getCurrentDay() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = new Date();
        String result = df.format(today);
        return result;
    }

    public static boolean isValidTransactionAmount(long amount) {
        if (amount > CCConstants.MAX_DEPOSITION_AMOUNT || amount < CCConstants.MIN_DEPOSITION_AMOUNT) {
            return false;
        }
        return true;
    }

    public static void validScript(String str) throws CfsException {
        if (str.contains("&amp;") || str.contains("&lt;") || str.contains("&gt;") || str.contains("&quot;")) {
            throw new CfsException(CfsException.CODE_INVALID_INPUT_DATA);
        }
        if (str.contains("<script>") || str.contains("</script>")) {
            throw new CfsException(CfsException.CODE_INVALID_INPUT_DATA);
        }

    }
}
