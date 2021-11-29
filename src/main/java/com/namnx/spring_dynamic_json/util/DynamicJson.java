package com.namnx.spring_dynamic_json.util;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.Map;
import java.util.Set;

public class DynamicJson<T> {

    protected final T value;
    protected MappingJacksonValue mappingJacksonValue;

    private DynamicJson(T value) {
        this.value = value;
    }

    public static <T> DynamicJson<T> with(T value) {
        return new DynamicJson<>(value);
    }

    public DynamicJson<T> except(String filterName, String... fieldsIgnore) {
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter(filterName, SimpleBeanPropertyFilter.serializeAllExcept(fieldsIgnore))
                .setFailOnUnknownId(false);
        setFilter(filterProvider);

        return this;
    }

    public DynamicJson<T> except(String filterName, Set<String> fieldsIgnore) {
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter(filterName, SimpleBeanPropertyFilter.serializeAllExcept(fieldsIgnore))
                .setFailOnUnknownId(false);
        setFilter(filterProvider);

        return this;
    }

    public DynamicJson<T> except(Map<String, Set<String>> filterNameAndFieldsIgnore) {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterNameAndFieldsIgnore.forEach((filterName, fieldsIgnore)
                -> filterProvider.addFilter(filterName, SimpleBeanPropertyFilter.serializeAllExcept(fieldsIgnore)));

        filterProvider.setFailOnUnknownId(false);
        setFilter(filterProvider);

        return this;
    }

    public DynamicJson<T> include(String filterName, String... fieldsInclude) {
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter(filterName, SimpleBeanPropertyFilter.filterOutAllExcept(fieldsInclude))
                .setFailOnUnknownId(false);
        setFilter(filterProvider);

        return this;
    }

    public DynamicJson<T> include(String filterName, Set<String> fieldsInclude) {
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter(filterName, SimpleBeanPropertyFilter.filterOutAllExcept(fieldsInclude))
                .setFailOnUnknownId(false);
        setFilter(filterProvider);

        return this;
    }

    public DynamicJson<T> include(Map<String, Set<String>> filterNameAndFieldsInclude) {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterNameAndFieldsInclude.forEach((filterName, fieldsInclude)
                -> filterProvider.addFilter(filterName, SimpleBeanPropertyFilter.filterOutAllExcept(fieldsInclude)));

        filterProvider.setFailOnUnknownId(false);
        setFilter(filterProvider);

        return this;
    }

    public MappingJacksonValue result() {
        return this.mappingJacksonValue;
    }

    private void setFilter(FilterProvider filterProvider) {
        this.mappingJacksonValue = new MappingJacksonValue(value);
        this.mappingJacksonValue.setFilters(filterProvider);
    }

}
