package com.example.demo.service;

import com.example.demo.model.DemoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DemoItemService {

    DemoItem storeDemoItem(DemoItem demoItem);

    Optional<DemoItem> getDemoItem(Long id);

}
