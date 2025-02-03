package com.codebase.core.impl;

import com.codebase.core.DeveloperInfo;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component(
        service = { DeveloperInfo.class }
)

public class DeveloperInfoImpl implements DeveloperInfo {

    private static final Logger logger = LoggerFactory.getLogger(DeveloperInfoImpl.class);
    private String developerName = "Rajat";
    private String developerProfile = "FullStack";
    private String developerCTC = "10 LPA";

    @Activate
    protected void activate(Map<String, Object> properties) {
        configure(properties, "Activated");
    }

    @Modified
    protected void modified(Map<String, Object> properties) {
        configure(properties, "Modified");
    }

    @Deactivate
    protected void deactivated(Map<String, Object> properties) {
        logger.info("#############Component (Deactivated) Good-bye");
    }

    protected void configure(Map<String, Object> properties, String status) {
        logger.info("#############Component (" +status+ ") " + getDeveloperName());
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
