package com.example.demo.controller;

import com.example.demo.generated.server.api.DemoItemApi;
import com.example.demo.generated.server.model.DemoItemDTO;
import com.example.demo.mapper.DemoItemMapper;
import com.example.demo.model.DemoItem;
import com.example.demo.service.DemoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class DemoItemController implements DemoItemApi {

    private final DemoItemService demoItemService;

    @Autowired
    public DemoItemController(DemoItemService demoItemService) {
        this.demoItemService = demoItemService;
    }


    @Override
    public ResponseEntity<DemoItemDTO> createDemoItem(DemoItemDTO demoItemDTO) {
        DemoItem storedDemoItem = demoItemService.storeDemoItem(DemoItemMapper.toEntity(demoItemDTO));
        return ResponseEntity.of(Optional.of(DemoItemMapper.toDto(storedDemoItem)));
    }

    @Override
    public ResponseEntity<DemoItemDTO> getDemoItem(BigDecimal id) {
        return ResponseEntity.of(demoItemService.getDemoItem(id.longValue())
                .map(DemoItemMapper::toDto));
    }
}
