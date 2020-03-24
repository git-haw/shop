package com.haw.shop.model;

import javax.persistence.*;

@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 品牌
     */
    @Column(name = "Product_type_id")
    private Integer productTypeId;

    /**
     * 质量
     */
    private Short quality;

    /**
     * 标题
     */
    private String title;

    /**
     * 货号
     */
    private String number;

    @Column(name = "productImage")
    private byte[] productimage;

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
     * 获取品牌
     *
     * @return Product_type_id - 品牌
     */
    public Integer getProductTypeId() {
        return productTypeId;
    }

    /**
     * 设置品牌
     *
     * @param productTypeId 品牌
     */
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * 获取质量
     *
     * @return quality - 质量
     */
    public Short getQuality() {
        return quality;
    }

    /**
     * 设置质量
     *
     * @param quality 质量
     */
    public void setQuality(Short quality) {
        this.quality = quality;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取货号
     *
     * @return number - 货号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置货号
     *
     * @param number 货号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return productImage
     */
    public byte[] getProductimage() {
        return productimage;
    }

    /**
     * @param productimage
     */
    public void setProductimage(byte[] productimage) {
        this.productimage = productimage;
    }
}