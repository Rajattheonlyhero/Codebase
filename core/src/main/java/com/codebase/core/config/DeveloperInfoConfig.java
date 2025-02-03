package com.codebase.core.config;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "DeveloperInfo Configuration", description = "Configurations for Developers")
public @interface DeveloperInfoConfig {
    @AttributeDefinition(
            name = "Developer's Name", type = AttributeType.STRING
    )
    public String getDeveloperName() default "Rajat";
    @AttributeDefinition(
            name = "Developer's Profile", type = AttributeType.STRING,
            options = {
                    @Option(label = "Frontend Developer", value = "Frontend"),
                    @Option(label = "Backend Developer", value = "Backend"),
                    @Option(label = "Full Stack Developer", value = "Fullstack"),
                    @Option(label = "DevOps Engineer", value = "Devops")
            }
    )
    public String getDeveloperProfile() default "fullstack";
    @AttributeDefinition(
            name = "Developer's CTC", type = AttributeType.INTEGER,
            options = {
                    @Option(label = "10 LPA", value = "10 LPA"),
                    @Option(label = "12 LPA", value = "12 LPA"),
                    @Option(label = "15 LPA", value = "15 LPA"),
                    @Option(label = "20 LPA", value = "20 LPA")
            }
    )
    public String getDeveloperCTC() default "10 LPA";
}
