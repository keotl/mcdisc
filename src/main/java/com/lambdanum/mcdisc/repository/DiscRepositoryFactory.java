package com.lambdanum.mcdisc.repository;

import com.lambdanum.mcdisc.DiscRepository;
import com.lambdanum.mcdisc.McdiscConfig;

public class DiscRepositoryFactory {

    private static DiscRepository cachedDiscRepository;

    public DiscRepository getDiscRepository(String discListLocation) {
        if (McdiscConfig.SHOULD_CACHE_DISC_LIST) {
            if (cachedDiscRepository == null) {
                cachedDiscRepository = new CachedDiscRepository(getDirectAccessRepository(discListLocation));
            }
            return cachedDiscRepository;
        } else {
            return getDirectAccessRepository(discListLocation);
        }
    }

    private DiscRepository getDirectAccessRepository(String discListLocation) {
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
