package com.psilonsoft.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Item;
import com.psilonsoft.model.entities.Trade;
import com.psilonsoft.model.entities.User;
import com.psilonsoft.model.repository.ItemRepository;
import com.psilonsoft.model.repository.TradeRepository;
import com.psilonsoft.model.repository.UserRepository;
import com.psilonsoft.services.TradeService;
import com.psilonsoft.services.exceptions.NoItemsLeftException;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates and stores a new Trade(transaction where a user withdraws an item from the warehouse)
     * 
     */
    @Override
    @Transactional
    public void logTakenItem(final Item item, final User user) throws NoItemsLeftException {
        Trade trade = new Trade();

        trade.setDate(new Date());
        if (item != null && user != null) {
            trade.setItem(item);
            trade.setUser(user);

            if (item.getStock() <= 0) {
                throw new NoItemsLeftException("There are no more items to take.");
            }
            tradeRepository.save(trade);

            item.setStock(item.getStock() - 1);
            itemRepository.save(item);

            // user.setDebt(user.getDebt() + item.getPrice());
            userRepository.save(user);
        }
    }

    // @Override
    // public int getTotalUserDebt(final User user) {
    // int debt = 0;
    // Iterable<Trade> allTrades = tradeRepository.findAll();
    // //Iterable<Trade> allUserTrades = user.getTrades();
    //
    // for (Trade trade : allUserTrades) {
    // debt += trade.getItem().getPrice();
    // }
    // return debt;
    // }

}
