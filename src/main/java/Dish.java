//import static java.lang.Math.round; - округление перенесено в main

// Рефакторинг после замечания:
// Старайся называть классы и переменные на английском языке, а не просто транслитом
// Tovar -> Dish
public class Dish {
    String name;
    double price;
    Dish next;

    Dish(String _name, double _price) {
        name = _name;
        price = _price;
        next = null;
    }
    void attach (Dish _next) {
        Dish t = this;

        while (t.next != null) t = t.next;
        t.next = _next;
    }

    public void print () {
        print (0);
    }
    public void print (int lineNumber) {
        //String _name = String.padLeft (name, 50);
        if (lineNumber < 1) {
            System.out.printf("%30s, %19s\n", name, Formatter.formatter(price));
        } else {
            System.out.printf("%d) %20s, %19s\n", lineNumber, name, Formatter.formatter(price));
        }
    }
    public void printAll () {
        int lineNumber = 1;
        System.out.println ("\nДобавленные товары:");
        Dish it = this;
        do {
            it.print (lineNumber++);
            it = it.next;
        } while (it != null);
    }
}
