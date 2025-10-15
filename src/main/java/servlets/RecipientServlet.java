package servlets;

import config.DIContainer;
import controller.RecipientController;
import dto.DonorDTO;
import dto.RecipientDTO;
import entity.Recipient;
import entity.enums.BloodType;
import entity.enums.Gender;
import entity.enums.Situation;
import entity.enums.State;
import mapper.RecipientMapper;
import utils.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
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
        String action = req.getParameter("action");

        switch (action) {
            case "create":
                showCreateForm(req, res);
                break;
            case "edit":
                showEditForm(req, res);
                break;
            case "delete":
                // TODO: implement handle delete
                break;
            case "filter":
                handleFilter(req, res);
                break;
            case "list":
                listAllRecipients(req, res);
                break;
            default:
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
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
            listAllRecipients(req, res);
            return;
        }

        switch (filterType) {
            case "bloodType":
                listRecipientsByBloodType(req, res);
                break;
            case "state":
                listRecipientsByState(req, res);
                break;
            case "situation":
                listRecipientsBySituation(req, res);
                break;
            case "waiting":
                listWaitingRecipients(req, res);
                break;
            case "priority":
                listPriorityRecipients(req, res);
                break;
            default:
                listAllRecipients(req, res);
                break;
        }

        // switch case
    }

    // --------- list methods ---------
    private void listAllRecipients(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<RecipientDTO> r = controller.getAllRecipients();
        req.setAttribute("recipients", r);
        Router.goTo(res, req, "/recipient/list");
    }

    private void listRecipientsByBloodType(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String bloodType = req.getParameter("bloodType");
        List<RecipientDTO> r = controller.getRecipientsByBloodType(bloodType);
        req.setAttribute("recipients", r);
        Router.goTo(res, req, "/recipient/list");
    }

    private void listRecipientsByState(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String state = req.getParameter("state");
        List<RecipientDTO> r = controller.getRecipientsByState(state);
        req.setAttribute("recipients", r);
        Router.goTo(res, req, "/recipient/list");
    }

    private void listRecipientsBySituation(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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

    private void listWaitingRecipients(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<RecipientDTO> r = controller.getWaitingRecipients();
        req.setAttribute("recipients", r);
        Router.goTo(res, req, "/recipient/list");
    }


    // --------- helpers ---------
    private RecipientDTO extractRecipientFromRequest(HttpServletRequest req) {
        RecipientDTO dto = new RecipientDTO();

        if (req.getParameter("id") != null && !req.getParameter("id").isEmpty()) {
            dto.setId(Long.parseLong(req.getParameter("id")));
        }

        dto.setFirstName(req.getParameter("firstName"));
        dto.setLastName(req.getParameter("lastName"));
        dto.setCin(req.getParameter("cin"));
        dto.setBloodType(BloodType.valueOf(req.getParameter("bloodType")));
        dto.setGender(Gender.valueOf(req.getParameter("gender")));
        dto.setBirthday(LocalDate.parse(req.getParameter("birthday")));
        dto.setRequiredBags(Integer.parseInt(req.getParameter("requiredBags")));
        dto.setCurrentBags(Integer.parseInt(req.getParameter("currentBags")));
        dto.setState(State.valueOf(req.getParameter("state")));
        dto.setSituation(Situation.valueOf(req.getParameter("situation")));

        return dto;
    }
}
