package com.lambdanum.mcdisc.repository;

import static junit.framework.TestCase.assertTrue;

import com.lambdanum.mcdisc.DiscRepository;
import com.lambdanum.mcdisc.McdiscConfig;

import org.junit.BeforeClass;
import org.junit.Test;

public class DiscRepositoryFactoryTest {

    private DiscRepositoryFactory discRepositoryFactory = new DiscRepositoryFactory();

    @BeforeClass
    public static void setNoCacheGlobalSetting() {
        McdiscConfig.SHOULD_CACHE_DISC_LIST = false;
    }

    @Test
    public void givenAnHttpUrl_whenGettingDiscRepository_thenRestDiscRepositoryIsCreated() {
        String httpUrl = "http://mcdisc.example.com";

        DiscRepository discRepository = discRepositoryFactory.getDiscRepository(httpUrl);

        assertTrue(discRepository instanceof RestDiscRepository);
    }

    @Test
    public void givenAnHttpsUrl_whenGettingDiscRepository_thenRestDiscRepositoryIsCreated() {
        String httpsUrl = "https://mcdisc.example.com";

        DiscRepository discRepository = discRepositoryFactory.getDiscRepository(httpsUrl);

        assertTrue(discRepository instanceof RestDiscRepository);
    }

    @Test
    public void givenAnFtpUrl_whenGettingDiscRepository_thenRestDiscRepositoryIsCreated() {
        String ftpUrl = "ftp://mcdisc.example.com";

        DiscRepository discRepository = discRepositoryFactory.getDiscRepository(ftpUrl);

        assertTrue(discRepository instanceof RestDiscRepository);
    }

    @Test
    public void givenAFilename_whenGettingDiscRepository_thenReturnFileDiscRepository() {
        String filename = "file";

        DiscRepository discRepository = discRepositoryFactory.getDiscRepository(filename);

        assertTrue(discRepository instanceof FileDiscRepository);
    }
}
