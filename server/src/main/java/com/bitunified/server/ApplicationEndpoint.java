package com.bitunified.server;

import com.bitunified.ledconfig.LedConfig;
import com.bitunified.ledconfig.PriceCalculator;
import com.bitunified.ledconfig.ProductConfigResult;
import com.bitunified.ledconfig.configuration.parser.steps.Data;
import com.bitunified.ledconfig.productconfiguration.Models;
import com.bitunified.ledconfig.productconfiguration.PriceList;
import com.bitunified.ledconfig.productconfiguration.ProductConfiguration;
import com.bitunified.ledconfig.productconfiguration.Relations;
import com.bitunified.ledconfig.productconfiguration.price.PriceCount;
import com.bitunified.ledconfig.productconfiguration.steps.Steps;
import com.bitunified.server.models.PartResult;
import com.bitunified.server.service.PriceService;
import com.bitunified.server.service.StepService;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Path("/engine")
public class ApplicationEndpoint extends ConfigApplication {

    @Context
    ServletContext context;

    public ApplicationEndpoint() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (!(Data.parser != null && Data.parser.getModels().size() > 10)) {
            updateData(context);
        }

    }

    @GET
    @Path("/steps")
    @Produces(MediaType.APPLICATION_JSON)
    public Steps getAllStepss() {
        StepService stepService = new StepService();

        return stepService.getSteps();
    }

    @GET
    @Path("/models")
    @Produces(MediaType.APPLICATION_JSON)
    public Models getAllModels() {
        Models models = new Models();
        models.setModels(Data.parser.getModels());
        return models;
    }

    @GET
    @Path("/relations")
    @Produces(MediaType.APPLICATION_JSON)
    public Relations getAllRelations() {
        Relations relations = new Relations();
        relations.setRelations(Data.parser.getRelationDefinitions());
        return relations;
    }


    @POST
    @Path("/submitconfig")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PriceList submitConfiguration(ProductConfiguration config) {

        LedConfig ledConfig = new LedConfig();
        ProductConfigResult productConfigResult = ledConfig.rules(config, Data.parser);

        PriceList priceList = new PriceList();

        PriceCalculator priceCalculator = new PriceCalculator();
        BigDecimal price = priceCalculator.calculate(productConfigResult);
        List<PriceCount> prices = productConfigResult.getPartList().entrySet().stream().map(item -> {
            if (item.getKey() != null && item.getValue() != null) {
                PriceCount pc = new PriceCount();
                pc.setCount(item.getValue());
                pc.setPart(item.getKey());
                return pc;
            }
            return null;
        }).collect(Collectors.toList());
        priceList.setPrices(prices);

        priceList.setTotalPrice(price.doubleValue());
        return priceList;
    }

    @POST
    @Path("/part")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PartResult getPart(ProductConfiguration config, @QueryParam("currentStep") Integer currentStep) {
        PriceService priceService = new PriceService();
        return priceService.getPart(config, currentStep);

    }
}
