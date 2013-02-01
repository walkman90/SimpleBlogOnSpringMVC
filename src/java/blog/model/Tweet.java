/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model;

/**
 *
 * @author Walkman
 */

public class Tweet {
    private long id;
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }    
}
