package com.example.backendcodechallenge.controller;

import com.example.backendcodechallenge.model.Asset;
import com.example.backendcodechallenge.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class CLIController {

    //field injection
    @Autowired
    AssetService assetService;

    //homepage, displays all assets
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("assets", assetService.findAll());
        return "index";
    }

    //--METHODS FOR CREATING NEW ASSET--//
    @GetMapping("/create")
    public String create(Model model){
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Asset asset){
        assetService.create(asset);
        return "redirect:/";
    }
    //--METHODS FOR CREATING NEW ASSET END--//

    //--METHODS FOR UPDATING ASSET--//
    @GetMapping("/update/{uuid}")
    public String update(@PathVariable("uuid") UUID uuid, Model model){
        model.addAttribute("asset", assetService.findById(uuid));
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Asset asset){
        assetService.update(asset);
        return "redirect:/";
    }
    //--METHODS FOR UPDATING ASSET END--//


    //delete button
    @GetMapping("/delete/{uuid}")
    public String delete(@PathVariable("uuid") UUID uuid){
        assetService.deleteById(uuid);
        return "redirect:/";
    }
}
