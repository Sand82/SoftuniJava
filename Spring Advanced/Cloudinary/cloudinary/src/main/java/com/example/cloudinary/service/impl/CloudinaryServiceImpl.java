package com.example.cloudinary.service.impl;

import com.cloudinary.Cloudinary;
import com.example.cloudinary.models.entities.Picture;
import com.example.cloudinary.repositories.PictureRepository;
import com.example.cloudinary.service.CloudinaryImage;
import com.example.cloudinary.service.CloudinaryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";
    private final Cloudinary cloudinary;
    private final PictureRepository pictureRepository;

    public CloudinaryServiceImpl(Cloudinary cloudinary, PictureRepository pictureRepository) {
        this.cloudinary = cloudinary;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public CloudinaryImage upload(MultipartFile multipartFile) throws IOException {

        File tempFile = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);

        @SuppressWarnings("unchecked")
        Map<String, String> uploadResult = cloudinary
                .uploader()
                .upload(tempFile, Map.of());

        String url = uploadResult.getOrDefault(URL, "https://thumbs.dreamstime.com/b/page-not-found-illustration-concept-signboard-rabbit-looking-confused-vector-web-template-121900149.jpg");

        String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

        var result = new CloudinaryImage()
                .setPublicId(publicId).setUrl(url);

        tempFile.delete();

        return result;
    }

    @Override
    public boolean delete(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId, Map.of());

        } catch (IOException e) {

            return false;
        }

        return true;
    }

    @Transactional
    @Override
    public void createPicture(MultipartFile file, String title) throws IOException {
        final CloudinaryImage uploader = upload(file);

        Picture picture = new Picture()
                .setPublicId(uploader.getPublicId())
                .setTitle(title)
                .setUrl(uploader.getUrl());

        pictureRepository.save(picture);
    }
}
