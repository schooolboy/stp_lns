package com.company;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileWork {
    Scanner in;
    String name1, name2;
    File fNames, fStats;
    FileReader reader1, reader2;
    FileWriter nWriter;
    FileWriter stWriter;
    FileWork(String name1, String name2) throws Exception{
        in = new Scanner(System.in);
        fNames = new File(name1);
        fStats = new File(name2);
        if (!fNames.createNewFile()){
            fNames.delete();
            fNames.createNewFile();
        }
        if (!fStats.createNewFile()){
            fStats.delete();
            fStats.createNewFile();
        }
        this.name1 = name1;
        this.name2 = name2;
    }
    // чтение для вывода
    boolean read(int n) throws Exception{
        reader1 = new FileReader(fNames);
        reader2 = new FileReader(fStats);
        BufferedReader fReader1 = new BufferedReader(reader1);
        BufferedReader fReader2 = new BufferedReader(reader2);
        String str1, str2;
        for (int i = 0; i < n; i++) {
            str1 = fReader1.readLine();
            str2 = fReader2.readLine();
            System.out.println(str1 + ":  " + str2);
        }
        fReader1.close();
        fReader2.close();
        reader1.close();
        reader2.close();
        return true;
    }
    boolean write(int n, int k, int cause) throws Exception{
        if (cause > 1) {
            fNames.delete();
            fStats.delete();
            fNames.createNewFile();
            fStats.createNewFile();
        }
        nWriter = new FileWriter(fNames, true);
        stWriter = new FileWriter(fStats, true);
        int wins, loses, stays;
        String str;
        for (int i = 0; i < n; i++) {
            System.out.print("Имя команды: ");
            str = in.next();
            nWriter.write(str + '\n');
            System.out.print("Победы: ");
            while (true) {
                wins = in.nextInt();
                if (wins < 0 || wins > ((n - 1) * k)) {
                    System.out.println("Значение невозможно, введите другое: ");
                } else {
                    stWriter.write(wins + " ");
                    break;
                }
            }
            System.out.print("Поражения: ");
            while (true) {
                loses = in.nextInt();
                if (loses < 0 || loses > ((n - 1) * k - wins)) {
                    System.out.print("Значение невозможно, введите другое: ");
                } else {
                    stWriter.write(loses + " ");
                    break;
                }
            }
            // ввод ничья
            stays = ((n - 1) * k - wins - loses);
            stWriter.write(stays + "\n");
        }
        nWriter.close();
        stWriter.close();
        return true;
    }
    boolean redact (int n, int k) throws Exception{
        int num, wins, loses, stays;
        int [][]statsArr = new int[n][3];
        String name;
        String []strArr = new String[n];
        Scanner scanner = new Scanner(fStats);
        reader1 = new FileReader(fNames);
        BufferedReader fReader1 = new BufferedReader(reader1);
        System.out.print("Номер команды: ");
        num = in.nextInt();
        while (true) {
            if (num > n) {
                System.out.print("Номер команды не должен быть больше числа участников: ");
                num = in.nextInt();
            } else {break;}
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                statsArr[i][j] = scanner.nextInt();
            }
            strArr[i] = fReader1.readLine();
        }
        System.out.print("Новое имя: ");
        name = in.next();
        strArr[num-1] = name;
        System.out.print("Победы: ");
        while (true) {
            wins = in.nextInt();
            if (wins < 0 || wins > ((n - 1) * k)) {
                System.out.print("Значение невозможно, введите другое: ");
            } else {
                statsArr[num-1][0] = wins;
                break;
            }
        }
        System.out.print("Поражения: ");
        while (true) {
            loses = in.nextInt();
            if (loses < 0 || loses > ((n - 1) * k - wins)) {
                System.out.print("Значение невозможно, введите другое: ");
            } else {
                statsArr[num-1][1] = loses;
                break;
            }
        }
        // ввод ничья
        stays = ((n - 1) * k - wins - loses);
        statsArr[num-1][2] = stays;
        fReader1.close(); reader1.close();
        scanner.close(); fStats.delete();fNames.delete();
        fNames = new File(name1);
        fStats = new File(name2);
        nWriter = new FileWriter(fNames, true); // информация записывается в конец файла
        stWriter = new FileWriter(fStats, true);
        for (int i = 0; i < n; i++) {
            nWriter.write(strArr[i] + '\n');
            stWriter.write(String.valueOf(statsArr[i][0]) + " " + String.valueOf(statsArr[i][1])+ " " + String.valueOf(statsArr[i][2]) + '\n');
        }
        nWriter.close();
        stWriter.close();
        return true;
    }
}
