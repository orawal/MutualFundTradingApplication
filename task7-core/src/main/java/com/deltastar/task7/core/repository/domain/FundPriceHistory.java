package com.deltastar.task7.core.repository.domain;

import com.deltastart.task7.core.constants.Util;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by tonythompson on 1/14/16.
 */
@Entity
public class FundPriceHistory {
    private int id;
    private int fundId;
    private long price;
    private Timestamp priceDate;
    private byte status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

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
    @Column(name = "fundId", nullable = false)
    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Transient
    public String getPriceForDisplay() {
        return Util.cashFormatForDisplay(price);
    }

    @Basic
    @Column(name = "priceDate", nullable = true)
    public Timestamp getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Timestamp priceDate) {
        this.priceDate = priceDate;
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

}
