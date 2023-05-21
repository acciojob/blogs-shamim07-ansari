package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        Optional<User> userOptional =  userRepository.findById(userId);
        if(!userOptional.isPresent()) {
            return null;
        }
        User user = userOptional.get();
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);

        user.getBlogList().add(blog);
        userRepository.save(user);
        blogRepository.save(blog);
        return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository.deleteById(blogId);
    }
}
