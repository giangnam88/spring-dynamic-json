package com.namnx.spring_dynamic_json.rest;

import com.namnx.spring_dynamic_json.util.DynamicJson;
import com.namnx.spring_dynamic_json.entity.BookingEntity;
import com.namnx.spring_dynamic_json.util.JsonFilterType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingRestController {

    @GetMapping("")
    public ResponseEntity<BookingEntity> detail() {
        return ResponseEntity.ok(BookingEntity.onDefault());
    }

    @GetMapping("/with-dynamic-json")
    public ResponseEntity<MappingJacksonValue> detailWithDynamicJson() {
        return ResponseEntity.ok(DynamicJson
                .with(BookingEntity.onDefault())
                .except(JsonFilterType.BOOKING, "id")
                .result()
        );
    }
}
