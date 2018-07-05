package com.dylandodds.resttest.resttest.service.impl;

import com.dylandodds.resttest.resttest.manager.OAndaManager;
import com.dylandodds.resttest.resttest.service.IOandaService;
import com.oanda.v20.pricing.ClientPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OAndaService implements IOandaService {

    private final OAndaManager oAndaManager;

    @Autowired
    public OAndaService(OAndaManager oAndaManager) {
        this.oAndaManager = oAndaManager;
    }

    public List<ClientPrice> getPrices()
    {
        return oAndaManager.PollPricesSince(null);
    }
}
