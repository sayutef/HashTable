package models;

public class FuncionHash1 {

    public  int hashAscii(String input) {
        int hash = 0;
        for (int i = 0; i < input.length(); i++) {
            int valorAscii=  input.charAt(i);
            int multiplicado =valorAscii *3;
            hash +=multiplicado;

        }
        return hash;
    }

}
