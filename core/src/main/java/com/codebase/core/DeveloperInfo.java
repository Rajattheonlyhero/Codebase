package com.codebase.core;


import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface DeveloperInfo {

    public String getDeveloperName();
    public String getDeveloperProfile();
    public String getDeveloperCTC();
}
