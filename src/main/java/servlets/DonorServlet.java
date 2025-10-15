package servlets;

import config.DIContainer;
import controller.DonorController;
import dto.DonorDTO;
import entity.Donor;
import entity.enums.BloodType;
import entity.enums.Gender;
import entity.enums.MedicalCondition;
import mapper.DonorMapper;
import utils.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DonorServlet extends HttpServlet {
    private DonorController controller;
    private DonorMapper mapper;

    @Override
    public void init() throws ServletException {
        this.controller = DIContainer.getInstance().getBean(DonorController.class);
        this.mapper = DIContainer.getInstance().getBean(DonorMapper.class);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = Optional.ofNullable(req.getParameter("action")).orElse("");

        switch (action) {
            case "create":
                showCreateForm(req, res);
                break;
            case "edit":
                showEditForm(req, res);
                break;
            case "delete":
                handleDelete(req, res);
                break;
            case "filter":
                handleFilter(req, res);
                break;
            case "list":
                listAllDonors(req, res);
                break;
            default:
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
                break;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = Optional.ofNullable(req.getParameter("action")).orElse("");

        switch (action) {
            case "create":
                handleCreate(req, res);
                break;
            case "update":
                handleUpdate(req, res);
                break;
            case "delete":
                handleDelete(req, res);
                break;
            default:
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                break;
        }
    }

    // --------- Views ---------
    private void showCreateForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Router.goTo(res, req, "/donor/create");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Optional<DonorDTO> donorOpt = controller.getDonor(id);
        donorOpt.ifPresent(d -> req.setAttribute("donor", d));
        Router.goTo(res, req, "/donor/edit");
    }

    private void handleFilter(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String filterType = req.getParameter("filterType");

        if (filterType == null || filterType.isEmpty()) {
            listAllDonors(req, res);
            return;
        }

        switch (filterType) {
            case "bloodType":
                listDonorsByBloodType(req, res);
                break;
            case "available":
                listAvailableDonors(req, res);
                break;
            case "eligible":
                listEligibleDonors(req, res);
                break;
            default:
                listAllDonors(req, res);
                break;
        }
    }

    // ------- list methods --------
    private void listAllDonors(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            List<DonorDTO> donors = controller.getAllDonors();
            req.setAttribute("donors", donors);
        Router.goTo(res, req, "/donor/list");
    }

    private void listDonorsByBloodType(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String bloodType = req.getParameter("bloodType");
            List<DonorDTO> filtered = controller.getDonorsByBloodType(bloodType);
            req.setAttribute("donors", filtered);
        Router.goTo(res, req, "/donor/list");
    }

    private void listAvailableDonors(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            List<DonorDTO> availableDonors = controller.getAvailableDonors();
            req.setAttribute("donors", availableDonors);
        Router.goTo(res, req, "/donor/list");
    }

    private void listEligibleDonors(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            Optional<DonorDTO> eligibleDonorOpt = controller.getEligibleDonors();
            if (eligibleDonorOpt.isPresent()) {
                req.setAttribute("donors", Collections.singletonList(eligibleDonorOpt.get()));
            } else {
                req.setAttribute("donors", Collections.emptyList());
            }
        Router.goTo(res, req, "/donor/list");
    }

    // --------- Handlets ---------
    private void handleCreate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        DonorDTO dto = extractDonorFromRequest(req);

        Donor d = mapper.toEntity(dto);

        dto.setEligible(controller.isEligible(d));
        dto.setCanDonate(controller.canDonate(d));

        controller.createDonor(dto);
        Router.goTo(res, req, "/donors?action=list");
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        DonorDTO dto = extractDonorFromRequest(req);

        Donor d = mapper.toEntity(dto);

        dto.setEligible(controller.isEligible(d));
        dto.setCanDonate(controller.canDonate(d));

        controller.updateDonor(dto);
        Router.redirect(res, req, "donors?action=list");
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        controller.deleteDonor(id);
        Router.redirect(res, req, "/donors?action=list");
    }

    // --------- Helpers ---------
    private DonorDTO extractDonorFromRequest(HttpServletRequest req) throws ServletException, IOException {
        DonorDTO dto = new DonorDTO();

        if (req.getParameter("id") != null) {
            dto.setId(Long.parseLong(req.getParameter("id")));
        }

        dto.setFirstName(req.getParameter("firstName"));
        dto.setLastName(req.getParameter("lastName"));
        dto.setCin(req.getParameter("cin"));

        dto.setBirthday(LocalDate.parse(req.getParameter("birthday")));
        dto.setBloodType(BloodType.valueOf(req.getParameter("bloodType")));
        dto.setGender(Gender.valueOf(req.getParameter("gender")));
        dto.setWeight(Double.parseDouble(req.getParameter("weight")));
        dto.setMedicalCondition(MedicalCondition.valueOf(req.getParameter("medicalCondition")));

        return dto;
    }
}
