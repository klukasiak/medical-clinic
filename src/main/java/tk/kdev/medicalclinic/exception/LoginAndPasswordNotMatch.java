package tk.kdev.medicalclinic.exception;

public class LoginAndPasswordNotMatch extends Exception {
    @Override
    public String toString() {
        return "Wrong password";
    }
}
