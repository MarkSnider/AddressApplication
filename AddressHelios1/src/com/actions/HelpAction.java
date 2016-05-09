package com.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class HelpAction extends DispatchAction{
	
    private final static String SUCCESS = "success";
    private final static String ERROR = "error";
    
    public ActionForward OK(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	System.err.println("INSIDE HELP ACTION");
    	String forwardLocation=SUCCESS;	
    	return mapping.findForward(forwardLocation);
    }

}
