package com.joseneyra.beer.inventory.service.services;

import com.joseneyra.brewery.model.BeerOrderDto;

public interface AllocationService {

    Boolean allocateOrder(BeerOrderDto beerOrderDto);

    void deAllocateOrder(BeerOrderDto beerOrderDto);
}
