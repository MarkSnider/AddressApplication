package com.forms;

import org.apache.struts.upload.FormFile;

public class FileUploadForm extends org.apache.struts.action.ActionForm {
	
    public FileUploadForm() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private FormFile file;
    
    public FormFile getFile() {
        return file;
    }
 
    public void setFile(FormFile file) {
        this.file = file;
    }    
}
