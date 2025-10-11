package controller;

import service.impl.RecipientServiceImpl;

public class RecipientController {
    private final RecipientServiceImpl service;

    public RecipientController(RecipientServiceImpl service) {
        this.service = service;
    }

    
}
