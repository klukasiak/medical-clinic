package tk.kdev.medicalclinic.exception;

public class PhoneNumberException extends Exception{
    @Override
    public String toString(){
        return "Phone number is not valid";
    }
}
