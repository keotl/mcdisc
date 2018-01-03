package com.lambdanum.mcdisc.repository;

import com.google.gson.Gson;
import com.lambdanum.mcdisc.DiscRepository;
import com.lambdanum.mcdisc.infrastructure.util.HttpUtil;

public class DiscRepositoryFactory {

    private static final String RELEASE_URL = "https://boiling-forest-57763.herokuapp.com/release";

    public DiscRepository getDiscRepository() {
        return new RestDiscRepository(RELEASE_URL, new HttpUtil(new Gson()));
    }
}
