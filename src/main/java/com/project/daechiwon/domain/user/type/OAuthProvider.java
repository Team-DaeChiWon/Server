package com.project.daechiwon.domain.user.type;

import java.util.Arrays;
import java.util.Optional;

public enum OAuthProvider {
    GOOGLE;

    public static Optional<OAuthProvider> fromName(String name) {
        return Arrays.stream(OAuthProvider.values()).filter(it -> it.name().equalsIgnoreCase(name)).findFirst();
    }
}
