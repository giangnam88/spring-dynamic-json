package com.namnx.spring_dynamic_json.rest;

import com.github.javafaker.Faker;
import com.namnx.spring_dynamic_json.util.DynamicJson;
import com.namnx.spring_dynamic_json.entity.BookingEntity;
import com.namnx.spring_dynamic_json.entity.CustomerEntity;
import com.namnx.spring_dynamic_json.util.JsonFilterType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRestController.class);

    private List<CustomerEntity> customers = new ArrayList<>();

    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {
        for (int i = 1; i <= 100; i++) {
            Faker faker = new Faker();
            CustomerEntity c = new CustomerEntity();
            c.setId(i);
            c.setAddress(faker.address().fullAddress());
            c.setName(faker.name().fullName());

            for (int i2 = 1; i2 <= 100; i2++) {
                BookingEntity b = new BookingEntity();
                b.setId(i2);
                b.setTime(LocalDateTime.now());
                b.setName(faker.name().name());
                c.getListBooking().add(b);
            }
            customers.add(c);
        }
        LOGGER.info("generate data completely");
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerEntity>> getAll() {
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/all-with-dynamic-json")
    public ResponseEntity<MappingJacksonValue> getAllWithDynamicJson() {

        return ResponseEntity.ok(DynamicJson
                .with(customers)
                .except(Map.of(
                        JsonFilterType.CUSTOMER, Set.of("address", "id"),
                        JsonFilterType.BOOKING, Set.of("name")
                ))
                .result()
        );
    }


    @GetMapping("/all-with-dynamic-json2")
    public ResponseEntity<MappingJacksonValue> getAllWithDynamicJson2() {

        return ResponseEntity.ok(DynamicJson
                .with(customers)
                .include(Map.of(
                        JsonFilterType.CUSTOMER, Set.of("listBooking", "id"),
                        JsonFilterType.BOOKING, Set.of("name")
                ))
                .result()
        );
    }
}
