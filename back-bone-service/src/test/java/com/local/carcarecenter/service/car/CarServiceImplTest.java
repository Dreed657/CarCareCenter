package com.local.carcarecenter.service.car;

import com.local.carcarecenter.dto.car.CarViewModel;
import com.local.carcarecenter.exception.EntityNotFoundExecution;
import com.local.carcarecenter.model.Car;
import com.local.carcarecenter.model.enums.EngineType;
import com.local.carcarecenter.repository.CarRepository;
import org.aspectj.lang.annotation.Before;
import org.hibernate.annotations.NotFound;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository mockRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private final ModelMapper mapper = new ModelMapper();

    private final List<Car> cars = List.of(
            new Car(1, "1FAFP56U77A162750", "POLARIS", "SPORTSMAN 300", 2009, EngineType.PETROL, new Date(), null),
            new Car(2, "2GCEC13T951106659", "POLARIS", "RANGER 800 CREW EPS", 2012, EngineType.PETROL, new Date(), null),
            new Car(3, "3N1CN7AP9CL942741", "SUZUKI", "DR-Z400S", 2004, EngineType.PETROL, new Date(), null)
    );

    public CarServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById() throws EntityNotFoundExecution {
        // Arrange
        var car = cars.get(0);
        var id = car.getId();
        Mockito.when(mockRepository.findById(id)).thenReturn(Optional.of(car));

        // Act
        var expected = mapper.map(car, CarViewModel.class);
        var actual = carService.getById(id);

        // Assert
        assertThat(actual).isEqualTo(expected);
        Mockito.verify(mockRepository).getById(id);
    }

    @Test
    void getByIdThrowsExceptionWhenNotFound() {
        assertThatThrownBy(() -> {
            carService.getById(100);
        }).isInstanceOf(EntityNotFoundExecution.class)
                .hasMessageContaining("Car was not found!");
    }

    @Test
    void getAll() {
        // Arrange
        Mockito.when(mockRepository.findAll()).thenReturn(cars);

        // Act
        var expected = cars
                .stream()
                .map(user -> mapper.map(user, CarViewModel.class))
                .collect(Collectors.toList());

        var actual = carService.getAll();

        // Assert
        assertThat(actual).isEqualTo(expected);
        Mockito.verify(mockRepository).findAll();
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