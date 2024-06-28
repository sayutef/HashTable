package models;

public class FuncionHash2 {

    public int hashDivision(String key) {
        int hashValue = 0;
        for (char c : key.toCharArray()) {
            int division = c/3;
            hashValue += division;

        }
        return hashValue ;
    }
}
