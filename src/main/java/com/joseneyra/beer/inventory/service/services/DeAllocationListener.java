package com.joseneyra.beer.inventory.service.services;

import com.joseneyra.beer.inventory.service.config.JmsConfig;
import com.joseneyra.brewery.model.events.DeAllocateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeAllocationListener {

    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeAllocateOrderRequest request) {
        allocationService.deAllocateOrder(request.getBeerOrderDto());
    }
}
