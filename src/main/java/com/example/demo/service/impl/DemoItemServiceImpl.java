package com.example.demo.service.impl;

import com.example.demo.model.DemoItem;
import com.example.demo.repository.DemoItemRepository;
import com.example.demo.service.DemoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DemoItemServiceImpl implements DemoItemService {

    private final DemoItemRepository demoItemRepository;

    @Autowired
    public DemoItemServiceImpl(DemoItemRepository demoItemRepository) {
        this.demoItemRepository = demoItemRepository;
    }

    @Override
    public DemoItem storeDemoItem(DemoItem demoItem) {
        return demoItemRepository.save(demoItem);
    }

    @Override
    public Optional<DemoItem> getDemoItem(Long id) {
        return demoItemRepository.findById(id);
    }
}
