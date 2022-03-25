package com.company;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileWork {
    Scanner in;
    String name1, name2;
    File f_names, f_stats;
    FileReader reader1, reader2;
    FileWriter n_writer;
    FileWriter st_writer;
    FileWork(String name1, String name2) throws Exception{
        in = new Scanner(System.in);
        f_names = new File(name1);
        f_stats = new File(name2);
        if (!f_names.createNewFile()){
            f_names.delete();
            f_names.createNewFile();
        }
        if (!f_stats.createNewFile()){
            f_stats.delete();
            f_stats.createNewFile();
        }
        this.name1 = name1;
        this.name2 = name2;
    }
    // чтение для вывода
    boolean Read(int N) throws Exception{
        reader1 = new FileReader(f_names);
        reader2 = new FileReader(f_stats);
        BufferedReader f_reader1 = new BufferedReader(reader1);
        BufferedReader f_reader2 = new BufferedReader(reader2);
        String str1, str2;
        for (int i = 0; i < N; i++) {
            str1 = f_reader1.readLine();
            str2 = f_reader2.readLine();
            System.out.println(str1 + ":  " + str2);
        }
        f_reader1.close();
        f_reader2.close();
        reader1.close();
        reader2.close();
        return true;
    }
    boolean Write(int n, int k, int cause) throws Exception{
        if (cause > 1) {
            f_names.delete();
            f_stats.delete();
            f_names.createNewFile();
            f_stats.createNewFile();
        }
        n_writer = new FileWriter(f_names, true);
        st_writer = new FileWriter(f_stats, true);
        int wins, loses, stays;
        String str;
        for (int i = 0; i < n; i++) {
            System.out.print("Имя команды: ");
            str = in.next();
            n_writer.write(str + '\n');
            System.out.print("Победы: ");
            while (true) {
                wins = in.nextInt();
                if (wins < 0 || wins > ((n - 1) * k)) {
                    System.out.println("Значение невозможно, введите другое: ");
                } else {
                    st_writer.write(wins + " ");
                    break;
                }
            }
            System.out.print("Поражения: ");
            while (true) {
                loses = in.nextInt();
                if (loses < 0 || loses > ((n - 1) * k - wins)) {
                    System.out.print("Значение невозможно, введите другое: ");
                } else {
                    st_writer.write(loses + " ");
                    break;
                }
            }
            // ввод ничья
            stays = ((n - 1) * k - wins - loses);
            st_writer.write(stays + "\n");
        }
        n_writer.close();
        st_writer.close();
        return true;
    }
    boolean Redact (int n, int k) throws Exception{
        int num, wins, loses, stays;
        int [][]stats_arr = new int[n][3];
        String name;
        String []str_arr = new String[n];
        Scanner scanner = new Scanner(f_stats);
        reader1 = new FileReader(f_names);
        BufferedReader f_reader1 = new BufferedReader(reader1);
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
                stats_arr[i][j] = scanner.nextInt();
            }
            str_arr[i] = f_reader1.readLine();
        }
        System.out.print("Новое имя: ");
        name = in.next();
        str_arr[num-1] = name;
        System.out.print("Победы: ");
        while (true) {
            wins = in.nextInt();
            if (wins < 0 || wins > ((n - 1) * k)) {
                System.out.print("Значение невозможно, введите другое: ");
            } else {
                stats_arr[num-1][0] = wins;
                break;
            }
        }
        System.out.print("Поражения: ");
        while (true) {
            loses = in.nextInt();
            if (loses < 0 || loses > ((n - 1) * k - wins)) {
                System.out.print("Значение невозможно, введите другое: ");
            } else {
                stats_arr[num-1][1] = loses;
                break;
            }
        }
        // ввод ничья
        stays = ((n - 1) * k - wins - loses);
        stats_arr[num-1][2] = stays;
        f_reader1.close(); reader1.close();
        scanner.close(); f_stats.delete();f_names.delete();
        f_names = new File(name1);
        f_stats = new File(name2);
        n_writer = new FileWriter(f_names, true); // информация записывается в конец файла
        st_writer = new FileWriter(f_stats, true);
        for (int i = 0; i < n; i++) {
            n_writer.write(str_arr[i] + '\n');
            st_writer.write(String.valueOf(stats_arr[i][0]) + " " + String.valueOf(stats_arr[i][1])+ " " + String.valueOf(stats_arr[i][2]) + '\n');
        }
        n_writer.close();
        st_writer.close();
        return true;
    }
}
