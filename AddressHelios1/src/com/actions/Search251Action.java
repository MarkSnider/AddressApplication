package com.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.dao.Contact251;
import com.forms.InputForm;
import com.service.Contact251Service;

public class Search251Action extends DispatchAction{

    public ActionForward OK(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    		// Cast the form to our desired form
    		InputForm theForm = (InputForm)form;
    		
    		System.out.println("Search 251... theForm.getContact251InfoLike() " + theForm.getContact251InfoLike());
    		// Get the search criteria from the form
    		String criteria = theForm.getContact251InfoLike();
    		
    		// Do the search as a like search and get the list of results
    		List<Contact251> theList = Contact251Service.selectInfoLike(criteria);
    		
    		// place the list into the request
    		request.setAttribute("251List", theList);
    		
    		return(mapping.findForward("success"));
    }
}
