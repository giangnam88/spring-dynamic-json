package com.namnx.spring_dynamic_json.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.github.javafaker.Faker;
import com.namnx.spring_dynamic_json.util.JsonFilterType;

import java.time.LocalDateTime;

@JsonFilter(JsonFilterType.BOOKING)
public class BookingEntity {
    private long id;
    private LocalDateTime time;
    private String name;

    public static BookingEntity onDefault() {
        BookingEntity b = new BookingEntity();
        Faker faker = new Faker();
        b.setId(1L);
        b.setTime(LocalDateTime.now());
        b.setName(faker.name().fullName());
        return b;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
