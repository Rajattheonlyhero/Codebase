package com.codebase.core.config;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="Activities Configuration", description = "Configuration for Activities class")
public @interface ActivitiesConfig {
    @AttributeDefinition(
            name = "Available Activities",
            description = "List of activities that can be selected",
            defaultValue = {"Camping","Skateboarding", "Skiing"}
    )
    String [] getRandomActivity();
}