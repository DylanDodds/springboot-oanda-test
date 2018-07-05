package com.dylandodds.resttest.resttest.manager;

import com.dylandodds.resttest.resttest.config.OAndaConfig;
import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.pricing.ClientPrice;
import com.oanda.v20.pricing.PricingGetRequest;
import com.oanda.v20.pricing.PricingGetResponse;
import com.oanda.v20.primitives.DateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class OAndaManager {
    private final OAndaConfig oAndaConfig;
    private final AccountID accountID;

    private List<String> instruments;
    private Context ctx;

    @Autowired
    public OAndaManager(OAndaConfig oAndaConfig){
        this.oAndaConfig = oAndaConfig;
        this.accountID = new AccountID(oAndaConfig.getAccountNumber());
        this.instruments = new ArrayList<>(Arrays.asList("EUR_USD", "USD_JPY", "GBP_USD", "USD_CHF"));
    }

    @PostConstruct
    public void intialize(){
        ctx = new ContextBuilder(oAndaConfig.getUrl())
            .setToken(oAndaConfig.getApiToken())
            .setApplication("OAndaManager")
            .build();
    }

    public List<ClientPrice> PollPrices()
    {
        return PollPricesSince(null);
    }

    public List<ClientPrice> PollPricesSince(DateTime since)
    {


        try{
            List<ClientPrice> clientPrices = new ArrayList<>();
            log.info("Polling Prices...");
            PricingGetRequest request = new PricingGetRequest(accountID, instruments);

            if(since != null)
            {
                log.info("Poll since: {}", since);
                request.setSince(since);
            }

            PricingGetResponse response = ctx.pricing.get(request);
            for(ClientPrice price : response.getPrices())
            {
                clientPrices.add(price);
            }

            return clientPrices;
        } catch (Exception e)
        {
            log.error("Could not poll prices at this time");
            return new ArrayList<>();
        }



    }
}
