package com.acoss.webae.service.impl;

import com.acoss.webae.service.MetadataService;
import com.acoss.webae.domain.Metadata;
import com.acoss.webae.repository.MetadataRepository;
import com.acoss.webae.service.dto.MetadataDTO;
import com.acoss.webae.service.mapper.MetadataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Metadata.
 */
@Service
@Transactional
public class MetadataServiceImpl implements MetadataService {

    private final Logger log = LoggerFactory.getLogger(MetadataServiceImpl.class);

    private final MetadataRepository metadataRepository;

    private final MetadataMapper metadataMapper;

    public MetadataServiceImpl(MetadataRepository metadataRepository, MetadataMapper metadataMapper) {
        this.metadataRepository = metadataRepository;
        this.metadataMapper = metadataMapper;
    }

    /**
     * Save a metadata.
     *
     * @param metadataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MetadataDTO save(MetadataDTO metadataDTO) {
        log.debug("Request to save Metadata : {}", metadataDTO);
        Metadata metadata = metadataMapper.toEntity(metadataDTO);
        metadata = metadataRepository.save(metadata);
        return metadataMapper.toDto(metadata);
    }

    /**
     * Get all the metadata.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MetadataDTO> findAll() {
        log.debug("Request to get all Metadata");
        return metadataRepository.findAll().stream()
            .map(metadataMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one metadata by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MetadataDTO findOne(Long id) {
        log.debug("Request to get Metadata : {}", id);
        Metadata metadata = metadataRepository.findOne(id);
        return metadataMapper.toDto(metadata);
    }

    /**
     * Delete the metadata by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Metadata : {}", id);
        metadataRepository.delete(id);
    }
}
