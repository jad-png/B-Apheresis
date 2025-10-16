// Donor Edit Page JavaScript
const donorEdit = {
    init: function() {
        this.attachEventListeners();
        this.setMaxDate();
        this.calculateAndDisplayAge();
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

        // Age calculation when birthday changes
        const birthdayInput = document.getElementById('birthday');
        if (birthdayInput) {
            birthdayInput.addEventListener('change', this.calculateAndDisplayAge.bind(this));
        }
    },

    setMaxDate: function() {
        const birthdayInput = document.getElementById('birthday');
        if (birthdayInput && !birthdayInput.getAttribute('max')) {
            const maxDate = new Date();
            maxDate.setFullYear(maxDate.getFullYear() - 18);
            birthdayInput.setAttribute('max', maxDate.toISOString().split('T')[0]);
        }
    },

    calculateAndDisplayAge: function() {
        const birthdayInput = document.getElementById('birthday');
        if (birthdayInput && birthdayInput.value) {
            const age = this.calculateAge(birthdayInput.value);
            this.showAgePreview(age);
        }
    },

    showAgePreview: function(age) {
        // Remove existing age preview
        let agePreview = document.getElementById('age-preview');
        if (!agePreview) {
            agePreview = document.createElement('div');
            agePreview.id = 'age-preview';
            agePreview.className = 'text-xs mt-1 text-blue-600';
            document.getElementById('birthday').parentNode.appendChild(agePreview);
        }

        if (age < 18) {
            agePreview.textContent = `Âge: ${age} ans (Trop jeune - minimum 18 ans requis)`;
            agePreview.className = 'text-xs mt-1 text-red-600';
        } else if (age > 65) {
            agePreview.textContent = `Âge: ${age} ans (Âge avancé - peut affecter l'éligibilité)`;
            agePreview.className = 'text-xs mt-1 text-yellow-600';
        } else {
            agePreview.textContent = `Âge: ${age} ans (Âge approprié)`;
            agePreview.className = 'text-xs mt-1 text-green-600';
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
            const age = this.calculateAge(birthday);

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

        // Show confirmation for status changes
        const originalEligible = donor != null && donor.getEligible() != null ? donor.getEligible() : "false";
        const currentWeight = document.getElementById('weight').value;
        const currentMedicalCondition = document.getElementById('medicalCondition').value;

        const willBeEligible = currentWeight >= 50 && currentMedicalCondition === 'HEALTHY';

        if (originalEligible !== willBeEligible) {
            const message = willBeEligible ?
                'Ces modifications rendront ce donneur éligible au don. Continuer ?' :
                'Ces modifications rendront ce donneur non éligible au don. Continuer ?';

            if (!confirm(message)) {
                e.preventDefault();
                return false;
            }
        }

        return true;
    },

    validateWeight: function(e) {
        const weight = e.target.value;

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
    donorEdit.init();
});