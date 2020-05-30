package com.handel.geo.controller;

import com.handel.geo.model.Users;
import com.handel.geo.model.Post;
import com.handel.geo.service.PostService;
import com.handel.geo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        List<Post> allPost = postService.getAllPosts();
        ModelAndView modelAndView = new ModelAndView();
        Users user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("allPosts", allPost);
        modelAndView.addObject("user", user);
        Post post = new Post();
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"));
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            post.setUser(userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        }
        modelAndView.addObject("post", post);
        System.out.println(post.getUser());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView createPost(@Valid Post post, BindingResult bindingResult, ModelAndView modelAndView) {
        System.out.println("POST: --> " + post);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            modelAndView.setViewName("home");
        } else {
            if (post != null) {
                post.setUser(userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
                post.setDate(LocalDateTime.now());
                postService.savePost(post);
                modelAndView.setViewName("redirect:/");
            } else {
                modelAndView.setViewName("access-denied.html");
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletePost(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        postService.deletePost(id);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPost(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Post post = postService.getPost(id);
        System.out.println("POST ID z metody GET-------" + post.getPostId());
        modelAndView.addObject("post", post);
        modelAndView.setViewName("editPostForm");
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editPost(@Valid Post post, BindingResult bindingResult, ModelAndView modelAndView) {
        System.out.println("POST ID z met POST"+post.getPostId());
        postService.updatePost(post);

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
