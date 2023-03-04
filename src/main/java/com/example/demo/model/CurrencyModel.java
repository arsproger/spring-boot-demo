package com.example.demo.model;


import com.example.demo.enums.CurrencyType;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JacksonXmlRootElement(localName = "Currency")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrencyModel {

    @JacksonXmlProperty(localName = "ISOCode", isAttribute = true)
    @Enumerated(EnumType.STRING)
    CurrencyType isoCode;

    @JacksonXmlProperty(localName = "Nominal")
    Integer nominal;

    @JacksonXmlProperty(localName = "Value")
    BigDecimal value;

    public void setValue(String value) {
        value = value.replace(',', '.');
        this.value = new BigDecimal(value);
    }
}