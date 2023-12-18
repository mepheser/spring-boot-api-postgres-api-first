package com.example.demo.mapper;

import com.example.demo.generated.server.model.DemoItemDTO;
import com.example.demo.model.DemoItem;

import java.math.BigDecimal;

public final class DemoItemMapper {

    public static DemoItem toEntity(DemoItemDTO dto) {
        DemoItem demoItem = new DemoItem();
        demoItem.setTitle(dto.getTitle());
        return demoItem;
    }

    public static DemoItemDTO toDto(DemoItem entity) {
        DemoItemDTO dto = new DemoItemDTO();
        dto.setId(BigDecimal.valueOf(entity.getId()));
        dto.setTitle(entity.getTitle());
        return dto;
    }

}
