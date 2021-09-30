package com.example.backendcodechallenge.service;

import com.example.backendcodechallenge.model.Asset;
import com.example.backendcodechallenge.repository.IAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssetService {

    @Autowired
    IAssetRepository assetRepository;

    public Asset create (Asset asset){
        return assetRepository.save(asset);
    }

    //method takes optional and check if exists before converting to Asset object and returning
    public Asset findById (UUID uuid){
        Optional<Asset> optionalAsset = assetRepository.findById(uuid);
        if (optionalAsset.isEmpty()){
            throw new RuntimeException(String.format("Asset with id: (%) not found.", uuid));
        }
        return optionalAsset.get();
    }

    //converts Iterable to List and returns
    public List<Asset> findAll(){
        List<Asset> assets = new ArrayList<>();
        for (Asset asset : assetRepository.findAll()) {
            assets.add(asset);
        }
        return assets;
    }

    //checks if given asset exists before updating
    public Asset update (Asset asset){
        List<UUID> uuids = new ArrayList<>();
        for (Asset a: assetRepository.findAll()) {
            uuids.add(a.getUuid());
        }

        if(!uuids.contains(asset.getUuid())){
            throw new RuntimeException(String.format("Asset with id: (%) not found", asset.getUuid()));
        }
        return assetRepository.save(asset);
    }

    public void deleteById (UUID uuid){
        assetRepository.deleteById(uuid);
    }

}
