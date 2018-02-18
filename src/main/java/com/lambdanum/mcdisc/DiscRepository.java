package com.lambdanum.mcdisc;

import com.lambdanum.mcdisc.model.Disc;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class DiscRepository {

    public abstract List<Disc> getDiscs();

    public Disc getRandomCustomDisc() {
        List<Disc> discs = getDiscs();
        int index = ThreadLocalRandom.current().nextInt(discs.size());
        return discs.get(index);
    }
}
