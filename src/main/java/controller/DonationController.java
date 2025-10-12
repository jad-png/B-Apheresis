package controller;

import service.impl.DonationServiceImpl;

public class DonationController {
    private final DonationServiceImpl service;

    public DonationController(DonationServiceImpl service) {
        this.service = service;
    }
}
