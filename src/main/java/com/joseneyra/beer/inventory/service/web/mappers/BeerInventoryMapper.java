package com.joseneyra.beer.inventory.service.web.mappers;

import com.joseneyra.brewery.model.BeerInventoryDto;
import com.joseneyra.beer.inventory.service.domain.BeerInventory;
import org.mapstruct.Mapper;

/**
 * Created by jt on 2019-05-31.
 */
@Mapper(uses = {DateMapper.class})
public interface BeerInventoryMapper {

    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDTO);

    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);
}
