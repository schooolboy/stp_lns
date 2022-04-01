package com.company;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        boolean res = false;
        int move;
        MyFuncs work = new MyFuncs();
        while(true) {
            work.showMenu();
            move = in.nextInt();
            switch (move) {
                case 1:
                    res = true;
                    work.createR(); // создаем
                    break;
                case 2:
                    if (res == false) {
                        System.out.println("Сначала нужно создать результат");
                        break;
                    }
                    work.lookR(); // промастриваем
                    break;
                case 3:
                    if (res == false) {
                        System.out.println("Сначала нужно создать результат");
                        break;
                    }
                    work.redactR();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Нет такой операции");
            }
        }
    }
}
