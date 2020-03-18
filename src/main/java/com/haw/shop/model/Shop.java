package com.haw.shop.model;

import javax.persistence.*;

@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String logo;

    private String address;

    private Byte supply;

    @Column(name = "brief_introduce")
    private String briefIntroduce;

    private String introduce;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return supply
     */
    public Byte getSupply() {
        return supply;
    }

    /**
     * @param supply
     */
    public void setSupply(Byte supply) {
        this.supply = supply;
    }

    /**
     * @return brief_introduce
     */
    public String getBriefIntroduce() {
        return briefIntroduce;
    }

    /**
     * @param briefIntroduce
     */
    public void setBriefIntroduce(String briefIntroduce) {
        this.briefIntroduce = briefIntroduce;
    }

    /**
     * @return introduce
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * @param introduce
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}