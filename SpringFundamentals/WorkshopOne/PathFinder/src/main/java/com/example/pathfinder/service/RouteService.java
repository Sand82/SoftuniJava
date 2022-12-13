package com.example.pathfinder.service;

import com.example.pathfinder.model.bindings.RouteAddBindingModel;
import com.example.pathfinder.model.entities.Route;
import com.example.pathfinder.model.view.RouteViewModel;

import java.io.IOException;
import java.util.List;

public interface RouteService {
    List<RouteViewModel> findAllRoutesView();

    void createRoute(RouteAddBindingModel routeAddBindingModel) throws IOException;

    Route findRouteById(Long id);
}
