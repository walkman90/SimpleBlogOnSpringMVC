/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import blog.model.Blogger;
import blog.model.Tweet;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Walkman
 */
public interface UserDAO {
    
    public void addUser(Blogger user);
    public boolean checkUserLogin(String login);
    public boolean checkUserEmail(String email);
    public boolean logIn(String email, String password);
    public long getUserId(String email);
    public void addTweet(String tweet, long id);
    public List<Tweet> getTweets(long id);
    public String getUserName(String email);
    public void removeTweet(long id);
    public Blogger getBloggerObject(long id);
    public void addImage(Long id, String url);
}
