package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
<<<<<<< HEAD
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.UserRepository;
=======
import com.codeup.springblog.repositories.PostRepository;
>>>>>>> f0e2ac6ff183b1be2232a202dc96b15974b2748f
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

<<<<<<< HEAD
    private final UserRepository userDao;

    public PostController(UserRepository userDao){
        this.userDao = userDao;
=======
    private final PostRepository postDao;

    public PostController(PostRepository postDao){
        this.postDao = postDao;
>>>>>>> f0e2ac6ff183b1be2232a202dc96b15974b2748f
    }

    @GetMapping("/posts")
    public String postsIndex(Model model){

        Post post1 = new Post("First Post", "Here is the first post", 1, userDao.getOne(1L));
        Post post2 = new Post("Second Post", "Here is the second post", 2, userDao.getOne(1L));
        Post post3 = new Post("Third Post", "Here is the third post", 3, userDao.getOne(1L));

        List<Post> postList = new ArrayList<>();
        postList.add(post1);
        postList.add(post2);
        postList.add(post3);


        model.addAttribute("title", "All Posts");
        model.addAttribute("posts", postList);


        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postView(@PathVariable long id, Model model){
        Post post = new Post("First Post", "Here is the first post.", 1, userDao.getOne(1L));
        model.addAttribute("title", "Single Post");
        model.addAttribute("post", post);
        model.addAttribute("id", id);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postForm(){
        return "Create a post here!";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "Creating a new post...";
    }
}
