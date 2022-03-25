package com.company;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        boolean res = false;
        int move;
        MyFuncs work = new MyFuncs();
        while(true) {
            work.ShowMenu();
            move = in.nextInt();
            switch (move) {
                case 1:
                    res = true;
                    work.CreateR(); // создаем
                    break;
                case 2:
                    if (res == false) {
                        System.out.println("Сначала нужно создать результат");
                        break;
                    }
                    work.LookR(); // промастриваем
                    break;
                case 3:
                    if (res == false) {
                        System.out.println("Сначала нужно создать результат");
                        break;
                    }
                    work.RedactR();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Нет такой операции");
            }
        }
    }
}