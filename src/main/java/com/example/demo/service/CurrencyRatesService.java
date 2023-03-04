package com.example.demo.service;

import com.example.demo.entity.CurrencyRates;
import com.example.demo.model.CurrencyRatesModel;
//import com.example.demo.repository.CurrencyRatesRepo;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;

@Slf4j
@Service
public class CurrencyRatesService {

    RestTemplate restTemplate;
//    CurrencyRatesRepo currencyRatesRepo;
    XmlMapper xmlMapper;

    public CurrencyRatesService(RestTemplate restTemplate
                                /*CurrencyRatesRepo currencyRatesRepo*/) {
        this.restTemplate = restTemplate;
//        this.currencyRatesRepo = currencyRatesRepo;
        this.xmlMapper = new XmlMapper();
    }

    public CurrencyRatesModel getModelFromApi() throws Exception {
        String xml = restTemplate.getForObject("https://www.nbkr.kg/XML/daily.xml", String.class);
        return xmlMapper.readValue(xml, CurrencyRatesModel.class);
    }

    public void saveModelFromApi() {
        try {
            CurrencyRatesModel model = getModelFromApi();
            CurrencyRates currencyRates = CurrencyRates.builder()
                    .createDate(new Timestamp(System.currentTimeMillis()))
                    .name(model.getName())
                    .currencyDate(Timestamp.valueOf(model.getDate().atStartOfDay()))
                    .currencyModelList(model.getCurrencyModelList())
                    .build();
//            currencyRatesRepo.save(currencyRates);
        } catch (Exception e) {
            log.error("saveModelFromApi()", e);
        }
    }
}
