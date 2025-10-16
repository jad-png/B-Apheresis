<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.DonorDTO" %>
<%@ page import="entity.enums.BloodType" %>
<%@ page import="entity.enums.Gender" %>
<%@ page import="entity.enums.MedicalCondition" %>
<%
    // Set page attributes
    request.setAttribute("pageTitle", "Modifier Donneur");
    request.setAttribute("pageSubtitle", "Modifier les informations d'un donneur");

    // Action button - Back to list
    String actionButton = "<a href=\"" + request.getContextPath() + "/donors?action=list\" " +
            "class=\"bg-gray-600 hover:bg-gray-700 text-white px-6 py-3 rounded-lg font-semibold transition-colors duration-200 flex items-center\">" +
            "<i class=\"fas fa-arrow-left mr-2\"></i>Retour à la liste</a>";
    request.setAttribute("actionButton", actionButton);

    // Get donor from request
    DonorDTO donor = (DonorDTO) request.getAttribute("donor");

    // Build content
    StringBuilder content = new StringBuilder();

    if (donor == null) {
        content.append("<div class=\"bg-yellow-50 border border-yellow-200 rounded-lg p-6 text-center\">");
        content.append("<i class=\"fas fa-exclamation-triangle text-yellow-500 text-2xl mb-4\"></i>");
        content.append("<h3 class=\"text-lg font-semibold text-yellow-800 mb-2\">Donneur non trouvé</h3>");
        content.append("<p class=\"text-yellow-700\">Le donneur que vous essayez de modifier n'existe pas.</p>");
        content.append("</div>");
    } else {
        content.append("<div class=\"bg-white rounded-lg shadow-sm border overflow-hidden\">");

        // Form Header
        content.append("<div class=\"bg-blue-50 border-b border-blue-200 px-6 py-4\">");
        content.append("<h3 class=\"text-lg font-semibold text-blue-800 flex items-center\">");
        content.append("<i class=\"fas fa-edit mr-2\"></i>");
        content.append("Modification du donneur #").append(donor.getId());
        content.append("</h3>");
        content.append("</div>");

        // Form
        content.append("<form action=\"").append(request.getContextPath()).append("/donors\" method=\"post\" id=\"donorForm\" class=\"p-6\">");
        content.append("<input type=\"hidden\" name=\"action\" value=\"update\">");
        content.append("<input type=\"hidden\" name=\"id\" value=\"").append(donor.getId()).append("\">");

        content.append("<div class=\"grid grid-cols-1 md:grid-cols-2 gap-6\">");

        // Personal Information Column
        content.append("<div>");
        content.append("<h4 class=\"text-md font-semibold text-gray-900 mb-4 pb-2 border-b\">Informations Personnelles</h4>");

        // First Name
        content.append("<div class=\"mb-4\">");
        content.append("<label for=\"firstName\" class=\"block text-sm font-medium text-gray-700 mb-1\">Prénom *</label>");
        content.append("<input type=\"text\" id=\"firstName\" name=\"firstName\" required ");
        content.append("class=\"w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500\" ");
        content.append("value=\"").append(donor.getFirstName() != null ? donor.getFirstName() : "").append("\" ");
        content.append("placeholder=\"Entrez le prénom\">");
        content.append("</div>");

        // Last Name
        content.append("<div class=\"mb-4\">");
        content.append("<label for=\"lastName\" class=\"block text-sm font-medium text-gray-700 mb-1\">Nom *</label>");
        content.append("<input type=\"text\" id=\"lastName\" name=\"lastName\" required ");
        content.append("class=\"w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500\" ");
        content.append("value=\"").append(donor.getLastName() != null ? donor.getLastName() : "").append("\" ");
        content.append("placeholder=\"Entrez le nom\">");
        content.append("</div>");

        // CIN
        content.append("<div class=\"mb-4\">");
        content.append("<label for=\"cin\" class=\"block text-sm font-medium text-gray-700 mb-1\">CIN *</label>");
        content.append("<input type=\"text\" id=\"cin\" name=\"cin\" required ");
        content.append("class=\"w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500\" ");
        content.append("value=\"").append(donor.getCin() != null ? donor.getCin() : "").append("\" ");
        content.append("placeholder=\"Ex: AB123456\" pattern=\"[A-Za-z0-9]{6,12}\">");
        content.append("<p class=\"text-xs text-gray-500 mt-1\">6-12 caractères alphanumériques</p>");
        content.append("</div>");

        // Birthday
        content.append("<div class=\"mb-4\">");
        content.append("<label for=\"birthday\" class=\"block text-sm font-medium text-gray-700 mb-1\">Date de Naissance *</label>");
        content.append("<input type=\"date\" id=\"birthday\" name=\"birthday\" required max=\"");

        // Calculate max date (18 years ago)
        java.time.LocalDate maxDate = java.time.LocalDate.now().minusYears(18);
        content.append(maxDate);

        content.append("\" ");
        content.append("class=\"w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500\" ");
        content.append("value=\"").append(donor.getBirthday() != null ? donor.getBirthday() : "").append("\">");
        content.append("<p class=\"text-xs text-gray-500 mt-1\">Âge minimum: 18 ans</p>");
        content.append("</div>");

        content.append("</div>"); // End personal info column

        // Medical Information Column
        content.append("<div>");
        content.append("<h4 class=\"text-md font-semibold text-gray-900 mb-4 pb-2 border-b\">Informations Médicales</h4>");

        // Gender
        content.append("<div class=\"mb-4\">");
        content.append("<label class=\"block text-sm font-medium text-gray-700 mb-2\">Genre *</label>");
        content.append("<div class=\"flex space-x-4\">");

        String maleChecked = donor.getGender() != null && donor.getGender().toString().equals("MALE") ? "checked" : "";
        String femaleChecked = donor.getGender() != null && donor.getGender().toString().equals("FEMALE") ? "checked" : "";

        content.append("<label class=\"inline-flex items-center\">");
        content.append("<input type=\"radio\" name=\"gender\" value=\"MALE\" required ").append(maleChecked).append(" class=\"form-radio text-blue-600 focus:ring-blue-500\">");
        content.append("<span class=\"ml-2 text-sm text-gray-700\">Masculin</span>");
        content.append("</label>");
        content.append("<label class=\"inline-flex items-center\">");
        content.append("<input type=\"radio\" name=\"gender\" value=\"FEMALE\" ").append(femaleChecked).append(" class=\"form-radio text-blue-600 focus:ring-blue-500\">");
        content.append("<span class=\"ml-2 text-sm text-gray-700\">Féminin</span>");
        content.append("</label>");
        content.append("</div>");
        content.append("</div>");

        // Blood Type
        content.append("<div class=\"mb-4\">");
        content.append("<label for=\"bloodType\" class=\"block text-sm font-medium text-gray-700 mb-1\">Groupe Sanguin *</label>");
        content.append("<select id=\"bloodType\" name=\"bloodType\" required ");
        content.append("class=\"w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500\">");
        content.append("<option value=\"\">Sélectionnez un groupe sanguin</option>");

        for (BloodType bloodType : BloodType.values()) {
            String displayName = "";
            switch(bloodType) {
                case O_POS: displayName = "O+"; break;
                case O_NEG: displayName = "O-"; break;
                case A_POS: displayName = "A+"; break;
                case A_NEG: displayName = "A-"; break;
                case B_POS: displayName = "B+"; break;
                case B_NEG: displayName = "B-"; break;
                case AB_POS: displayName = "AB+"; break;
                case AB_NEG: displayName = "AB-"; break;
            }
            String selected = donor.getBloodType() != null && donor.getBloodType().equals(bloodType) ? "selected" : "";
            content.append("<option value=\"").append(bloodType).append("\" ").append(selected).append(">").append(displayName).append("</option>");
        }

        content.append("</select>");
        content.append("</div>");

        // Weight
        content.append("<div class=\"mb-4\">");
        content.append("<label for=\"weight\" class=\"block text-sm font-medium text-gray-700 mb-1\">Poids (kg) *</label>");
        content.append("<input type=\"number\" id=\"weight\" name=\"weight\" required min=\"40\" max=\"200\" step=\"0.1\" ");
        content.append("class=\"w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500\" ");
        content.append("value=\"").append(donor.getWeight() != null ? donor.getWeight() : "").append("\" ");
        content.append("placeholder=\"Ex: 65.5\">");
        content.append("<p class=\"text-xs text-gray-500 mt-1\">Poids minimum: 50 kg pour être éligible</p>");
        content.append("</div>");

        // Medical Condition
        content.append("<div class=\"mb-4\">");
        content.append("<label for=\"medicalCondition\" class=\"block text-sm font-medium text-gray-700 mb-1\">Condition Médicale *</label>");
        content.append("<select id=\"medicalCondition\" name=\"medicalCondition\" required ");
        content.append("class=\"w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500\">");
        content.append("<option value=\"\">Sélectionnez une condition</option>");

        for (MedicalCondition condition : MedicalCondition.values()) {
            String displayName = "";
            switch(condition) {
                case HEALTHY: displayName = "En bonne santé"; break;
                case HEPATITIS_B: displayName = "Hépatite B"; break;
                case HEPATITIS_C: displayName = "Hépatite C"; break;
                case HIV: displayName = "VIH"; break;
                case INSULIN_DEPENDENT_DIABETES: displayName = "Diabète insulinodépendant"; break;
                case PREGNANT: displayName = "Enceinte"; break;
                case BREASTFEEDING: displayName = "Allaitement"; break;
                case OTHER: displayName = "Autre condition"; break;
            }
            String selected = donor.getMedicalCondition() != null && donor.getMedicalCondition().equals(condition) ? "selected" : "";
            content.append("<option value=\"").append(condition).append("\" ").append(selected).append(">").append(displayName).append("</option>");
        }

        content.append("</select>");
        content.append("<p class=\"text-xs text-gray-500 mt-1\">Seule \"En bonne santé\" rend éligible</p>");
        content.append("</div>");

        content.append("</div>"); // End medical info column
        content.append("</div>"); // End grid

        // Current Status Information
        content.append("<div class=\"bg-gray-50 rounded-lg p-4 mt-6\">");
        content.append("<h4 class=\"text-md font-semibold text-gray-900 mb-3\">Statut Actuel</h4>");
        content.append("<div class=\"grid grid-cols-2 md:grid-cols-4 gap-4 text-sm\">");

        // Age
        content.append("<div class=\"text-center\">");
        content.append("<div class=\"text-gray-600\">Âge</div>");
        content.append("<div class=\"font-semibold text-gray-900\">").append(donor.getAge() != null ? donor.getAge() + " ans" : "N/A").append("</div>");
        content.append("</div>");

        // Eligible
        content.append("<div class=\"text-center\">");
        content.append("<div class=\"text-gray-600\">Éligible</div>");
        content.append("<div class=\"font-semibold\">");
        if (donor.getEligible() != null && donor.getEligible()) {
            content.append("<span class=\"text-green-600\"><i class=\"fas fa-check-circle mr-1\"></i>Oui</span>");
        } else {
            content.append("<span class=\"text-red-600\"><i class=\"fas fa-times-circle mr-1\"></i>Non</span>");
        }
        content.append("</div>");
        content.append("</div>");

        // Can Donate
        content.append("<div class=\"text-center\">");
        content.append("<div class=\"text-gray-600\">Peut Donner</div>");
        content.append("<div class=\"font-semibold\">");
        if (donor.getCanDonate() != null && donor.getCanDonate()) {
            content.append("<span class=\"text-green-600\"><i class=\"fas fa-check-circle mr-1\"></i>Oui</span>");
        } else {
            content.append("<span class=\"text-red-600\"><i class=\"fas fa-times-circle mr-1\"></i>Non</span>");
        }
        content.append("</div>");
        content.append("</div>");

        // Status
        content.append("<div class=\"text-center\">");
        content.append("<div class=\"text-gray-600\">Statut</div>");
        content.append("<div class=\"font-semibold\">");
        if (donor.getStatus() != null) {
            String statusColor = "bg-gray-100 text-gray-800";
            String statusText = "INCONNU";
            switch(donor.getStatus().toString()) {
                case "AVAILABLE":
                    statusColor = "bg-green-100 text-green-800";
                    statusText = "DISPONIBLE";
                    break;
                case "UNAVAILABLE":
                    statusColor = "bg-yellow-100 text-yellow-800";
                    statusText = "INDISPONIBLE";
                    break;
                case "INELIGIBLE":
                    statusColor = "bg-red-100 text-red-800";
                    statusText = "NON ÉLIGIBLE";
                    break;
            }
            content.append("<span class=\"inline-flex items-center px-2 py-1 rounded-full text-xs font-medium ").append(statusColor).append("\">").append(statusText).append("</span>");
        } else {
            content.append("<span class=\"text-gray-500\">N/A</span>");
        }
        content.append("</div>");
        content.append("</div>");

        content.append("</div>"); // End status grid
        content.append("</div>"); // End status section

        // Form Actions
        content.append("<div class=\"flex justify-end space-x-4 mt-8 pt-6 border-t border-gray-200\">");
        content.append("<a href=\"").append(request.getContextPath()).append("/donors?action=list\" ");
        content.append("class=\"bg-gray-300 hover:bg-gray-400 text-gray-700 px-6 py-2 rounded-lg font-semibold transition-colors duration-200\">");
        content.append("Annuler");
        content.append("</a>");
        content.append("<button type=\"submit\" ");
        content.append("class=\"bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded-lg font-semibold transition-colors duration-200 flex items-center\">");
        content.append("<i class=\"fas fa-save mr-2\"></i>");
        content.append("Mettre à jour");
        content.append("</button>");
        content.append("</div>");

        content.append("</form>");
        content.append("</div>"); // End main card
    }

    // Set page-specific script
    request.setAttribute("pageScript", "donorEdit.init();");

    request.setAttribute("content", content.toString());
%>

<jsp:include page="../layouts/main.jsp"/>