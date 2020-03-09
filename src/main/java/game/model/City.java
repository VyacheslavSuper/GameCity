package game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private String name;

    public static City createCity(String stringCity) {
        return new City(stringCity.trim().toLowerCase());
    }

    public String getFirstSymbol() {
        return String.valueOf(name.charAt(0));
    }

    public String getLastSymbol() {
        int i = 1;
        do {
            String symbol = String.valueOf(name.charAt(name.length() - i));
            if (checkSymbol(symbol)) {
                return symbol;
            } else {
                i++;
            }
        } while (true);
    }

    private boolean checkSymbol(String symbol) {
        if (symbol == null) {
            return false;
        }
        switch (symbol) {
            case "ь":
            case "ъ":
            case "й":
            case "ы":
                return false;
            default:
                return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(name);
        builder.setCharAt(0, Character.toUpperCase(name.charAt(0)));
        return builder.toString();
    }
}
