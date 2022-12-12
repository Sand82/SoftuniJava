package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.view.RouteViewModel;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private RouteRepository routeRepository;

    private ModelMapper mapper;

    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper mapper) {
        this.routeRepository = routeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<RouteViewModel> findAllRoutesView() {
        return routeRepository.findAll().stream().map(r -> {

            RouteViewModel routeViewModel = mapper.map(r, RouteViewModel.class);

            if (r.getPictures().isEmpty()) {
                routeViewModel.setPictureUrl("/images/pic4.jpg");
            } else {
                routeViewModel.setPictureUrl(r.getPictures().stream().findFirst().get().getUrl());
            }

            return routeViewModel;

        }).toList();
    }
}
