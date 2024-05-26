package org.example.oligarchrating.domain.repository;

import org.example.oligarchrating.domain.model.Oligarch;
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
    @Mock
    private ExternalApiServiceInterface externalApiService;

    @InjectMocks
    private OligarchRatingService oligarchService;

    @Test
    public void testFindById() {
        Long id = 1L;
        Oligarch oligarch = new Oligarch(id, "John", "Doe", 1000000000L);
        when(oligarchRepository.findById(id)).thenReturn(Optional.of(oligarch));

        Optional<Oligarch> result = oligarchService.getOligarchById(id);

        assertEquals(oligarch, result.orElse(null));
        verify(oligarchRepository, times(1)).findById(id);
    }

    @Test
    public void testFindAll() {
        List<Oligarch> oligarchs = List.of(
                new Oligarch(1L, "John", "Doe", 1000000000L),
                new Oligarch(2L, "Jane", "Doe", 2000000000L)
        );

        when(oligarchRepository.findAll()).thenReturn(oligarchs);

        List<Oligarch> result = oligarchService.getAllOligarchs();

        assertEquals(oligarchs.size(), result.size());
        assertEquals(oligarchs, result);

        verify(oligarchRepository, times(1)).findAll();
    }
}