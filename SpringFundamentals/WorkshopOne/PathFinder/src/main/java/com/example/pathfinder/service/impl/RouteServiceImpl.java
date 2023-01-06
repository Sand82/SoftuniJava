package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.bindings.RouteAddBindingModel;
import com.example.pathfinder.model.entities.Route;
import com.example.pathfinder.model.services.RouteServiceModel;
import com.example.pathfinder.model.view.RouteDetailsViewModel;
import com.example.pathfinder.model.view.RouteViewModel;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.service.CategoryService;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private RouteRepository routeRepository;
    private ModelMapper mapper;
    private UserService userService;
    private CategoryService categoryService;

    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper mapper, UserService userService, CategoryService categoryService) {
        this.routeRepository = routeRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.categoryService = categoryService;
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

    @Override
    public void createRoute(RouteAddBindingModel routeAddBindingModel) throws IOException {

        RouteServiceModel routeServiceModel = mapper.map(routeAddBindingModel, RouteServiceModel.class);
        routeServiceModel.setGpxCoordinates(new String(routeAddBindingModel.getGpxCoordinates().getBytes()));

        saveRoute(routeServiceModel);
    }

    @Override
    public RouteDetailsViewModel findRouteById(Long id) {

        Route route = routeRepository.findById(id).get();

        RouteDetailsViewModel routeDetailsViewModel = mapper.map(route, RouteDetailsViewModel.class);

        return routeDetailsViewModel;
    }

    private void saveRoute(RouteServiceModel routeServiceModel) {

        Route route = mapper.map(routeServiceModel, Route.class);
//
//        route.setAuthor(userService.getById(currentUser.getId()));
//        route.setCategories(routeServiceModel
//                .getCategories()
//                .stream()
//                .map(c -> categoryService.findCategoryByName(c))
//                .collect(Collectors.toSet()));

        routeRepository.save(route);
    }
}
