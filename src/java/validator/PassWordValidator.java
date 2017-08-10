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
@FacesValidator("PassWordValidator")
public class PassWordValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String a = value.toString();
        FacesMessage ms = new FacesMessage();
        ms.setSummary("errorxx");
        ms.setSeverity(ms.SEVERITY_ERROR);
        UserProcess us = new UserProcess();
        if(a.length()<6)
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"","PassWord min length must be 6"));
        else if(a.length()>50)
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"","PassWord max length must be 50"));
        context.addMessage("myform:userName", ms);
    }
    
}
