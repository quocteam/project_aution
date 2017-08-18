/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import app.UserProcess;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author quoc95
 */
@FacesValidator("priceValidator")
public class verify implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        int a = Integer.parseInt(value.toString());
        UserProcess us = new UserProcess();
        if(a>10)
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"","a lớn hơn 10"));
        else
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"","a nhỏ hơn 10"));
    }
    
}
