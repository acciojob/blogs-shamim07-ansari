package com.driver.services;

import com.driver.controller.ImageController;
import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository;
    @Autowired
    ImageRepository imageRepository;

    public Image addImage(Integer blogId, String description, String dimensions){
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if(!blogOptional.isPresent()) {
            return null;
        }
        Blog blog = blogOptional.get();
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        blog.getImageList().add(image);
        blogRepository.save(blog);
        imageRepository.save(image);
        return image;
    }

    public void deleteImage(Integer id){
        Optional<Image> imageOptional = imageRepository.findById(id);
        if(!imageOptional.isPresent()) {
            return;
        }
        imageRepository.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Optional<Image> imageOptional = imageRepository.findById(id);
        if(!imageOptional.isPresent()) {
            return 0;
        }
        int count = 0;
        Image image = imageOptional.get();
        if(image.getDimensions().equals(screenDimensions)) {
            count++;
        }
        return count;
    }
}
