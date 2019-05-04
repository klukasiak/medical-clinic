package tk.kdev.aplikacjebazodanowejavafxspring.exception;

public class UserNotFoundException extends Exception{
    @Override
    public String toString(){
        return "Nie znaleziono uzytkownika";
    }
}
