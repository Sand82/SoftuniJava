package com.example.mobilelele.web;

import com.example.mobilelele.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
public class BrandsController {

    private BrandService brandService;

    @Autowired
    public BrandsController(BrandService brandService) {

        this.brandService = brandService;
    }

    @GetMapping("/all")
    private String allBrands(Model model){

        model.addAttribute("allBrands", null);

        return "brands";
    }
}
