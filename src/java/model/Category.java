/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author quoc9
 */
public class Category {
    private String categoryName;
    private String categoryType;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public Category() {
    }

    public Category(String categoryName, String categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
    
}
