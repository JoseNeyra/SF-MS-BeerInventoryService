package com.joseneyra.beer.inventory.service.services;

import com.joseneyra.beer.inventory.service.domain.BeerInventory;
import com.joseneyra.beer.inventory.service.repositories.BeerInventoryRepository;
import com.joseneyra.brewery.model.BeerOrderDto;
import com.joseneyra.brewery.model.BeerOrderLineDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class AllocationServiceImpl implements AllocationService{

    private final BeerInventoryRepository beerInventoryRepository;
    @Override
    public Boolean allocateOrder(BeerOrderDto beerOrderDto) {
        log.debug("Allocating OrderId: " + beerOrderDto.getId());

        AtomicInteger totalOrdered = new AtomicInteger();
        AtomicInteger totalAllocated = new AtomicInteger();

        beerOrderDto.getBeerOrderLines().forEach(beerOrderLine -> {
            int orderQty = beerOrderLine.getOrderQuantity() != null ? beerOrderLine.getOrderQuantity() : 0;
            int allocatedQty = beerOrderLine.getQuantityAllocated() != null ? beerOrderLine.getQuantityAllocated() : 0;
            if(((orderQty - allocatedQty) > 0)){
                allocateBeerOrderLine(beerOrderLine);
            }
            totalOrdered.set(totalAllocated.get() + orderQty);
            totalAllocated.set(totalAllocated.get() + allocatedQty);
        });

        log.debug("Total Ordered: " + totalOrdered.get() + ", Total Allocated: " + totalAllocated.get());

        return totalOrdered.get() == totalAllocated.get();
    }

    private void allocateBeerOrderLine(BeerOrderLineDto beerOrderLineDto) {
        List<BeerInventory> beerInventoryList = beerInventoryRepository.findAllByUpc(beerOrderLineDto.getUpc());

        beerInventoryList.forEach(beerInventory -> {
            int inventory = (beerInventory.getQuantityOnHand() == null) ? 0 : beerInventory.getQuantityOnHand();
            int orderQty = (beerOrderLineDto.getOrderQuantity() == null) ? 0 : beerOrderLineDto.getOrderQuantity();
            int allocatedQty = (beerOrderLineDto.getQuantityAllocated() == null) ? 0 : beerOrderLineDto.getQuantityAllocated();
            int qtyToAllocate = orderQty - allocatedQty;

            if (inventory >= qtyToAllocate) {       // Full Allocation
                inventory = inventory - qtyToAllocate;
                beerOrderLineDto.setQuantityAllocated(orderQty);
                beerInventory.setQuantityOnHand(inventory);

                beerInventoryRepository.save(beerInventory);
            } else if (inventory > 0) {             // Partial Allocation
                beerOrderLineDto.setQuantityAllocated(allocatedQty + inventory);
                beerInventory.setQuantityOnHand(0);

                beerInventoryRepository.delete(beerInventory);
            }
        });
    }
}
