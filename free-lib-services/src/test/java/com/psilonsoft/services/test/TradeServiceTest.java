package com.psilonsoft.services.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.psilonsoft.model.entities.Item;
import com.psilonsoft.model.entities.User;
import com.psilonsoft.model.repository.ItemRepository;
import com.psilonsoft.model.repository.TradeRepository;
import com.psilonsoft.model.repository.UserRepository;
import com.psilonsoft.services.TradeService;
import com.psilonsoft.services.exceptions.NoItemsLeftException;

public class TradeServiceTest extends BaseServiceTest {
    @Autowired
    private TradeService tradeService;
    
    /**
     * Mock
     */
    @Autowired
    private TradeRepository tradeRepository;
    
    /**
     * Mock
     */
    @Autowired
    private ItemRepository itemRepository;

    /**
     * Mock
     */
    @Autowired
    private UserRepository userRepository;

    @Test
    public void tradeIsSuccessfulyLogged() {
        User user = new User();
        Item item = new Item();
        item.setId(1L);
        item.setStock(1);
        item.setPrice(20);
        user.setId(1L);
        user.setDebt(0);

        Mockito.when(itemRepository.findOne(item.getId())).thenReturn(item);

        try {
            tradeService.logTakenItem(item, user);
        } catch (NoItemsLeftException e) {
            e.printStackTrace();
        }

        Assert.assertEquals("User should have increased debt", 20, user.getDebt());
        Assert.assertEquals("Item should have less stock", 0, item.getStock());

    }

    @Test(expected = NoItemsLeftException.class)
    public void cannotWithdrawMoreItemsThanThereIsStock() throws NoItemsLeftException {
        User user = new User();
        Item item = new Item();
        item.setId(1L);
        item.setStock(0);
        item.setPrice(20);

        Item item2 = new Item();
        item2.setId(1L);
        item2.setStock(0);
        item2.setName("name");
        item2.setPrice(20);

        tradeService.logTakenItem(item, user);
    }

    // @Test
    // public void debtIsCalculatedCorrectly() throws NoItemsLeftException {
    // User user = UnitTestHelpers.prepareFullyPopulatedUser();
    //
    // Item item1 = UnitTestHelpers.generateItems(1).get(0);
    // Item item2 = UnitTestHelpers.generateItems(1).get(0);
    //
    // Trade trade1 = new Trade();
    // trade1.setItem(item1);
    // trade1.setUser(user);
    //
    // Trade trade2 = new Trade();
    // trade2.setItem(item1);
    // trade2.setUser(user);
    //
    // List<Trade> trades = new ArrayList<Trade>(Arrays.asList(trade1, trade2));
    //
    // // tradeService.logTakenItem(item1, user);
    // // tradeService.logTakenItem(item2, user);
    //
    // Mockito.when(user.getTrades()).thenReturn(trades);
    //
    // Assert.assertEquals("User should have exact debt", item1.getPrice() + item2.getPrice(),
    // tradeService.getTotalUserDebt(user));
    // }
}