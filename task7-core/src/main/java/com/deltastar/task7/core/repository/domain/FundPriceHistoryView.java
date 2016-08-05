package com.deltastar.task7.core.repository.domain;

import com.deltastart.task7.core.constants.Util;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@NamedQueries({
        @NamedQuery(name = "findFundPriceHistoryByFundId", query = "SELECT fphv FROM FundPriceHistoryView fphv where fphv.fundId = :p_fundId order by fphv.priceDate desc")
})
public class FundPriceHistoryView {
    private int id;
    private int fundId;
    private long price;

    private Timestamp priceDate;
    private String fundName;

    @Id
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
    @Column(name = "fundName", nullable = true, length = 256)
    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }


}
