package com.example.inventoryTrackingWebApp.mapper;

import com.example.inventoryTrackingWebApp.model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM INVENTORY WHERE productId = #{productId}")
    Product getProduct(Integer productId);

    @Select("SELECT * FROM INVENTORY")
    List<Product> getProductList();

    @Insert("INSERT INTO INVENTORY (productId, productName, quantity) " +
            "VALUES(#{productId}, #{productName}, #{quantity})")
    @Options(useGeneratedKeys = true, keyProperty = "productId")
    int insertProduct(Product product);

    @Delete("DELETE FROM INVENTORY WHERE productId = #{productId}")
    int deleteProduct(Integer productId);

    @Update("UPDATE INVENTORY SET productName = #{productName}, quantity = #{quantity}" +
            "WHERE productId = #{productId}")
    int updateProduct(Product product);

}
