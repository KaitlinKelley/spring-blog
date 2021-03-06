package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;

import com.codeup.springblog.services.EmailService;
import com.codeup.springblog.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;
    private final UserService userService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService, UserService userService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
        this.userService = userService;
    }

    @GetMapping("/posts")
    public String postsIndex(Model model){
       model.addAttribute("posts", postDao.findAll());
       return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postView(@PathVariable long id, Model model){
        Post post = postDao.getOne(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEditPostForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post){
        User user = userService.getLoggedInUser();
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/create")
    public String postForm(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post){
        // Will throw if no users in the db!
        // In the future, we will get the logged in user
        User user = userService.getLoggedInUser();
        post.setUser(user);

        Post savedPost = postDao.save(post);

        //send an email when an ad is successfully saved
        String subject = "New Post Created: " + savedPost.getTitle();
        String body = "Dear " + savedPost.getUser().getUsername()
                + ". Thank you for creating a post. Your post id is "
                + savedPost.getId();

        emailService.prepareAndSend(savedPost, subject, body);
        return "redirect:/posts";
    }
}
