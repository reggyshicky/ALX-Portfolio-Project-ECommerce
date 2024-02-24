package com.reginah.Expensetrackerapi.repository;

import com.reginah.Expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    //SELECT * FROM tbl_expenses WHERE category=? -behind the scenes
    Page<Expense> findByCategory(String Category, Pageable page);

    //SELECT * FROM tbl_expenses WHERE name LIKE '%keywords%' -'behind the scenes'
    Page<Expense> findByNameContaining(String keyword, Pageable page);

    //This date is of type sql
    //SELECT * FROM tbl_exense WHERE date BETWEEN `startDate` AND `endDate`
    Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);
}
