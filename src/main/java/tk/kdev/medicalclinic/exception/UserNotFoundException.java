package tk.kdev.medicalclinic.exception;

public class UserNotFoundException extends Exception {
    @Override
    public String toString() {
        return "Nie znaleziono uzytkownika";
    }
}
