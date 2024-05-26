package org.example.oligarchrating.domain.repository;

import org.example.oligarchrating.domain.entities.OligarchEntity;
import org.example.oligarchrating.infrastructure.repository.model.Oligarch;
import org.example.oligarchrating.domain.service.OligarchRatingService;
import org.example.oligarchrating.infrastructure.rest.ExternalApiServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class OligarchRepositoryTest {

    @Mock
    private OligarchRepository oligarchRepository;

    @Test
    public void testSave() {
        Oligarch oligarch = new Oligarch(1L, "John", "Doe", 1000000000L);
        when(oligarchRepository.save(any())).thenReturn(oligarch);

        Oligarch result = oligarchRepository.save(any());
        assertInstanceOf(Oligarch.class, result);
    }

    @Test
    public void testFindById() {
        Oligarch oligarch = new Oligarch(1L, "John", "Doe", 1000000000L);
        when(oligarchRepository.findById(anyLong())).thenReturn(Optional.of(oligarch));

        Optional<Oligarch> result = oligarchRepository.findById(anyLong());
        assertEquals(oligarch, result.orElse(null));
    }

    @Test
    public void testFindAll() {
        List<Oligarch> oligarchs = List.of(
                new Oligarch(1L, "John", "Doe", 1000000000L),
                new Oligarch(2L, "Jane", "Doe", 2000000000L)
        );

        when(oligarchRepository.findAll()).thenReturn(oligarchs);
        List<Oligarch> result = oligarchRepository.findAll();

        assertEquals(oligarchs.size(), result.size());
        assertEquals(oligarchs, result);

    }
}