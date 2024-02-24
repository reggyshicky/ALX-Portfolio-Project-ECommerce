package com.reginah.Expensetrackerapi.controller;

import com.reginah.Expensetrackerapi.entity.Expense;
import com.reginah.Expensetrackerapi.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;

import java.util.List;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    //Original without Pagination

    //    @GetMapping("/expenses")
//    public List<Expense> getAllExpenses() {
//        return expenseService.getAllExpenses();
//    }


//PAGINATION
//    @GetMapping("/expenses")
//    public Page<Expense> getAllExpenses(Pageable page) {
//
//        return expenseService.getAllExpenses(page);
//    }

    //alternative, removing
    // the pageable object of the API response
    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page) {
//        int number = 1;
//        calculateFactorial(number);

        return expenseService.getAllExpenses(page).toList();
    }



    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable() Long id) {
        return expenseService.getExpenseById(id);
    }


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses")
    public  void deleteExpenseById(@RequestParam() Long id) {
        expenseService.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense addExpenseDetails(@Valid @RequestBody Expense expense) {
        return expenseService.addExpenseDetails((expense));
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id) {
        return expenseService.updateExpenseDetails(id, expense);
    }

    //Test General Exception handling
    public int calculateFactorial(int number) {
        return number * calculateFactorial(number -1);
    }

    @GetMapping("/expenses/category")
    public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page) {
        return expenseService.readByCategory(category, page);
    }

    @GetMapping("/expenses/name")
    public List<Expense> getExpensesByName(@RequestParam String keyword, Pageable page) {
        return expenseService.readByName(keyword, page);
    }

    @GetMapping("/expenses/date")
    public List<Expense> getExpensesByDate(@RequestParam(required = false) Date startDate,
                                           @RequestParam(required = false) Date endDate,
                                           Pageable page) {
        return expenseService.readByDate(startDate, endDate, page);
    }
}
