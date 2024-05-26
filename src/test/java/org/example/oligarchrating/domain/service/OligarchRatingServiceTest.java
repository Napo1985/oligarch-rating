package org.example.oligarchrating.domain.service;

import org.example.oligarchrating.domain.dto.FinancialAssets;
import org.example.oligarchrating.domain.dto.Person;
import org.example.oligarchrating.domain.exception.NotFoundException;
import org.example.oligarchrating.domain.model.Oligarch;
import org.example.oligarchrating.domain.repository.OligarchRepository;
import org.example.oligarchrating.infrastructure.rest.ExternalApiServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OligarchRatingServiceTest {

    @Mock
    private OligarchRepository oligarchRepository;

    @Mock
    private ExternalApiServiceInterface externalApiService;

    @InjectMocks
    private OligarchRatingService oligarchRatingService;

    @BeforeEach
    void setUp() { //TODO is this needed
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEvaluateAndSavePerson() {
        when(externalApiService.evaluateCash(any(BigDecimal.class), anyString())).thenReturn(BigDecimal.valueOf(1));
        when(externalApiService.getBitcoinValue()).thenReturn(BigDecimal.valueOf(1));
        when(externalApiService.getOligarchThreshold()).thenReturn(BigDecimal.valueOf(1));

        Person person = new Person();
        person.setId(1L);
        person.setFirstName("John");
        person.setLastName("Doe");

        FinancialAssets financialAssets = new FinancialAssets();
        financialAssets.setCashAmount(BigDecimal.valueOf(10));
        financialAssets.setBitcoinAmount(5);
        financialAssets.setCurrency("usd");

        person.setFinancialAssets(financialAssets);

        when(oligarchRatingService.evaluateAndSavePerson(person)).thenReturn(new Oligarch(1L,"d","f",5L));

        when(oligarchRepository.findById(anyLong())).thenReturn(Optional.empty());
        Oligarch oligarch = oligarchRatingService.evaluateAndSavePerson(person);

        assertEquals(oligarch.getId(), person.getId());
        assertEquals(oligarch.getFirstName(), person.getFirstName());
        assertEquals(oligarch.getLastName(), person.getLastName());
    }

    @Test
    void testGetAllOligarchs() {
        // Mock the behavior of oligarchRepository.findAll()
        List<Oligarch> oligarchs = new ArrayList<>();
        oligarchs.add(new Oligarch(1L, "John", "Doe", 1000000L));
        oligarchs.add(new Oligarch(2L, "Jane", "Smith", 2000000L));
        when(oligarchRepository.findAll()).thenReturn(oligarchs);

        List<Oligarch> result = oligarchRatingService.getAllOligarchs();

        verify(oligarchRepository, times(1)).findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(oligarchs, result);
    }

    @Test
    void testGetOligarchById() {
        Oligarch oligarch = new Oligarch(1L, "John", "Doe", 1000000L);
        when(oligarchRepository.findById(1L)).thenReturn(Optional.of(oligarch));

        Optional<Oligarch> result = oligarchRatingService.getOligarchById(1L);

        verify(oligarchRepository, times(1)).findById(1L);

        assertTrue(result.isPresent());
        assertEquals(oligarch, result.get());
    }

    @Test
    void testGetOligarchByIdNotFound() {
        when(oligarchRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> oligarchRatingService.getOligarchById(2L));

        verify(oligarchRepository, times(1)).findById(2L);
    }

    @Test
    void testGetOligarchRank() {
        Long targetId = 1L;
        Oligarch targetOligarch = new Oligarch(1L, "John", "Doe", 1000L);
        List<Oligarch> allOligarchs = Arrays.asList(
                new Oligarch(2L, "Jane", "Smith", 2000L),
                targetOligarch,
                new Oligarch(3L, "Alice", "Johnson", 1500L),
                new Oligarch(1L, "John", "Doe", 1000L)
        );

        when(oligarchRepository.findById(targetId)).thenReturn(Optional.of(targetOligarch));
        when(oligarchRepository.findAll()).thenReturn(allOligarchs);

        int rank = oligarchRatingService.getOligarchRank(targetId);

        assertEquals(3, rank);
    }

    @Test
    void testGetOligarchRank_NotFound() {
        // Arrange
        Long targetId = 4L;
        when(oligarchRepository.findById(targetId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> oligarchRatingService.getOligarchRank(targetId));
    }
}