/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import app.CategoryProcess;
import java.util.ArrayList;
import model.Category;

/**
 *
 * @author quoc9
 */
public class CategoryBean {

    ArrayList<Category> arrCate =  new CategoryProcess().getAllCategory();
    ArrayList<String> arrType = new CategoryProcess().getAllCategoryType();

    public ArrayList<Category> getArrCate() {
        return arrCate;
    }

    public void setArrCate(ArrayList<Category> arrCate) {
        this.arrCate = arrCate;
    }

    public ArrayList<String> getArrType() {
        return arrType;
    }

    public void setArrType(ArrayList<String> arrType) {
        this.arrType = arrType;
    }
    
    public CategoryBean() {
    }
    
}
