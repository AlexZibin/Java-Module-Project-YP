import static java.lang.Math.round;

public class Tovar {
    String name;
    double price;
    Tovar next;

    Tovar (String _name, double _price) {
        name = _name;
        //Оставляем 2 знака после запятой во избежание дальнейшей путаницы
        //price = ((double)((int)(_price*100)))/100;
        price = round(_price * 100.0)/100.0;
        next = null;
    }
    void attach (Tovar _next) {
        Tovar t = this;

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
        System.out.println ("Добавленные товары:");
        Tovar it = this;
        do {
            it.print (lineNumber++);
            it = it.next;
        } while (it != null);
    }
}
