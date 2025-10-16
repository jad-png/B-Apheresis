// Donor Create Page JavaScript
const donorCreate = {
    init: function() {
        this.attachEventListeners();
        this.setMaxDate();
    },

    attachEventListeners: function() {
        const form = document.getElementById('donorForm');
        if (form) {
            form.addEventListener('submit', this.validateForm.bind(this));
        }

        // Real-time weight validation
        const weightInput = document.getElementById('weight');
        if (weightInput) {
            weightInput.addEventListener('input', this.validateWeight.bind(this));
        }

        // Real-time CIN validation
        const cinInput = document.getElementById('cin');
        if (cinInput) {
            cinInput.addEventListener('input', this.validateCIN.bind(this));
        }
    },

    setMaxDate: function() {
        // Set max date to 18 years ago (already done in JSP, but double-check)
        const birthdayInput = document.getElementById('birthday');
        if (birthdayInput && !birthdayInput.getAttribute('max')) {
            const maxDate = new Date();
            maxDate.setFullYear(maxDate.getFullYear() - 18);
            birthdayInput.setAttribute('max', maxDate.toISOString().split('T')[0]);
        }
    },

    validateForm: function(e) {
        const weight = document.getElementById('weight').value;
        const medicalCondition = document.getElementById('medicalCondition').value;

        let isValid = true;
        let errorMessage = '';

        // Weight validation
        if (weight < 50) {
            isValid = false;
            errorMessage = 'Le poids minimum requis est de 50 kg pour être éligible au don.';
        }

        // Medical condition validation
        if (medicalCondition !== 'HEALTHY') {
            if (!confirm('Ce donneur ne sera pas éligible au don avec cette condition médicale. Voulez-vous continuer ?')) {
                isValid = false;
            }
        }

        // Age validation
        const birthday = document.getElementById('birthday').value;
        if (birthday) {
            const birthDate = new Date(birthday);
            const today = new Date();
            const age = today.getFullYear() - birthDate.getFullYear();
            const monthDiff = today.getMonth() - birthDate.getMonth();

            if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
                age--;
            }

            if (age < 18) {
                isValid = false;
                errorMessage = 'Le donneur doit avoir au moins 18 ans.';
            } else if (age > 65) {
                if (!confirm('Ce donneur a plus de 65 ans et ne sera pas éligible. Voulez-vous continuer ?')) {
                    isValid = false;
                }
            }
        }

        if (!isValid) {
            e.preventDefault();
            if (errorMessage) {
                alert(errorMessage);
            }
            return false;
        }

        return true;
    },

    validateWeight: function(e) {
        const weight = e.target.value;
        const weightHelp = e.target.parentElement.querySelector('.weight-help');

        if (weight < 50) {
            this.showValidationMessage(e.target, 'Le poids minimum est de 50 kg pour être éligible', 'error');
        } else {
            this.clearValidationMessage(e.target);
        }
    },

    validateCIN: function(e) {
        const cin = e.target.value;
        const cinRegex = /^[A-Za-z0-9]{6,12}$/;

        if (cin && !cinRegex.test(cin)) {
            this.showValidationMessage(e.target, 'Le CIN doit contenir 6 à 12 caractères alphanumériques', 'error');
        } else {
            this.clearValidationMessage(e.target);
        }
    },

    showValidationMessage: function(input, message, type) {
        this.clearValidationMessage(input);

        const messageDiv = document.createElement('div');
        messageDiv.className = `text-xs mt-1 ${type === 'error' ? 'text-red-600' : 'text-green-600'}`;
        messageDiv.textContent = message;
        messageDiv.id = `${input.id}-validation`;

        input.parentNode.appendChild(messageDiv);
        input.classList.add('border-red-300');
    },

    clearValidationMessage: function(input) {
        const existingMessage = document.getElementById(`${input.id}-validation`);
        if (existingMessage) {
            existingMessage.remove();
        }
        input.classList.remove('border-red-300');
    },

    // Helper to calculate age from birthday
    calculateAge: function(birthday) {
        const birthDate = new Date(birthday);
        const today = new Date();
        let age = today.getFullYear() - birthDate.getFullYear();
        const monthDiff = today.getMonth() - birthDate.getMonth();

        if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
            age--;
        }

        return age;
    }
};

// Initialize when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
    donorCreate.init();
});