package com.company;
import java.util.Scanner;

public class MyFuncs {
    int n, k, cause;
    FileWork fl;
    Scanner in;
    // вывод меню
    MyFuncs() throws Exception{
        cause = 0;
        in = new Scanner(System.in);
        fl = new FileWork("t1.txt", "t2.txt");
    }
    void showMenu (){
        System.out.println("1. Создать");
        System.out.println("2. Просмотреть");
        System.out.println("3. Отредактировать");
        System.out.println("4. Выход");
    }
    // создать
    boolean createR() throws Exception{
        cause++;
        System.out.println("Число участников: ");
        while(true) {
            n = in.nextInt(); // N - число участников
            if (n > 2 && n < 65) {
                System.out.println("N:" + n);
                break;
            } else {
                System.out.println("Дипазон допустимых значений - от 3 до 64");
            }
        }
        // K - число кругов
        k = (int) (Math.log(n) / Math.log(2.0));
        System.out.println("K:" + k);
        fl.write(n, k, cause);
        return true;
    }
    // просмотреть
    boolean lookR ()throws Exception{
        System.out.println("Команда победы поражения ничьи");
        fl.read(n);
        return true;
    }
    // отредактировать
    boolean redactR () throws Exception{
        fl.redact(n, k);
        return true;
    }
}
