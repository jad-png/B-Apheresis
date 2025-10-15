package servlets;

import config.DIContainer;
import controller.RecipientController;
import dto.DonorDTO;
import dto.RecipientDTO;
import entity.Recipient;
import mapper.RecipientMapper;
import utils.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class RecipientServlet extends HttpServlet {
    private RecipientController controller;
    private RecipientMapper mapper;

    @Override
    public void init() {
        this.controller = DIContainer.getInstance().getBean(RecipientController.class);
        this.mapper = DIContainer.getInstance().getBean(RecipientMapper.class);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    }

    //    --------- Views -------
    private void showCreateForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Router.goTo(res, req, "/recipient/create");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        Optional<RecipientDTO> recipientOpt = controller.getRecipientById(id);
        recipientOpt.ifPresent(r -> req.setAttribute("recipient", r));
        Router.goTo(res, req, "/recipient/edit");
    }

    private void handleFilter(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String filterType = req.getParameter("filterType");

        if (filterType == null || filterType.isEmpty()) {
            // if null list all recipient
            return;
        }

        // switch case
    }

    // --------- list methods ---------
    private void listAllRecipients(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<RecipientDTO> r = controller.getAllRecipients();
        req.setAttribute("recipients", r);
        Router.goTo(res, req, "/recipient/list");
    }

    private void listRecipientByBloodType(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String bloodType = req.getParameter("bloodType");
        List<RecipientDTO> r = controller.getRecipientsByBloodType(bloodType);
        req.setAttribute("recipients", r);
        Router.goTo(res, req, "/recipient/list");
    }

    private void listRecipientByState(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String state = req.getParameter("state");
        List<RecipientDTO> r = controller.getRecipientsByState(state);
        req.setAttribute("recipients", r);
        Router.goTo(res, req, "/recipient/list");
    }

    private void listRecipientBySituation(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String situation = req.getParameter("situation");
        List<RecipientDTO> r = controller.getRecipientsBySituation(situation);
        req.setAttribute("recipients", r);
        Router.goTo(res, req, "/recipient/list");
    }

    private void listPriorityRecipients(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<RecipientDTO> r = controller.getRecipientsByPriority();
        req.setAttribute("recipients", r);
        Router.goTo(res, req, "/recipient/list");
    }
}
