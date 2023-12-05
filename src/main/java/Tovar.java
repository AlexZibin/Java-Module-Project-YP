public class Tovar {
    String name;
    double price;
    Tovar next;

    Tovar (String _name, double _price) {
        name = _name;
        price = _price;
        next = null;
    }
    void attach (Tovar _next) {
        Tovar t = this;

        while (t.next != null) t = t.next;
        t.next = _next;
    }

    void print () {
        System.out.printf ("%s, \t%s\n", name, Formatter.formatter(price));
    }
    void printAll () {
        System.out.println ("Добавленные товары:");
        Tovar it = this;
        do {
            it.print ();
            it = it.next;
        } while (it != null);
    }
}
