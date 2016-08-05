package com.deltastar.task7.core.repository.domain;

import com.deltastart.task7.core.constants.Util;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by tonythompson on 1/14/16.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findAllFund", query = "SELECT f FROM Fund f"),
        @NamedQuery(name = "findFundByFundName", query = "SELECT f FROM Fund f where f.fundName = :p_fundName"),
        @NamedQuery(name = "findFundByFundNameOrSymbol", query = "SELECT f FROM Fund f where f.fundName like :p_fundName or f.symbol like :p_symbol"),
        @NamedQuery(name = "findFundBySymbol", query = "SELECT f FROM Fund f where f.symbol= :p_symbol")

})
public class Fund {
    private int id;
    private String fundName;
    private String symbol;
    private String comment;
    private byte status;
    private long lastPrice;
    private Timestamp lastTransitionDay;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Fund(String userName, String symbol, String comment) {
        this.fundName = userName;
        this.symbol = symbol;
        this.comment = comment;
    }

    public Fund() {
    }


    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fundName", nullable = false, length = 256)
    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    @Basic
    @Column(name = "symbol", nullable = true, length = 256)
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Basic
    @Column(name = "comment", nullable = true, length = 512)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "lastPrice", nullable = false)
    public long getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(long lastPrice) {
        this.lastPrice = lastPrice;
    }

    @Transient
    public String getLastPriceForDisplay() {
        return Util.cashFormatForDisplay(lastPrice);
    }

    @Basic
    @Column(name = "createdAt", nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "lastTransitionDay", nullable = false)
    public Timestamp getLastTransitionDay() {
        return lastTransitionDay;
    }

    public void setLastTransitionDay(Timestamp lastTransitionDay) {
        this.lastTransitionDay = lastTransitionDay;
    }




    @Basic
    @Column(name = "updatedAt", nullable = false)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
