package org.example.oligarchrating.domain.service;

import org.example.oligarchrating.domain.entities.FinancialAssets;
import org.example.oligarchrating.domain.entities.OligarchEntity;
import org.example.oligarchrating.domain.entities.PersonEntity;
import org.example.oligarchrating.domain.exception.NotFoundException;
import org.example.oligarchrating.infrastructure.repository.model.Oligarch;
import org.example.oligarchrating.domain.repository.OligarchRepository;
import org.example.oligarchrating.infrastructure.rest.ExternalApiServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        when(externalApiService.evaluateCash(anyDouble(), anyString())).thenReturn(1D);
        when(externalApiService.getBitcoinValue()).thenReturn(1D);
        when(externalApiService.getOligarchThreshold()).thenReturn(1D);
        PersonEntity personEntity = new PersonEntity(1L,"John", "Doe", new FinancialAssets(10D,"usd",5L));
        when(oligarchRepository.findById(anyLong())).thenReturn(Optional.empty());


        OligarchEntity oligarch = oligarchRatingService.save(personEntity);

        assertEquals(oligarch.getId(), personEntity.getId());
        assertEquals(oligarch.getFirstName(), personEntity.getFirstName());
        assertEquals(oligarch.getLastName(), personEntity.getLastName());
    }

    @Test
    void testGetAllOligarchs() {
        // Mock the behavior of oligarchRepository.findAll()
        List<Oligarch> oligarchs = new ArrayList<>();
        oligarchs.add(new Oligarch(1L, "John", "Doe", 1000000L));
        oligarchs.add(new Oligarch(2L, "Jane", "Smith", 2000000L));
        when(oligarchRepository.findAll()).thenReturn(oligarchs);

        List<OligarchEntity> result = oligarchRatingService.getAllOligarchs();

        verify(oligarchRepository, times(1)).findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetOligarchById() {
        OligarchEntity oligarchEntity = new OligarchEntity(1L, "John", "Doe", 10L);
        Oligarch oligarch = new Oligarch(1L, "John", "Doe", 10L);
        when(oligarchRepository.findById(anyLong())).thenReturn(Optional.of(oligarch));

        OligarchEntity result = oligarchRatingService.getOligarchById(1L);

        assertTrue(result != null);
        assertEquals(oligarchEntity, result);
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
        Long targetId = 4L;
        when(oligarchRepository.findById(targetId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> oligarchRatingService.getOligarchRank(targetId));
    }
}