package com.lambdanum.mcdisc.repository;

import com.lambdanum.mcdisc.DiscRepository;
import com.lambdanum.mcdisc.model.Disc;

import java.util.List;

public class CachedDiscRepository extends DiscRepository {

    private List<Disc> discs;

    public CachedDiscRepository(DiscRepository discRepository) {
        discs = discRepository.getDiscs();
    }

    @Override
    public List<Disc> getDiscs() {
        return discs;
    }
}
