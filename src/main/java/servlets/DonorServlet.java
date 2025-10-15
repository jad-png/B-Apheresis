package servlets;

import config.DIContainer;
import controller.DonorController;
import dto.DonorDTO;
import entity.enums.BloodType;
import entity.enums.Gender;
import entity.enums.MedicalCondition;
import utils.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DonorServlet extends HttpServlet {
    private DonorController controller;

    @Override
    public void init() throws ServletException {
        this.controller = DIContainer.getInstance().getBean(DonorController.class);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

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

    private void listDonors(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<DonorDTO> donors = controller.getAllDonors();
        req.setAttribute("donors", donors);
        Router.goTo(res, req, "/donor/list");
    }

    private void handleFilter(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String bloodType = req.getParameter("bloodType");
        List<DonorDTO> filtered = controller.getDonorsByBloodType(bloodType);
        req.setAttribute("filtered", filtered);
        Router.goTo(res, req, "/donor/list");
    }

    // --------- Handlets ---------
    private void handleCreate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

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
