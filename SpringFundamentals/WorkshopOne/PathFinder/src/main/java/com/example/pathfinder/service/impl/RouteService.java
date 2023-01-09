package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.bindings.RouteAddBindingModel;
import com.example.pathfinder.model.view.RouteDetailsViewModel;
import com.example.pathfinder.model.view.RouteViewModel;

import java.io.IOException;
import java.util.List;

public interface RouteService {
    List<RouteViewModel> findAllRoutesView();

    void createRoute(RouteAddBindingModel routeAddBindingModel) throws IOException;

    RouteDetailsViewModel findRouteById(Long id);
}
