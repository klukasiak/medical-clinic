package tk.kdev.medicalclinic.exception;

public class NameException extends Exception {
    @Override
    public String toString(){
        return "Invalid first or last name (only a-z letters)";
    }
}
