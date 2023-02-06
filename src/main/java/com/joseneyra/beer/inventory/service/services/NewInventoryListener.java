package com.joseneyra.beer.inventory.service.services;

import com.joseneyra.beer.inventory.service.config.JmsConfig;
import com.joseneyra.beer.inventory.service.domain.BeerInventory;
import com.joseneyra.beer.inventory.service.repositories.BeerInventoryRepository;
import com.joseneyra.brewery.model.events.NewInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event) {
        log.debug("Got Inventory: " + event.toString());

        beerInventoryRepository.save(BeerInventory.builder()
                        .id(event.getBeerDto().getId())
                        .beerId(event.getBeerDto().getId())
                        .upc(event.getBeerDto().getUpc())
                        .quantityOnHand(event.getBeerDto().getQuantityOnHand())
                        .build());
    }
}
