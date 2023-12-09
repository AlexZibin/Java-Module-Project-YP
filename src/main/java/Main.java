import static java.lang.Math.round;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numberOfPersons;
        double billTotal = 0;
        boolean notNumber;

        Scanner scanner = new Scanner(System.in);
        System.out.println ("\nКалькулятор счёта v1.0");
        System.out.println ("Давайте разделим счёт поровну!");

        //*** 1. Входные параметры для счётчика
        do {
            System.out.print ("На скольких человек делить счёт (обязательно > 1)? ");
            //numberOfPersons = scanner.nextInt();
            numberOfPersons = 0;
            notNumber = false;
            try {
                numberOfPersons = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                notNumber = true;
            }
            if (numberOfPersons == 1) {
                System.out.println ("Ошибочка. Один человек? \nВ этом случае нет смысла ничего считать и делить.\n");
            } else if (notNumber) {
                System.out.println("\nОшибка! Ожидается целое число, бОльшее 1.\nДробные числа и нецифровые символы вводить нельзя.\n");
            } else if (numberOfPersons < 1) {
                System.out.println ("Ошибка! Это некорректное значение для подсчёта.\nОжидается целое число, бОльшее 1.\n");
            }
        } while (numberOfPersons <= 1);
        System.out.printf ("Отлично!\nБудем делить счёт на %d человек.\nПриступим к выбору блюд:\n", numberOfPersons);

        //*** 2. Добавление товаров в калькулятор
        int numberOfDishes = 0;
        Dish firstDish = null;
        Dish nextDish;
        do {
            //System.out.println ("Название товара (или слово \"Завершить\"):");
            //1. Запросите у пользователя название товара и его стоимость. Стоимость должна быть в формате рубли.копейки, например 10.45 или 11.40.
            System.out.printf ("Название блюда № %d: ", ++numberOfDishes);
            String name = scanner.nextLine();
            if (name.isEmpty()) name = "<Пусто" + numberOfDishes + ">";
            System.out.printf ("Введено название: %s\n", name);
            //if (name.equalsIgnoreCase("Завершить")) break;

            double price;

            // Отредактировано после замечания:
            // Это можно сломать и вывести не то сообщение, если просто ввести цену -3.1415962, так что лучше так не делать, подумай как можно переделать
            //double ourMagicValue = -3.1415962;
            do {
                price = 0;
                notNumber = false;
                System.out.print("Cтоимость товара: ");
                /*price = scanner.nextDouble();
                scanner.nextLine();*/
                try {
                    price = Double.parseDouble (scanner.nextLine ());
                } catch (NumberFormatException e) {
                    notNumber = true;
                }
                //Оставляем 2 знака после запятой во избежание дальнейшей путаницы
                //price = ((double)((int)(_price*100)))/100;
                price = round (price * 100.0)/100.0;
                if (price > 0)
                    break;
                if (notNumber)
                    System.out.println ("\nОшибка! Нецифровые символы вводить нельзя. Целую и дробную часть надо разделять точкой, а не запятой.\n");
                else
                    System.out.printf ("\nОшибка! Cтоимость товара (%.2f) должна быть больше нуля!\n", price);
            } while (true);

            //3. При добавлении товара в калькулятор нужно считать текущую общую сумму всех товаров.
            billTotal += price;

            //4. Калькулятор должен запоминать названия всех добавленных товаров, чтобы выводить все товары, которые были в него добавлены.
            //Зибин: Можно было все товары сцепить в 1 String, но я решил сделать через список:
            nextDish = new Dish(name, price);
            if (firstDish == null) {
                firstDish = nextDish;
            } else {
                firstDish.attach (nextDish);
            }

            //5. После добавления товара в калькулятор нужно показать пользователю сообщение об успешном добавлении товара.
            System.out.print   ("Успешно добавлен товар: ");
            nextDish.print ();

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
        System.out.printf ("Отлично! \nОбщая сумма: %s.\n", Formatter.formatter (billTotal).trim ());
        //System.out.println ("Добавленные товары:\n");
        firstDish.printAll();
        System.out.printf ("\nСумма, которую должен заплатить каждый из %d человек: %s.\n",
                numberOfPersons, Formatter.formatter (billTotal/numberOfPersons).trim ());
    }
}

