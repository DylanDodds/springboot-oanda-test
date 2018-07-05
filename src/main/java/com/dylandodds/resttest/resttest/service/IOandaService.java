package com.dylandodds.resttest.resttest.service;


import com.oanda.v20.pricing.ClientPrice;

import java.util.List;

public interface IOandaService {
    public List<ClientPrice> getPrices();
}
