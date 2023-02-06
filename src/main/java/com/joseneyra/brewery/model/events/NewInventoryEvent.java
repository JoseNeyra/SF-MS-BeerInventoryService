package com.joseneyra.brewery.model.events;

import com.joseneyra.brewery.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
