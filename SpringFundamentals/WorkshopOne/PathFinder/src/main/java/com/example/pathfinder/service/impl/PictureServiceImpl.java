package com.example.pathfinder.service.impl;

import com.example.pathfinder.repository.PictureRepository;
import com.example.pathfinder.service.PictureService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<String> findAllUrls() {

        List<String> pictures = pictureRepository.findAllUrls();
        return pictures;
    }
}
