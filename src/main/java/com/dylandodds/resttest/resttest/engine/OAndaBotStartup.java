package com.dylandodds.resttest.resttest.engine;

import com.dylandodds.resttest.resttest.service.IOandaService;
import com.dylandodds.resttest.resttest.service.impl.OAndaService;
import com.oanda.v20.pricing.ClientPrice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class OAndaBotStartup implements ApplicationListener<ApplicationReadyEvent> {

    private IOandaService oAndaService;

    @Autowired
    public OAndaBotStartup(OAndaService oAndaService){
        this.oAndaService = oAndaService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent)
    {
        List<ClientPrice> clientPriceList = oAndaService.getPrices();
        for(ClientPrice price : clientPriceList)
        {
            log.info("price: {}", price);
        }
    }
}
