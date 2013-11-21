package com.psilonsoft.model.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.psilonsoft.model.config.ModelAndRepoConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ModelAndRepoConfig.class, })
@TransactionConfiguration(defaultRollback = false)
public class BaseModelTestIT extends AbstractTransactionalJUnit4SpringContextTests {

    @Test
    public void success() {
        // simple empty test to test mapping correctness
    }

}
