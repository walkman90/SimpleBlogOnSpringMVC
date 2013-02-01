/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
 

import blog.model.Blogger;
import blog.model.ImageUpload;
import blog.model.LogIn;
import blog.model.Tweet;
import common.UserService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
@SessionAttributes
public class BlogController {
    @Autowired   
    private UserService userService;
 
    @RequestMapping(value = "/addBlogger", 
            method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addUser(@Valid @ModelAttribute("blogger") Blogger blogger, 
     BindingResult bindingResult,  HttpSession session, Model model) {
        if(userService.checkLogin(blogger.getLogin())) {
            bindingResult.rejectValue("login", "wrong.username",
                    "This login already taken, try another, please.");
        }
        if(userService.checkEmail(blogger.getEmail())) {
            bindingResult.rejectValue("email", "wrong.email",
                    "This email already registered");
        }    
        if(bindingResult.hasErrors()){
            model.addAttribute("blogger", blogger);
            return new ModelAndView("form", "blogger", new Blogger());
        }
        userService.addUser(blogger);
        session.setAttribute("userId", 
                userService.getUserId(blogger.getEmail()));
        session.setAttribute("userName", 
                userService.getUserName(blogger.getEmail()));
        return new ModelAndView(
                "registrationSuccess", "imageUpload", new ImageUpload());
    }
    
    @RequestMapping(value = "/imgUpload", 
            method = { RequestMethod.POST,RequestMethod.GET })
    public ModelAndView imgUpload(@ModelAttribute("imageUpload") ImageUpload imgUpload, 
    BindingResult bindingResult, HttpSession session, 
    HttpServletRequest request, Model model) throws IOException {  
        MultipartFile file = imgUpload.getFileData();
        if(file != null) {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            String userName = (String)session.getAttribute("userName");
            if((file.getContentType()).contains("image")) {
                String type = (file.getContentType()).substring(6);
                String localFileName;
                if (file.getSize() > 0) {
                    inputStream = file.getInputStream();
                    if (file.getSize() > 1000000) {
                        bindingResult.rejectValue(
                                "fileData", "wrong.fileData", "Size over 1MB!");
                    }
                    String fileName = request.getServletContext().getRealPath("") + 
                            "\\..\\..\\web\\img\\" + userName + "." + type;
                    outputStream = new FileOutputStream(fileName);
                    int readBytes = 0;
                    byte[] buffer = new byte[10000];
                    while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                        outputStream.write(buffer, 0, readBytes);
                    }
                    outputStream.close();
                    inputStream.close();

                    String thumbFileName = fileName.replace("."+type, "_thumb."+type);
                    Thumbnails.of(fileName).size(200, 200).toFile(thumbFileName);
                    localFileName = "/img/" + userName + "_thumb." + type;
                } else {
                    localFileName =  "/img/default.jpg";
                }
                userService.addImage(
                        (Long)session.getAttribute("userId"), localFileName);
            } else {
                bindingResult.rejectValue("fileData",
                        "empty.img","It's not an image");
            }
        } else {
            userService.addImage(
                    (Long)session.getAttribute("userId"), "/img/default.jpg");
        }
        if(bindingResult.hasErrors()){
              return new ModelAndView(
                      "registrationSuccess", "imageUpload", new ImageUpload());
        }
        
        Blogger b = userService.getBloggerObject(
                (Long)session.getAttribute("userId"));
        session.setAttribute("blogger", b);
        return new ModelAndView("userHome");
    }
     
    @RequestMapping("/registration")
    public ModelAndView showContacts() {       
        return new ModelAndView("form", "blogger", new Blogger());
    }
    
    @RequestMapping(value = "/userHome",
            method = { RequestMethod.POST,RequestMethod.GET })
    public String showHomePage(@ModelAttribute("logIn") LogIn logIn,
    BindingResult bindingResult, Model model, HttpSession session) {
        if(session.getAttribute("userId") == null) {
            if(!userService.logIn(logIn.getEmail(), logIn.getPassword())) {
                bindingResult.rejectValue("password",
                   "wrong.username.password", "Incorrect username or password");
            }
            if(bindingResult.hasErrors()){
                model.addAttribute("logIn", logIn);
                return "logIn";
            }
            session.setAttribute("userId", 
                    userService.getUserId(logIn.getEmail()));
            session.setAttribute("userName", 
                    userService.getUserName(logIn.getEmail()));
        }
        List<Tweet> tweets = userService.getTweets(
                (Long)session.getAttribute("userId"));
        Collections.reverse(tweets);
        Blogger b = userService.getBloggerObject(
                (Long)session.getAttribute("userId"));
        session.setAttribute("blogger", b);
        model.addAttribute("tweets", tweets);
        return "userHome";
    }
    
    @RequestMapping("/logIn")
    public ModelAndView showLogInPage() {
        return new ModelAndView("logIn", "logIn", new LogIn());
    }
    
    @RequestMapping(value = "/addTweet", method = RequestMethod.POST)
    public String addTweet(HttpServletRequest request, HttpSession session) {
        String tweet = request.getParameter("tweet");
        if(!tweet.isEmpty()) {
            userService.addTweet(tweet, (Long)session.getAttribute("userId"));
        }
        return "redirect:/userHome.html";
    }
    
    @RequestMapping(value = "/removeTweet", method = RequestMethod.GET)
    public String removeTweet( @RequestParam("id") Long id, Model model) {
        userService.removeTweet(id);
        return "redirect:/userHome.html";
    }
    
    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:/logIn.html";
    }
      
}