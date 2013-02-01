/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import blog.model.Blogger;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Walkman
 */
public class BloggerRowMapper implements RowMapper {
    @Override
    public Blogger mapRow(ResultSet rs, int rowNum) throws SQLException {
        Blogger b = new Blogger();
        b.setLogin(rs.getString("LOGIN"));
        b.setEmail(rs.getString("EMAIL"));
        b.setImgURL(rs.getString("IMAGE_URL"));
        return b;
    }
                        
}
