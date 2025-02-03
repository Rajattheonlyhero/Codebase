package com.codebase.core.impl;

import com.codebase.core.Activities;
import com.codebase.core.config.ActivitiesConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

@Component(
        service = {Activities.class}
)
@Designate(ocd = ActivitiesConfig.class)

public class ActivitiesImpl implements Activities {
    private static final Logger logger = LoggerFactory.getLogger(ActivitiesImpl.class);

    public String[] activities;
    public final Random random = new Random();

    @Activate
    @Modified
    public void activate(ActivitiesConfig config){
        this.activities= new String[]{Arrays.toString(config.getRandomActivity())};
        logger.info("OSGI configuration updated :{}", String.join(",", activities));
    }
    public String[] getRandomActivity(){
        return activities;
    }

}