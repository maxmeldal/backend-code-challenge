package com.example.backendcodechallenge.repository;

import com.example.backendcodechallenge.model.Asset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class IAssetRepositoryTest {

    @Autowired
    IAssetRepository assetRepository;

    @Test
    public void createAsset(){
        Asset atest = new Asset("video", "breadmaker", "kitchen aid" ,"url");
        Asset a1 = assetRepository.save(atest);

        assertEquals(assetRepository.findById(a1.getUuid()).get().getTitle(), "breadmaker");
    }

    @Test
    public void findAsset(){
        Asset asset = assetRepository.findAll().iterator().next();
        Optional<Asset> assetByUuid = assetRepository.findById(asset.getUuid());
        assertTrue(assetByUuid.isPresent());
    }

    @Test
    public void findAssets(){
        Iterable<Asset> assets = assetRepository.findAll();
        int count = 0;
        for (Asset a : assets) {
            count++;
        }
        assertEquals(4, count);
    }

    @Test
    public void deleteAsset(){
        Asset asset = assetRepository.findAll().iterator().next();
        assetRepository.deleteById(asset.getUuid());
        Iterable<Asset> assets = assetRepository.findAll();
        int count = 0;
        for (Asset a : assets) {
            count++;
        }
        assertEquals(3, count);
    }
}