package com.codebase.core.impl;

import com.codebase.core.DeveloperInfo;
import com.codebase.core.config.DeveloperInfoConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component(
        service = { DeveloperInfo.class }
)

@Designate(ocd = DeveloperInfoConfig.class)
public class DeveloperInfoImpl implements DeveloperInfo {

    private static final Logger logger = LoggerFactory.getLogger(DeveloperInfoImpl.class);
    private String developerName;
    private String developerProfile;
    private String developerCTC;


    @Activate
    protected void activate(DeveloperInfoConfig config) {
        developerName = config.getDeveloperName();
        developerProfile =config.getDeveloperProfile();
        developerCTC = config.getDeveloperCTC();
    }

    @Deactivate
    protected void deactivated(Map<String, Object> properties) {
        logger.info("DeveloperInfo Component deactivated");
    }

    @Override
    public String getDeveloperName() {
        return developerName;
    }

    @Override
    public String getDeveloperProfile() {
        return developerProfile;
    }

    @Override
    public String getDeveloperCTC() {
        return developerCTC;
    }
}
