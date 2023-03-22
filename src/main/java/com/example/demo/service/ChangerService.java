package com.example.demo.service;

import com.example.demo.enums.CurrencyType;
import com.example.demo.model.CurrencyModel;
import com.example.demo.model.CurrencyRatesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangerService {
    private final CurrencyRatesService currencyRatesService;

    @Autowired
    public ChangerService(CurrencyRatesService currencyRatesService) {
        this.currencyRatesService = currencyRatesService;
    }


    public Double change(String currency, Double amount) throws Exception {
        CurrencyType currencyType = CurrencyType.valueOf(currency);
        CurrencyRatesModel model = currencyRatesService.getModelFromApi();
        for (CurrencyModel currencyModel : model.getCurrencyModelList()) {
            if (currencyModel.getIsoCode().equals(currencyType))
                return (amount * currencyModel.getValue());
        }
        return 0D;
    }
}
