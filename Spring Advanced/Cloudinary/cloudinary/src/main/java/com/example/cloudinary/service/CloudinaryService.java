package com.example.cloudinary.service;

import com.example.cloudinary.models.entities.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    CloudinaryImage upload(MultipartFile file) throws IOException;

    boolean delete(String publicId) throws IOException;

    void createPicture(MultipartFile picture, String title) throws IOException;
}
