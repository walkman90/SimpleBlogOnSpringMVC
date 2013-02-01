/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
/**
 *
 * @author Walkman
 */
public class ImageUpload {
    private CommonsMultipartFile fileData;

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }
    
}
