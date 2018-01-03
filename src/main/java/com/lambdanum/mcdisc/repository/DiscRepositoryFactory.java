package com.lambdanum.mcdisc.repository;

import com.lambdanum.mcdisc.DiscRepository;

public class DiscRepositoryFactory {

    public DiscRepository getDiscRepository(String discListLocation) {
        if (isAnUrl(discListLocation)) {
            return new RestDiscRepository(discListLocation);
        } else {
            return new FileDiscRepository(discListLocation);
        }
    }

    private boolean isAnUrl(String discListLocation) {
        return discListLocation.startsWith("http://")
            || discListLocation.startsWith("https://")
            || discListLocation.startsWith("ftp://");
    }
}
