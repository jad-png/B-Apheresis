package servlets;

import config.DIContainer;
import controller.DonationController;
import controller.DonorController;
import controller.RecipientController;
import dto.DonationDTO;
import dto.DonorDTO;
import dto.RecipientDTO;
import entity.Donor;
import entity.Recipient;
import mapper.DonationMapper;
import service.impl.MatchingService;
import service.interfaces.DonationService;
import utils.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DonationServlet extends HttpServlet {
    private DonationController donationCon;
    private DonorController donorCon;
    private RecipientController recipientCon;
    private MatchingService matchingService;
    private DonationMapper mapper;

    public void init() throws ServletException {
        this.donationCon = DIContainer.getInstance().getBean(DonationController.class);
        this.donorCon = DIContainer.getInstance().getBean(DonorController.class);
        this.recipientCon = DIContainer.getInstance().getBean(RecipientController.class);
        this.matchingService = DIContainer.getInstance().getBean(MatchingService.class);
        this.mapper = DIContainer.getInstance().getBean(DonationMapper.class);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = Optional.ofNullable(req.getParameter("action")).orElse("");

        switch (action) {
            case "list":
                listDonations(req, res);
                break;
            case "match":
                showMatchPage(req, res);
                break;
            case "delete":
                handleDelete(req, res);
                break;
            default:
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = Optional.ofNullable(req.getParameter("action")).orElse("");

        switch (action) {
            case "create":
                handleCreate(req, res);
                break;
            default:
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    // ------- View Handlers ---------
    private void listDonations(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<DonationDTO> d = donationCon.getAllDonations();
        req.setAttribute("donations", d);
        Router.goTo(res, req, "/donations/list");
    }

    private void showMatchPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String donorIdParam = req.getParameter("donorId");

        if (donorIdParam == null || donorIdParam.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "donorId is required");
            return;
        }
            Long donorId = Long.parseLong(donorIdParam);
            Optional<DonorDTO> donorOpt = donorCon.getDonor(donorId);

            if (!donorOpt.isPresent()) {
                res.sendError(HttpServletResponse.SC_NOT_FOUND, "Donor not found with ID: " + donorId);
                return;
            }

            DonorDTO donor = donorOpt.get();
            List<Recipient> compatibleRecipients = matchingService.findCompatibleRecipients(donor.getBloodType());

            req.setAttribute("donor", donor);
            req.setAttribute("compatibleRecipients", compatibleRecipients);
            Router.goTo(res, req, "/donations/match");
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "donorId is required");
            Router.redirect(res, req, "/donations?action=list");
        }

        Long id = Long.parseLong(idParam);
        donationCon.deleteDonation(id);
        Router.redirect(res, req, "/donations?action=list");
    }

    // --------- Form Handlers ---------

    private void handleCreate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String donorIdParam = req.getParameter("donorId");
        String recipientIdParam = req.getParameter("recipientId");

        if (donorIdParam == null || recipientIdParam == null
            || recipientIdParam.isEmpty() || donorIdParam.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing donorId or recipientId");
            return;
        }

        Long donorId = Long.parseLong(donorIdParam);
        Long recipientId = Long.parseLong(recipientIdParam);

        DonationDTO dto = new DonationDTO();
        dto.setDonorId(donorId);
        dto.setRecipientId(recipientId);

        donationCon.createDonation(dto);
        Router.redirect(res, req, "/donations?action=list");
    }
}

