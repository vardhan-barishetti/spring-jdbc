package com.spring.jdbc.ecom.dao.impl;

import com.spring.jdbc.ecom.dao.CategoryDao;
import com.spring.jdbc.ecom.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate jdbcTemplate;

    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        String createQuery = "CREATE TABLE IF NOT EXISTS categories(id int primary key, title varchar(200) not null, description varchar(200) not null)";
        jdbcTemplate.update(createQuery);
        System.out.println("Category table is created/adjusted");
    }

    @Override
    public Category create(Category category) {
        String query = "insert into categories(id, title, description) values(?,?,?)";
        int update =
                jdbcTemplate.update(
                        query,
                        category.getId(),
                        category.getTitle(),
                        category.getDescription()
                );

        System.out.println(update + "categories effected");
        return category;
    }
}
