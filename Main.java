package com.company;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        boolean res = false;
        int move;
        my_funcs work = new my_funcs();
        while(true) {
            work.show_menu();
            move = in.nextInt();
            switch (move) {
                case 1:
                    res = true;
                    work.create_r(); // создаем
                    break;
                case 2:
                    if (res == false) {
                        System.out.println("Сначала нужно создать результат");
                        break;
                    }
                    work.look_r(); // промастриваем
                    break;
                case 3:
                    if (res == false) {
                        System.out.println("Сначала нужно создать результат");
                        break;
                    }
                    work.redact_r();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Нет такой операции");
            }
        }
    }
}

// все с чем работаем
class my_funcs {
    int N, K, cause;
    file_work fl;
    Scanner in;
    // вывод меню
    my_funcs() throws Exception{
        cause = 0;
        in = new Scanner(System.in);
        fl = new file_work("t1.txt", "t2.txt");
    }
    void show_menu (){
        System.out.println("1. Создать");
        System.out.println("2. Просмотреть");
        System.out.println("3. Отредактировать");
        System.out.println("4. Выход");
    }
    // создать
    boolean create_r() throws Exception{
        cause++;
        System.out.println("Число участников: ");
        while(true) {
            N = in.nextInt(); // N - число участников
            if (N > 2 && N < 65) {
                System.out.println("N:" + N);
                break;
            } else {
                System.out.println("Дипазон допустимых значений - от 3 до 64");
            }
        }
        // K - число кругов
        K = (int) (Math.log(N) / Math.log(2.0));
        System.out.println("K:" + K);
        fl.write(N, K, cause);
        return true;
    }
    // просмотреть
    boolean look_r ()throws Exception{
        System.out.println("Команда победы поражения ничьи");
        fl.read(N);
        return true;
    }
    // отредактировать
    boolean redact_r () throws Exception{
        fl.redact(N, K);
        return true;
    }
}

// работа с файлами
class file_work{
    Scanner in;
    String name1, name2;
    File f_names, f_stats;
    FileReader reader1, reader2;
    FileWriter n_writer;
    FileWriter st_writer;
    file_work(String name1, String name2) throws Exception{
        in = new Scanner(System.in);
        f_names = new File(name1);
        f_stats = new File(name2);
        f_names.createNewFile();
        f_stats.createNewFile();
        this.name1 = name1;
        this.name2 = name2;
    }
    // чтение для вывода
    boolean read(int N) throws Exception{
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
    boolean write(int N, int K, int cause) throws Exception{
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
        for (int i = 0; i < N; i++) {
            System.out.print("Имя команды: ");
            str = in.next();
            n_writer.write(str + '\n');
            System.out.print("Победы: ");
            while (true) {
                wins = in.nextInt();
                if (wins < 0 || wins > ((N - 1) * K)) {
                    System.out.println("Значение невозможно, введите другое: ");
                } else {
                    st_writer.write(wins + " ");
                    break;
                }
            }
            System.out.print("Поражения: ");
            while (true) {
                loses = in.nextInt();
                if (loses < 0 || loses > ((N - 1) * K - wins)) {
                    System.out.print("Значение невозможно, введите другое: ");
                } else {
                    st_writer.write(loses + " ");
                    break;
                }
            }
            // ввод ничья
            stays = ((N - 1) * K - wins - loses);
            st_writer.write(stays + "\n");
        }
        n_writer.close();
        st_writer.close();
        return true;
    }
    boolean redact (int N, int K) throws Exception{
        int n, wins, loses, stays;
        int [][]stats_arr = new int[N][3];
        String name;
        String []str_arr = new String[N];
        Scanner scanner = new Scanner(f_stats);
        reader1 = new FileReader(f_names);
        BufferedReader f_reader1 = new BufferedReader(reader1);
        System.out.print("Номер команды: ");
        n = in.nextInt();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                stats_arr[i][j] = scanner.nextInt();
            }
            str_arr[i] = f_reader1.readLine();
        }
        System.out.print("Новое имя: ");
        name = in.next();
        str_arr[n-1] = name;
        System.out.print("Победы: ");
        while (true) {
            wins = in.nextInt();
            if (wins < 0 || wins > ((N - 1) * K)) {
                System.out.print("Значение невозможно, введите другое: ");
            } else {
                stats_arr[n-1][0] = wins;
                break;
            }
        }
        System.out.print("Поражения: ");
        while (true) {
            loses = in.nextInt();
            if (loses < 0 || loses > ((N - 1) * K - wins)) {
                System.out.print("Значение невозможно, введите другое: ");
            } else {
                stats_arr[n-1][1] = loses;
                break;
            }
        }
        // ввод ничья
        stays = ((N - 1) * K - wins - loses);
        stats_arr[n-1][2] = stays;
        f_reader1.close(); reader1.close();
        scanner.close(); f_stats.delete();f_names.delete();
        f_names = new File(name1);
        f_stats = new File(name2);
        n_writer = new FileWriter(f_names, true); // информация записывается в конец файла
        st_writer = new FileWriter(f_stats, true);
        for (int i = 0; i < N; i++) {
            n_writer.write(str_arr[i] + '\n');
            st_writer.write(String.valueOf(stats_arr[i][0]) + " " + String.valueOf(stats_arr[i][1])+ " " + String.valueOf(stats_arr[i][2]) + '\n');
        }
        n_writer.close();
        st_writer.close();
        return true;
    }
}