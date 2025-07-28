package com.project.bookcenter.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html 렌더링됨
    }

    @GetMapping("/books")
    public String booksPage(Model model) {
        // 페이지 식별용 변수 전달 (사이드바 메뉴 강조용)
        model.addAttribute("page", "books");
        return "books/book-list"; // templates/books/book-list.html
    }
}
