package tk.kdev.aplikacjebazodanowejavafxspring.exception;

public class LoginAndPasswordNotMatch extends Exception{
    @Override
    public String toString(){
        return "Zle haslo";
    }
}
