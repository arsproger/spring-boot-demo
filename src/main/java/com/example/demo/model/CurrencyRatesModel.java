package com.example.demo.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "CurrencyRates")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrencyRatesModel {

    Long id;

    LocalDateTime createDate;

    @JacksonXmlProperty(localName = "Name", isAttribute = true)
    String name;

    @JacksonXmlProperty(localName = "Date", isAttribute = true)
    LocalDate date;

    @JacksonXmlProperty(localName = "Currency")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<CurrencyModel> currencyModelList;

    public void setDate(String date) {
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
