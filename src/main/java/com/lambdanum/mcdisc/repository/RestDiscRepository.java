package com.lambdanum.mcdisc.repository;

import com.lambdanum.mcdisc.model.Disc;
import com.lambdanum.mcdisc.DiscRepository;
import com.lambdanum.mcdisc.infrastructure.util.HttpUtil;

import java.util.Arrays;
import java.util.List;

public class RestDiscRepository implements DiscRepository {

    private String serverUrl;
    private HttpUtil httpUtil;

    public RestDiscRepository(String serverUrl, HttpUtil httpUtil) {
        this.serverUrl = serverUrl;
        this.httpUtil = httpUtil;
    }

    @Override
    public List<Disc> getDiscs() {
        return Arrays.asList(httpUtil.get(serverUrl, Disc[].class));
    }
}
