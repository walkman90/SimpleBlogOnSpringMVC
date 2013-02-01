/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import blog.model.Blogger;
import blog.model.Tweet;
import dao.impl.JdbcUserDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Walkman
 */

@Service("userService")
public class UserService {
    @Autowired
    private JdbcUserDAO userDAO;
    
    public void addUser(Blogger user) {
        userDAO.addUser(user);
    }
    
    public boolean checkLogin(String login) {
        return userDAO.checkUserLogin(login);
    }
    
    public boolean checkEmail(String email) {
        return userDAO.checkUserEmail(email);
    }
    
    public boolean logIn(String email, String password) {
        return userDAO.logIn(email, password);
    }
    
    public long getUserId(String email) {
        return userDAO.getUserId(email);
    }
    
    public Blogger getBloggerObject(long id) {
        return userDAO.getBloggerObject(id);
    }
    
    public void addImage(Long id, String url) {
        userDAO.addImage(id, url);
    }
    
    public void addTweet(String tweet, long id) {
        userDAO.addTweet(tweet, id);
    }
    
    public List<Tweet> getTweets(long id){
        return userDAO.getTweets(id);
    }
    
    public String getUserName(String email) {
        return userDAO.getUserName(email);
    }
    
    public void removeTweet(long id) {
        userDAO.removeTweet(id);
    }
}
