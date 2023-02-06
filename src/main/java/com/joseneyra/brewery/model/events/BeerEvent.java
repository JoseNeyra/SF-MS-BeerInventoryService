package com.joseneyra.brewery.model.events;

import com.joseneyra.brewery.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -4491609997407250459L;

    private BeerDto beerDto;
}
