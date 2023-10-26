package ru.library.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import ru.library.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.library.models.Person;
import ru.library.services.BookService;
import ru.library.services.PeopleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {

        this.bookService = bookService;
        this.peopleService = peopleService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,@ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findOne(id));
        model.addAttribute("people", peopleService.findAll());
        model.addAttribute("chelik", bookService.findOne(id).getOwner());
        return "books/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book , BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "books/new";
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";
    }
    @PostMapping("/{id}")
    public String setBook(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable int id){
        Book updatedBook = bookService.findOne(id);
       updatedBook.setOwner(person);
        bookService.update(id,updatedBook);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) return "books/edit";
        bookService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/setFree")
    public String setFree(@PathVariable("id") int id) {
        bookService.setFree(id);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
