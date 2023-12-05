public class Formatter {
    static String formatter (double rubles) {
        return String.format ("%.2f", rubles) + " " + getRubleAddition ((int) rubles);
    }

    // https://ru.stackoverflow.com/questions/664884/%D0%9A%D0%B0%D0%BA-%D0%B4%D0%BE%D0%B1%D0%B0%D0%B2%D0%B8%D1%82%D1%8C-%D0%BA-%D1%87%D0%B8%D1%81%D0%BB%D0%B0%D0%BC-21-22-23-24-%D1%81%D0%BB%D0%BE%D0%B2%D0%BE-%D1%80%D1%83%D0%B1%D0%BB%D0%B5%D0%B9-%D0%B2-%D0%BD%D1%83%D0%B6%D0%BD%D0%BE%D0%BC-%D0%BF%D0%B0%D0%B4%D0%B5%D0%B6%D0%B5java
    // "Как добавить к числам (21,22,23,24) слово "рублей" в нужном падеже"
    static String getRubleAddition (int num) {
        int preLastDigit = num % 100 / 10;
        if (preLastDigit == 1) {
            return "рублей";
        }
        return switch (num % 10) {
            case 1 -> "рубль";
            case 2, 3, 4 -> "рубля";
            default -> "рублей";
        };
    }
}
