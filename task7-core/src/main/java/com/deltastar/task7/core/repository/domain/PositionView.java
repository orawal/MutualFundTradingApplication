package com.deltastar.task7.core.repository.domain;

import com.deltastart.task7.core.constants.Util;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@NamedQueries({
        @NamedQuery(name = "findPositionViewByCustomerIdAndStatus", query = "SELECT pv FROM PositionView pv where pv.customerId = :p_customerId and pv.status = :p_status order by pv.updatedAt desc ")
})
public class PositionView {
    private int id;
    private int customerId;
    private int fundId;
    private long shares;
    private long amount;
    private Byte status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String fundName;
    private long lastPrice;
    private String userName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "customerId", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "fundId", nullable = false)
    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    @Basic
    @Column(name = "shares", nullable = false)
    public long getShares() {
        return shares;
    }

    @Transient
    public String getSharesForDisplay() {
        return Util.formatCash(shares);
    }

    @Transient
    public String getLastPriceForDisplay() {
        return Util.cashFormatForDisplay(lastPrice);
    }

    @Transient
    public String getAmountForDisplay() {
        return Util.cashFormatForDisplay(amount);
    }


    public void setShares(long shares) {
        this.shares = shares;
    }


    @Basic
    @Column(name = "amount", nullable = false)
    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }


    @Basic
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
    @Column(name = "updatedAt", nullable = false)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "fundName", nullable = true, length = 256)
    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    @Basic
    @Column(name = "lastPrice", nullable = false)
    public long getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(long lastPrice) {
        this.lastPrice = lastPrice;
    }

    @Basic
    @Column(name = "userName", nullable = true, length = 32)
    public String getUserName() {
        return userName;
    }


    @Transient
    public String getDisplayStatus() {
        return Util.getDisplayStatusForPosition(status);
    }

    @Transient
    public String getValue() {
        return Util.formatCash(shares == 0 ? amount : lastPrice * shares, shares != 0);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
