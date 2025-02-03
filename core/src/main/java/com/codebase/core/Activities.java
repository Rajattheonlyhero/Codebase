package com.codebase.core;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType

public interface Activities {
    String[] getRandomActivity();
}