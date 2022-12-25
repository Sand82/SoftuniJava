package com.example.cloudinary.web;

import com.example.cloudinary.models.binding.PictureBindingModel;
import com.example.cloudinary.models.entities.Picture;
import com.example.cloudinary.service.CloudinaryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PictureController {

    private final CloudinaryService cloudinaryService;

    public PictureController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/pictures/add")
    public String addPicture() {

        return "add";
    }

    @PostMapping("/pictures/add")
    public String add(PictureBindingModel pictureBindingModel) throws IOException {

        cloudinaryService.createPicture(pictureBindingModel.getPicture(), pictureBindingModel.getTitle());

        return "redirect:all";
    }

    @ModelAttribute
    public PictureBindingModel pictureBindingModel(){
       return new PictureBindingModel();
    }

    @GetMapping("/pictures/all")
    public String all() {

        return "all";
    }
}
