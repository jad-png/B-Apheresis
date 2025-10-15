package servlets;

import config.DIContainer;
import controller.DonorController;
import dto.DonorDTO;
import utils.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    // --------- Handlets ---------
    private void handleCreate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        DonorDTO dto = new
    }
}
