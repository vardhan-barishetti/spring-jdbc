package com.spring.jdbc.ecom.dao.impl;

import com.spring.jdbc.ecom.dao.ProductDao;
import com.spring.jdbc.ecom.helper.ProductMapper;
import com.spring.jdbc.ecom.model.Product;
import com.spring.jdbc.ecom.model.ProductWithCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private JdbcTemplate jdbcTemplate;

    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        String createQuery = "CREATE TABLE IF NOT EXISTS products(id int primary key, title varchar(200) not null, description varchar(200) not null, price int not null, cat_id int, FOREIGN KEY (cat_id) REFERENCES categories(id))";
        jdbcTemplate.update(createQuery);
    }

    @Override
    public Product create(Product product) {
        String query = "insert into products(id, title, description, price,cat_id) values(?,?,?,?,?)";
        int update =
                jdbcTemplate.update(
                        query,
                        product.getId(),
                        product.getTitle(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCatId());

        System.out.println(update + "rows effected");
        return product;
    }

    @Override
    public Product update(Product product, int productId) {
        String query = "update products set title=?, description=?, price=? where id=?";
        int updatedRows = jdbcTemplate.update(
                query,
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                productId

        );
        System.out.println(updatedRows + "rows effected");
        product.setId(productId);
        return product;
    }

    @Override
    public void delete(int productId) {
        String query = "delete from products where id=?";
        int updatedRows = jdbcTemplate.update(
                query,
                productId
        );
        System.out.println(updatedRows + "rows effected");

    }

    @Override
    public List<Product> getAll() {
        String query = "select * from products";
//        List<Product> products = jdbcTemplate.queryForList(query, Product.class);

        //impl 2

//        List<Product> products =  jdbcTemplate.query(query, new RowMapper<Product>() {
//            @Override
//            public Product mapRow(ResultSet rs, int rowNum) throws SQLException{
//                Product product = new Product();
//                product.setId(rs.getInt("id"));
//                product.setTitle(rs.getString("title"));
//                product.setPrice(rs.getInt("price"));
//                product.setDescription(rs.getString("description"));
//
//                return product;
//
//            }
//        });


        //impl 3

//        List<Product> products = jdbcTemplate.query(query, (rs, rowNum) -> {
//            Product product = new Product();
//            product.setId(rs.getInt("id"));
//            product.setTitle(rs.getString("title"));
//            product.setPrice(rs.getInt("price"));
//            product.setDescription(rs.getString("description"));
//
//            return product;
//
//        });

        //impl 4

        List<Product> products = jdbcTemplate.query(query, new ProductMapper());

        return products;
    }

    @Override
    public Product get(int productId) {
        Product product = jdbcTemplate.queryForObject("select * from products where id=?",new ProductMapper(), productId);
        return product;
    }

    @Override
    public List<Product> search(String keyword) {
        return null;
    }

    @Override
    public List<Product> getAllByCategory(int CatId) {
        return null;
    }

    @Override
    public List<ProductWithCategory> getAllWithCategory() {
        String query = "select p.id as id, p.title as title, p.description as description, p.price as price, c.title as catTitle FROM products p INNER JOIN categories c ON p.cat_id=c.id";

        return jdbcTemplate.query(query,(rs, rowNum) ->
                {
                    ProductWithCategory productWithCategory = new ProductWithCategory();
                    productWithCategory.setId(rs.getInt("id"));
                    productWithCategory.setTitle(rs.getString("title"));
                    productWithCategory.setDescription(rs.getString("description"));
                    productWithCategory.setCatTitle(rs.getString("catTitle"));
                    return productWithCategory;
                }
                );
    }
}
