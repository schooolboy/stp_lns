package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.FormSubmitEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class MainController{
    String cls3, cls4;

    @GetMapping("/turnir_table.html")
    public String answer1(Model model) throws Exception{
        int value = 1;
        File f = null;
        FileInputStream fInp;
        FileOutputStream fOut;
        // в случае если файл существует читаем значение и определяем стиль, в ином случае (первый запуск - стиль пустой)
        try {
            fInp = new FileInputStream("savings.txt");
        } catch (FileNotFoundException e) {
            f = new File("savings.txt");
            fInp = new FileInputStream(f);
            value = 0;
        }
        if (value == 0) {
            cls3 = "";
            cls4 = "";
            fOut = new FileOutputStream(f);
            fOut.write(0);
        } else {
            value = fInp.read();
            if (value == 1) {
                cls3 = "st3";
                cls4 = "st4";
            } else {
                cls3 = "";
                cls4 = "";
            }
        }
        fInp.close();
        model.addAttribute("cls3", cls3);
        model.addAttribute("cls4", cls4);
        return "turnir_table.html";
    }

    @PostMapping("/turnir_tablet.html")
    public String answer2(@RequestParam String change, Model model) throws Exception{
        if (change.equals("on/off")) {
            int value = 1;
            FileInputStream fInp;
            FileOutputStream fOut;
            fInp = new FileInputStream("savings.txt");
            // чтение из файла
            value = fInp.read();
            if (value == 0) {
                cls3 = "st3";
                cls4 = "st4";
                value = 1;
            } else {
                cls3 = "";
                cls4 = "";
                value = 0;
            }
            fInp.close();
            // удаляем содержимое файла
            new FileOutputStream("savings.txt").close();
            // пишем новое значение
            fOut = new FileOutputStream("savings.txt");
            fOut.write(value);
            // конец
            fOut.close();
            model.addAttribute("cls3", cls3);
            model.addAttribute("cls4",  cls4);
            return "turnir_table.html";
        } else
        {
            model.addAttribute("cls3", "");
            model.addAttribute("cls4",  "");
            return "turnir_table.html";
        }
    }

}

