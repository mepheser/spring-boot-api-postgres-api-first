package com.example.demo.repository;

import com.example.demo.AbstractDatabaseUnitTest;
import com.example.demo.model.DemoItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class DemoItemRepositoryTests extends AbstractDatabaseUnitTest {

	@Autowired
	private DemoItemRepository demoItemRepository;

	@Test
	void store() {
		DemoItem item = new DemoItem();
		item.setTitle("title");

		DemoItem savedDemoItem = demoItemRepository.save(item);

		Optional<DemoItem> loadedDemoItem = demoItemRepository.findById(savedDemoItem.getId());
		Assertions.assertTrue(loadedDemoItem.isPresent());
		Assertions.assertNotNull(loadedDemoItem.get().getId());
		Assertions.assertEquals(item.getTitle(), loadedDemoItem.get().getTitle());
	}

}
