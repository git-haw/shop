package com.haw.shop.model;

import javax.persistence.*;

@Table(name = "describe")
public class Describe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 所属商品
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 宝贝主图
     */
    @Column(name = "image_1")
    private String image1;

    @Column(name = "image_2")
    private String image2;

    @Column(name = "image_3")
    private String image3;

    @Column(name = "image_4")
    private String image4;

    @Column(name = "image_5")
    private String image5;

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

    /**
     * 获取宝贝主图
     *
     * @return image_1 - 宝贝主图
     */
    public String getImage1() {
        return image1;
    }

    /**
     * 设置宝贝主图
     *
     * @param image1 宝贝主图
     */
    public void setImage1(String image1) {
        this.image1 = image1;
    }

    /**
     * @return image_2
     */
    public String getImage2() {
        return image2;
    }

    /**
     * @param image2
     */
    public void setImage2(String image2) {
        this.image2 = image2;
    }

    /**
     * @return image_3
     */
    public String getImage3() {
        return image3;
    }

    /**
     * @param image3
     */
    public void setImage3(String image3) {
        this.image3 = image3;
    }

    /**
     * @return image_4
     */
    public String getImage4() {
        return image4;
    }

    /**
     * @param image4
     */
    public void setImage4(String image4) {
        this.image4 = image4;
    }

    /**
     * @return image_5
     */
    public String getImage5() {
        return image5;
    }

    /**
     * @param image5
     */
    public void setImage5(String image5) {
        this.image5 = image5;
    }
}