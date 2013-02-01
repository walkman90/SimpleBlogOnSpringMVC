/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import blog.model.Blogger;
import blog.model.Tweet;
import dao.UserDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 *
 * @author Walkman
 */
public class JdbcUserDAO extends JdbcDaoSupport implements UserDAO {
    @Override
    public void addUser(Blogger user){
        String sql = "INSERT INTO Users " +
			"(LOGIN, PASSWORD, EMAIL) VALUES (?, ?, ?)";
        
        getJdbcTemplate().update(sql, new Object[] { user.getLogin(),
            user.getPassword(), user.getEmail()
		});
    }
    @Override
    public boolean checkUserLogin(String login) {
         String sql = "SELECT COUNT(0) "
                    + "FROM USERS "
                    + "WHERE LOGIN = ?";
        Integer num = getJdbcTemplate().queryForObject(
                sql, new Object[] { login }, Integer.class);
        return (num > 0) ? true : false ;
    }
    
    @Override
    public boolean checkUserEmail(String email) {
         String sql = "SELECT COUNT(0) "
                    + "FROM USERS "
                    + "WHERE email = ?";
        Integer num = getJdbcTemplate().queryForObject(
                sql,new Object[] { email }, Integer.class);
        return (num > 0) ? true : false ;
    }
    
    @Override
    public boolean logIn(String email, String password)  {
       String sql = "SELECT COUNT(0) "
                    + "FROM USERS "
                    + "WHERE EMAIL = ? AND PASSWORD = ?";
        Integer num = getJdbcTemplate().queryForObject(
                sql,new Object[] { email, password }, Integer.class);
        return (num > 0) ? true : false ;
    }
    
    @Override
    public long getUserId(String email) {
         String sql = "SELECT ID "
                    + "FROM USERS "
                    + "WHERE EMAIL = ?";
        long id = getJdbcTemplate().queryForObject(
                sql,new Object[] { email}, Long.class);
        return id;
    }
    
    @Override
    public void addTweet(String tweet, long id) {
        String sql = "INSERT INTO TWEETS " +
			"(USER_ID, TEXT) VALUES (?, ?)";
        
        getJdbcTemplate().update(sql, new Object[] { id, tweet});
    }
    
    @Override
    public List<Tweet> getTweets(long id) {
         String sql = "SELECT TEXT, ID "
                    + "FROM TWEETS "
                    + "WHERE USER_ID = ?";
    return getJdbcTemplate().query(sql, new Object[] { id }, 
            new ParameterizedRowMapper<Tweet>() {
            @Override
            public Tweet mapRow(ResultSet rs, int rowNum) throws SQLException {
               Tweet tw = new Tweet();
               tw.setId(rs.getLong("ID"));
               tw.setText(rs.getString("TEXT"));
            return tw;
            }
        });
    }
    
    @Override
    public String getUserName(String email) {
        String sql = "SELECT LOGIN "
                    + "FROM USERS "
                    + "WHERE EMAIL = ?";
        return getJdbcTemplate().queryForObject(
                sql,new Object[] { email}, String.class);
        
    }

    @Override
    public void removeTweet(long id) {
        String sql = "DELETE "
                    + "FROM TWEETS "
                    + "WHERE ID = ?";
        getJdbcTemplate().update(sql,new Object[] { id});
    }
    
    @Override
    public Blogger getBloggerObject(long id) {
        String sql = "SELECT * "
                    + "FROM USERS "
                    + "WHERE ID = ?";
    return (Blogger)getJdbcTemplate().queryForObject(
			sql, new Object[] { id }, new BloggerRowMapper());
    }
    
    @Override
    public void addImage(Long id, String url){
        String sql = "UPDATE Users SET " +
			"IMAGE_URL = ? "
                      + "WHERE ID = ?";    
        getJdbcTemplate().update(sql, new Object[] { url, id });
    }
}