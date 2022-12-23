package com.example.mobilelele.services;

import com.example.mobilelele.model.view.StatsView;

public interface StatsService {

    void onRequest();

    StatsView getStats();
}
