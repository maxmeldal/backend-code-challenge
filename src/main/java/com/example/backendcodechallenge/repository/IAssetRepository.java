package com.example.backendcodechallenge.repository;

import com.example.backendcodechallenge.model.Asset;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//crud repository contains methods for create, read, readall, update and delete
@Repository
public interface IAssetRepository extends CrudRepository<Asset, UUID> {
}
