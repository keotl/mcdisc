package com.lambdanum.mcdisc.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lambdanum.mcdisc.DiscRepository;
import com.lambdanum.mcdisc.McDiscMod;
import com.lambdanum.mcdisc.model.Disc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileDiscRepository extends DiscRepository {

    private Logger logger = LogManager.getLogger(McDiscMod.MODID);

    private String discListLocation;
    private Gson mapper = new GsonBuilder().serializeNulls().create();

    public FileDiscRepository(String discListLocation) {
        this.discListLocation = discListLocation;
    }

    @Override
    public List<Disc> getDiscs() {
        try {
            List<String> fileLines = Files.readAllLines(Paths.get(discListLocation));
            String jsonBody = StringUtils.join(fileLines, "\n");
            return Arrays.asList(mapper.fromJson(jsonBody, Disc[].class));
        } catch (IOException e) {
            logger.error("Could not read disc list from file " + discListLocation);
            return Collections.emptyList();
        }
    }
}
