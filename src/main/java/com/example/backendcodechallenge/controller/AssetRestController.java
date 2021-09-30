package com.example.backendcodechallenge.controller;

import com.example.backendcodechallenge.model.Asset;
import com.example.backendcodechallenge.repository.IAssetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api")
public class AssetRestController {

    //constructor injection
    IAssetRepository assetRepository;
    public AssetRestController(IAssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @GetMapping("/assets")
    public ResponseEntity<Iterable<Asset>> findAll(){
        //returns list of Assets and httpsstatus OK
        return ResponseEntity.status(HttpStatus.OK).body(assetRepository.findAll());
    }

    //findById mapping takes uuid
    @GetMapping("/assets/{uuid}")
    public ResponseEntity<Optional<Asset>> findByUuid (@PathVariable UUID uuid){
        //save asset in optional
        Optional<Asset> optionalAsset = assetRepository.findById(uuid);

        //check if present and return if is
        if (optionalAsset.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalAsset);
        }

        //if not present, return not_found status
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalAsset);
    }

    //create mapping takes asset object
    @PostMapping("/assets")
    public ResponseEntity<String> create (@RequestBody Asset asset){
        if (!asset.getType().equalsIgnoreCase("video") ||!asset.getType().equalsIgnoreCase("photo")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{'msg': 'asset type must be either \"video\" or \"photo\"'}");
        }

        //save new asset
        assetRepository.save(asset);

        //return status created
        return ResponseEntity.status(HttpStatus.CREATED).body("{'msg': 'asset " + asset.getUuid() + " created'}");
    }

    //update mapping takes uuid and asset object
    @PutMapping("/assets/{uuid}")
    public ResponseEntity<String> update (@PathVariable UUID uuid, @RequestBody Asset assetNew){
        if (!assetNew.getType().equalsIgnoreCase("video") ||!assetNew.getType().equalsIgnoreCase("photo")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{'msg': 'asset type must be either \"video\" or \"photo\"'}");
        }

        //finds asset and saves in optional
        Optional<Asset> optionalAsset = assetRepository.findById(uuid);

        //if uuid doesn't exist return not fount status
        if(optionalAsset.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg': 'asset " + uuid + " not found'}");
        }

        //save the new asset
        assetNew.setUuid(uuid);
        assetRepository.save(assetNew);

        //return status ok
        return ResponseEntity.status(HttpStatus.OK).body("{'msg': 'asset " + uuid + " updated'}");
    }

    //delete mapping take uuid
    @DeleteMapping("/assets/{uuid}")
    public ResponseEntity<String> delete (@PathVariable UUID uuid){
        //finds asset and saves in optional
        Optional<Asset> optionalAsset = assetRepository.findById(uuid);

        //if uuid doesn't exist return not fount status
        if(optionalAsset.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg': 'asset " + uuid + " not found'}");
        }

        //delete asset
        assetRepository.deleteById(uuid);

        //return status ok
        return ResponseEntity.status(HttpStatus.OK).body("{'msg': 'asset " + uuid + " deleted'}");
    }
}
