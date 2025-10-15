package servlets;

import config.DIContainer;
import controller.RecipientController;
import mapper.RecipientMapper;

import javax.servlet.http.HttpServlet;

public class RecipientServlet extends HttpServlet {
    private RecipientController controller;
    private RecipientMapper mapper;

    @Override
    public void init() {
        this.controller = DIContainer.getInstance().getBean(RecipientController.class);
        this.mapper = DIContainer.getInstance().getBean(RecipientMapper.class);
    }

    
}
