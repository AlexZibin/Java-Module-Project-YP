import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numberOfPersons;
        int numberOfGoods = 0;
        double amount = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println ("\nКалькулятор счёта v1.0");
        System.out.println ("Давайте разделим счёт поровну!");

        //*** 1. Входные параметры для счётчика
        do {
            System.out.print ("На скольких человек делить счёт (обязательно > 1)? ");
            numberOfPersons = scanner.nextInt();
            if (numberOfPersons == 1) {
                System.out.println ("Ошибка. В этом случае нет смысла ничего считать и делить.");
            } else if (numberOfPersons < 1) {
                System.out.println ("Ошибка. Это некорректное значение для подсчёта.");
            }
        } while (numberOfPersons <= 1);
        scanner.nextLine(); //throw away the \n not consumed by nextInt()

        //*** 2. Добавление товаров в калькулятор
        Tovar firstTovar = null;
        Tovar  nextTovar = null;
        do {
            //System.out.println ("Название товара (или слово \"Завершить\"):");
            //1. Запросите у пользователя название товара и его стоимость. Стоимость должна быть в формате рубли.копейки, например 10.45 или 11.40.
            ++numberOfGoods;
            System.out.print ("Название товара: ");
            String name = scanner.nextLine();
            if (name.isEmpty()) name = "<Пусто" + numberOfGoods + ">";
            System.out.printf ("Введено название: %s\n", name);
            //if (name.equalsIgnoreCase("Завершить")) break;

            Double price;
            Double ourMagicValue = -3.1415962;
            do {
                System.out.print("Cтоимость товара: ");
                /*price = scanner.nextDouble();
                scanner.nextLine();*/
                try {
                    price = Double.parseDouble (scanner.nextLine ());
                } catch (NumberFormatException e) {
                    price = ourMagicValue;
                }
                if (price > 0) break;
                if (price == ourMagicValue)
                    System.out.println("Ошибка! Нецифровые символы вводить нельзя. Целую и дробную часть надо разделять точкой, а не запятой.\n\n");
                else
                    System.out.println("Ошибка! Cтоимость товара должна быть больше нуля!\n\n");
            } while (true);

            //3. При добавлении товара в калькулятор нужно считать текущую общую сумму всех товаров.
            amount += price;

            //4. Калькулятор должен запоминать названия всех добавленных товаров, чтобы выводить все товары, которые были в него добавлены.
            //Зибин: Можно было все товары сцепить в 1 String, но я решил сделать через список:
            nextTovar = new Tovar (name, price);
            if (firstTovar == null) {
                firstTovar = nextTovar;
            } else {
                firstTovar.attach (nextTovar);
            }

            //5. После добавления товара в калькулятор нужно показать пользователю сообщение об успешном добавлении товара.
            System.out.print   ("Успешно добавлен товар: ");
            nextTovar.print ();

            //6. После добавления товара нужно спрашивать у пользователя, хочет ли он добавить ещё один товар.
            System.out.println ("Хотите добавить ещё один товар? \n(Введите \"Да\" или слово \"Завершить\"):");
            //7. Пользователь должен ввести команду "Завершить" для того, чтоб завершить процесс добавления товаров.
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("Завершить")) break;
            //8. Введение любого другого символа или слова должно запрашивать у пользователя следующий товар до тех пор,
            //   пока пользователь не введёт команду "Завершить" после добавления товара.

        } while (true);

        //*** 3. Вывод результатов
        //1. ...нужно показать пользователю все добавленные товары и посчитать, сколько должен заплатить каждый человек.
        //2. Каждый новый добавленный товар должен быть выведен с новой строки после текста: "Добавленные товары:".
        System.out.printf ("Отлично! \nОбщая сумма: %s\n", Formatter.formatter (amount));
        System.out.println ("Добавленные товары:\n");
        firstTovar.printAll();
        System.out.printf ("\nСумма, которую должен заплатить каждый из %d человек: %s\n", numberOfPersons, Formatter.formatter (amount/numberOfPersons));
    }
}

