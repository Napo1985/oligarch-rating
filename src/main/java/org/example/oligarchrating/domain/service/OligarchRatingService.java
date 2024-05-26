package org.example.oligarchrating.domain.service;

import org.example.oligarchrating.domain.entities.OligarchEntity;
import org.example.oligarchrating.domain.entities.PersonEntity;
import org.example.oligarchrating.domain.exception.DuplicationException;
import org.example.oligarchrating.domain.exception.NotFoundException;
import org.example.oligarchrating.infrastructure.repository.model.Oligarch;
import org.example.oligarchrating.domain.repository.OligarchRepository;
import org.example.oligarchrating.infrastructure.rest.ExternalApiServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OligarchRatingService {

    @Autowired
    private OligarchRepository oligarchRepository;

    @Autowired
    private ExternalApiServiceInterface externalApiService;

    public OligarchEntity save(PersonEntity personEntity) {

        Double cashValue = externalApiService.evaluateCash(personEntity.getFinancialAssets().getCashAmount(), personEntity.getFinancialAssets().getCurrency());
        Double bitcoinValue = externalApiService.getBitcoinValue();
        Double oligarchThreshold = externalApiService.getOligarchThreshold();
        Double totalAssetsValue = cashValue + (bitcoinValue * personEntity.getFinancialAssets().getBitcoinAmount());

        Oligarch oligarch = null;
        if (totalAssetsValue.compareTo(oligarchThreshold) > 0) {
            Optional<Oligarch> resultOligarch = oligarchRepository.findById(personEntity.getId());
            if (resultOligarch.isPresent()) {
                throw new DuplicationException("id:" + personEntity.getId() + " already found");
            }

            oligarch = new Oligarch(personEntity.getId(), personEntity.getFirstName(), personEntity.getLastName(), totalAssetsValue.longValue());
            oligarchRepository.save(oligarch);
        }
        return new OligarchEntity(oligarch.getId(), oligarch.getFirstName(), oligarch.getLastName(), oligarch.getAssetsValue());
    }

    public List<OligarchEntity> getAllOligarchs() {
        return oligarchRepository.findAll().stream()
                .map(oligarch -> new OligarchEntity(oligarch.getId(), oligarch.getFirstName(), oligarch.getLastName(), oligarch.getAssetsValue()))
                .collect(Collectors.toList());
    }

    public OligarchEntity getOligarchById(Long id) {
        Oligarch oligarch = oligarchRepository.findById(id).orElseThrow(() -> new NotFoundException("id:" + id + " not found"));
        return new OligarchEntity(oligarch.getId(), oligarch.getFirstName(), oligarch.getLastName(), oligarch.getAssetsValue());
    }

    public int getOligarchRank(Long id) {
        Oligarch oligarch = oligarchRepository.findById(id).orElseThrow(() -> new NotFoundException("id:" + id + " not found"));
        List<Oligarch> sortedOligarchs = getSortedOligarchs();
        return findRank(sortedOligarchs, oligarch);
    }

    private List<Oligarch> getSortedOligarchs() {
        return oligarchRepository.findAll().stream()
                .sorted((o1, o2) -> o2.getAssetsValue().compareTo(o1.getAssetsValue()))
                .toList();
    }

    private int findRank(List<Oligarch> sortedOligarchs, Oligarch targetOligarch) {
        int rank = 1;
        for (Oligarch oligarch : sortedOligarchs) {
            if (oligarch.getAssetsValue() > targetOligarch.getAssetsValue()) {
                rank++;
            } else if (oligarch.getAssetsValue().equals(targetOligarch.getAssetsValue())) {
                break;
            }
        }
        return rank;
    }
}
