package com.project.bookcenter.ib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ib")
public class BookReceiptController {

    @GetMapping("/receipt")
    public String showReceiptForm() {
        return "ib/book-receipt";
    }
}
