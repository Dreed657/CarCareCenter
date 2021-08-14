package com.local.carcarecenter.service.car;

import com.local.carcarecenter.dto.car.CarViewModel;
import com.local.carcarecenter.exception.EntityNotFoundExecution;
import com.local.carcarecenter.model.Car;
import com.local.carcarecenter.model.enums.EngineType;
import com.local.carcarecenter.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@TestPropertySource(locations= "classpath:application.properties")
class CarServiceImplTest {

    @Autowired
    private CarRepository repo;

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private ModelMapper mapper;

    private final List<Car> cars = List.of(
            new Car("1FAFP56U77A162750", "POLARIS", "SPORTSMAN 300", 2009, EngineType.PETROL),
            new Car("2GCEC13T951106659", "POLARIS", "RANGER 800 CREW EPS", 2012, EngineType.PETROL),
            new Car("3N1CN7AP9CL942741", "SUZUKI", "DR-Z400S", 2004, EngineType.PETROL)
    );

    @BeforeEach
    void setUp() {
        repo.saveAll(cars);
    }

    @AfterEach
    void tearDown() {
        repo.deleteAll(repo.findAll());
    }

    @Test
    void getById() throws EntityNotFoundExecution {
        // Arrange
        var car = cars.get(0);
        var id = car.getId();

        // Act
        var expected = mapper.map(car, CarViewModel.class);
        var actual = carService.getById(id);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getByIdThrowsExceptionWhenNotFound() {
        assertThatThrownBy(() -> {
            carService.getById(100L);
        }).isInstanceOf(EntityNotFoundExecution.class)
                .hasMessageContaining("Car was not found!");
    }

    @Test
    void getAll() {
        // Act
        var expected = cars
                .stream()
                .map(user -> mapper.map(user, CarViewModel.class))
                .collect(Collectors.toList());

        var actual = carService.getAll();

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}