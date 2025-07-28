package com.project.bookcenter.book.controller;

import com.project.bookcenter.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/selectBookList")
    public List<Map<String, Object>> selectBookList(@RequestParam Map<String, Object> paramMap) {
        return bookService.selectBookList(paramMap);
    }
}