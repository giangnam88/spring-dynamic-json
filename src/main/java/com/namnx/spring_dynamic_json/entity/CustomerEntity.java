package com.namnx.spring_dynamic_json.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.namnx.spring_dynamic_json.util.JsonFilterType;

import java.util.ArrayList;
import java.util.List;

@JsonFilter(JsonFilterType.CUSTOMER)
public class CustomerEntity {
    private long id;
    private String name;
    private String address;
    private List<BookingEntity> listBooking = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<BookingEntity> getListBooking() {
        return listBooking;
    }

    public void setListBooking(List<BookingEntity> listBooking) {
        this.listBooking = listBooking;
    }
}
