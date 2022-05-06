package com.nikita.dem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    String cls3, cls4;
    int a = 1;
    @GetMapping("/turnir_table.html")
    public String answer(Model model) {
        if (a % 2 != 0) {
            cls3 = "st3";
            cls4 = "st4";
        } else {
            cls3 = "";
            cls4 = "";
        }
        a++;
        model.addAttribute("cls3", cls3);
        model.addAttribute("cls4", cls4);
        return "turnir_table.html";
    }

}
