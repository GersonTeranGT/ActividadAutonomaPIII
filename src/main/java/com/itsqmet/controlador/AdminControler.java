package com.itsqmet.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControler {

    @GetMapping
    public String panelAdministrador(){
        return "pages/panelAdmin";
    }
}
