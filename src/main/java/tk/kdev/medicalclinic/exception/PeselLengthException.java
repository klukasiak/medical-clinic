package tk.kdev.medicalclinic.exception;

public class PeselLengthException extends Exception{
    @Override
    public String toString() {
        return "Pesel length not equals to 11";
    }
}
