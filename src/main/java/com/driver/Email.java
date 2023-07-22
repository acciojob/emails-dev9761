package com.driver;

class Email {
        private String emailId;
        private String password;

        public Email(String emailId) {
                this.emailId = emailId;
                this.password = "Accio@123";
        }

        public boolean changePassword(String oldPassword, String newPassword) {
                if (oldPassword.equals(password) && isValidPassword(newPassword)) {
                        password = newPassword;
                        return true;
                }
                return false;
        }

        private boolean isValidPassword(String password) {
                return password.length() >= 8 && password.matches(".*[A-Z].*")
                        && password.matches(".*[a-z].*") && password.matches(".*\\d.*")
                        && password.matches(".*[^a-zA-Z\\d].*");
        }
}
