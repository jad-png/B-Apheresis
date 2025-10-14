package servlets;

import config.DIContainer;
import controller.DonorController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DonorServlet extends HttpServlet {
    private DonorController controller;

    @Override
    public void init() throws ServletException {
        this.controller = DIContainer.getInstance().getBean(DonorController.class);
    }
}