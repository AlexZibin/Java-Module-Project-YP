import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numberOfPersons;
        double amount = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println ("\nКалькулятор счёта v1.0");
        System.out.println ("Давайте разделим счёт поровну!");
        do {
            System.out.println ("На скольких человек делить счёт (обязательно > 1) ?");
            numberOfPersons = scanner.nextInt();
            if (numberOfPersons == 1) {
                System.out.println ("В этом случае нет смысла ничего считать и делить.");
            } else if (numberOfPersons < 1) {
                System.out.println ("Это некорректное значение для подсчёта.");
            }
        } while (numberOfPersons <= 1);
        scanner.nextLine(); //throw away the \n not consumed by nextInt()

        Tovar firstTovar = null;
        Tovar  nextTovar = null;
        do {
            //System.out.println ("Название товара (или слово \"Завершить\"):");
            System.out.println ("Название товара:");
            String name = scanner.nextLine();
            System.out.printf ("Введено название: %s\n", name);
            //if (name.equalsIgnoreCase("Завершить")) break;

            double price;
            do {
                System.out.println("Cтоимость товара:");
                price = scanner.nextDouble();
                scanner.nextLine();
                if (price > 0) break;
                System.out.println("Ошибка! Cтоимость товара должна быть больше нуля!\n\n");
            } while (true);

            amount += price;

            nextTovar = new Tovar (name, price);
            if (firstTovar == null) {
                firstTovar = nextTovar;
            } else {
                firstTovar.attach (nextTovar);
            }

            //После добавления товара в калькулятор нужно показать пользователю сообщение об успешном добавлении товара.
            System.out.print   ("Успешно добавлен товар ");
            nextTovar.print ();

            //После добавления товара нужно спрашивать у пользователя, хочет ли он добавить ещё один товар.
            System.out.println ("Хотите добавить ещё один товар? \n(Введите \"Да\" или слово \"Завершить\"):");
            //Пользователь должен ввести команду "Завершить" для того, чтоб завершить процесс добавления товаров.
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("Завершить")) break;
            //Введение любого другого символа или слова должно запрашивать у пользователя следующий товар до тех пор,
            //   пока пользователь не введёт команду "Завершить" после добавления товара.

        } while (true);

        //нужно показать пользователю все добавленные товары и посчитать, сколько должен заплатить каждый человек.
        //Каждый новый добавленный товар должен быть выведен с новой строки после текста: "Добавленные товары:".
        System.out.printf ("Отлично! \nОбщая сумма: %.2f\n", amount);

        firstTovar.printAll();
    }
}

