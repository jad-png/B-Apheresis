package controller;

import service.impl.DonorServiceImpl;

public class DonorController {
    private final DonorServiceImpl donorSer;

    public  DonorController(DonorServiceImpl donorSer) {
        this.donorSer = donorSer;
    }

    
}
