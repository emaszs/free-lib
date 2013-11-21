package com.psilonsoft.model.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Item;
import com.psilonsoft.model.entities.Trade;
import com.psilonsoft.model.entities.User;
import com.psilonsoft.model.repository.ItemRepository;
import com.psilonsoft.model.repository.TradeRepository;
import com.psilonsoft.model.repository.UserRepository;

public class TradeRepositoryIT extends BaseModelTestIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Transactional
    public void canCreateAndRetrieveTrade() {
        //
        User user = new User();
        user = Helpers.prepareFullyPopulatedUser();
        user = userRepository.save(user);


        Item item = new Item();
        item = Helpers.generateItems(1).get(0);

        item = itemRepository.save(item);


        Trade tr = new Trade();
        Date date = new Date();
        tr.setDate(date);
        tr.setUser(user);
        tr.setItem(item);
        tr = tradeRepository.save(tr);

        Trade retrievedTrade = tradeRepository.findOne(tr.getId());

        Assert.assertEquals("Expecting dates to be the same", date, retrievedTrade.getDate());
        Assert.assertEquals("Expecting items to be the same", item, retrievedTrade.getItem());
        Assert.assertEquals("Expecting users to be the same", user, retrievedTrade.getUser());
    }


}
