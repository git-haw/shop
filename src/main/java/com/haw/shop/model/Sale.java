package com.haw.shop.model;

import javax.persistence.*;

@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 单价
     */
    private Long price;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 商家编码
     */
    @Column(name = "store_code")
    private String storeCode;

    /**
     * 商品条形码
     */
    @Column(name = "product_bar_code")
    private String productBarCode;

    /**
     * 所属商品
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取单价
     *
     * @return price - 单价
     */
    public Long getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * 获取数量
     *
     * @return number - 数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置数量
     *
     * @param number 数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取商家编码
     *
     * @return store_code - 商家编码
     */
    public String getStoreCode() {
        return storeCode;
    }

    /**
     * 设置商家编码
     *
     * @param storeCode 商家编码
     */
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    /**
     * 获取商品条形码
     *
     * @return product_bar_code - 商品条形码
     */
    public String getProductBarCode() {
        return productBarCode;
    }

    /**
     * 设置商品条形码
     *
     * @param productBarCode 商品条形码
     */
    public void setProductBarCode(String productBarCode) {
        this.productBarCode = productBarCode;
    }

    /**
     * 获取所属商品
     *
     * @return product_id - 所属商品
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置所属商品
     *
     * @param productId 所属商品
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}