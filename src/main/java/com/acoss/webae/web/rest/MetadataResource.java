package com.acoss.webae.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.acoss.webae.service.MetadataService;
import com.acoss.webae.web.rest.errors.BadRequestAlertException;
import com.acoss.webae.web.rest.util.HeaderUtil;
import com.acoss.webae.service.dto.MetadataDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

import org.json.*;

/**
 * REST controller for managing Metadata.
 */
@RestController
@RequestMapping("/api")
public class MetadataResource {

    private final Logger log = LoggerFactory.getLogger(MetadataResource.class);

    private static final String ENTITY_NAME = "metadata";

    private final MetadataService metadataService;

    public MetadataResource(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    /**
     * POST  /metadata : Create a new metadata.
     *
     * @param metadataDTO the metadataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new metadataDTO, or with status 400 (Bad Request) if the metadata has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/metadata")
    @Timed
    public ResponseEntity<MetadataDTO> createMetadata(@RequestBody MetadataDTO metadataDTO) throws URISyntaxException {
        log.debug("REST request to save Metadata : {}", metadataDTO);
        if (metadataDTO.getId() != null) {
            throw new BadRequestAlertException("A new metadata cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MetadataDTO result = metadataService.save(metadataDTO);
        return ResponseEntity.created(new URI("/api/metadata/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /metadata : Updates an existing metadata.
     *
     * @param metadataDTO the metadataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated metadataDTO,
     * or with status 400 (Bad Request) if the metadataDTO is not valid,
     * or with status 500 (Internal Server Error) if the metadataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/metadata")
    @Timed
    public ResponseEntity<MetadataDTO> updateMetadata(@RequestBody MetadataDTO metadataDTO) throws URISyntaxException {
        log.debug("REST request to update Metadata : {}", metadataDTO);
        if (metadataDTO.getId() == null) {
            return createMetadata(metadataDTO);
        }
        MetadataDTO result = metadataService.save(metadataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, metadataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /metadata : get all the metadata.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of metadata in body
     */
    @GetMapping("/metadata")
    @Timed
    public List<MetadataDTO> getAllMetadata() {
        log.debug("REST request to get all Metadata");
        return metadataService.findAll();
        }

    /**
     * GET  /metadata/:id : get the "id" metadata.
     *
     * @param id the id of the metadataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the metadataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/metadata/{id}")
    @Timed
    public ResponseEntity<MetadataDTO> getMetadata(@PathVariable Long id) {
        log.debug("REST request to get Metadata : {}", id);
        MetadataDTO metadataDTO = metadataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(metadataDTO));
    }

    /**
     * DELETE  /metadata/:id : delete the "id" metadata.
     *
     * @param id the id of the metadataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/metadata/{id}")
    @Timed
    public ResponseEntity<Void> deleteMetadata(@PathVariable Long id) {
        log.debug("REST request to delete Metadata : {}", id);
        metadataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/metadata/ace")
    @Timed
    public String getMetadataACE() throws JSONException {
        String s = "{\n" +
            "  \"title\" : \"ACE\",\n" +
            "  \"type\" : \"object\",\n" +
            "  \"descripion\" : \"ACE\",\n" +
            "  \"properties\" : {\n" +
            "    \"E76.1\" : {\n" +
            "      \"title\" : \"Code APRM\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : 140,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}€@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\n" +
            "      \"xpath\" : \"E76.1\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 0,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E76\" : {\n" +
            "      \"title\" : \"Activité principale artisanale\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : 0,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"\",\n" +
            "      \"xpath\" : \"E76\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 0,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E79\" : {\n" +
            "      \"title\" : \"Origine de la modification d'activité\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : 0,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"\",\n" +
            "      \"xpath\" : \"E79\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 0,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E78\" : {\n" +
            "      \"title\" : \"L'activité principale de cet établissement devient celle de l'entreprise\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : 1,\n" +
            "      \"minLength\" : 1,\n" +
            "      \"pattern\" : \"\",\n" +
            "      \"xpath\" : \"E78\",\n" +
            "      \"business_type\" : \"OUI_NON\",\n" +
            "    \"oneOf\": [\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"O\"\n" +
            "      ],\n" +
            "      \"description\": \"Oui\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"N\"\n" +
            "      ],\n" +
            "      \"description\": \"Non\"\n" +
            "    }\n" +
            "    ],\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 3,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E79.1\" : {\n" +
            "      \"title\" : \"Code modification activité\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : -1,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}€@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\n" +
            "      \"xpath\" : \"E79.1\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 0,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E80\" : {\n" +
            "      \"title\" : \"Libellé de l'activité spécifique pour le RSI\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : 300,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}€@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\n" +
            "      \"xpath\" : \"E80\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 5,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E79.2\" : {\n" +
            "      \"title\" : \"Autre modification activité\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : 20,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}€@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\n" +
            "      \"xpath\" : \"E79.2\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 1,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E71\" : {\n" +
            "      \"title\" : \"Activité la plus importante \",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : 140,\n" +
            "      \"minLength\" : 1,\n" +
            "      \"pattern\" : \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}€@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\n" +
            "      \"xpath\" : \"E71\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 1,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E76.2\" : {\n" +
            "      \"title\" : \"Code aprm activité principale artisanale\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : 6,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}€@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\n" +
            "      \"xpath\" : \"E76.2\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 1,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E70\" : {\n" +
            "      \"title\" : \"Activité(s) exercée(s)\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : 840,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}€@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\n" +
            "      \"xpath\" : \"E70\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 0,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    }\n" +
            "  },\n" +
            "  \"required\" : [ \"E71\", \"E76.1\", \"E79\" ],\n" +
            "  \"defaults\" : null\n" +
            "}";

        JSONObject obj = new JSONObject(s);
        return s;
    }

    @GetMapping("/metadata/cae")
    @Timed
    public String getMetadataCAE() throws JSONException {
        String s = "{\n" +
            "  \"title\" : \"CAE\",\n" +
            "  \"type\" : \"object\",\n" +
            "  \"descripion\" : \"CAE\",\n" +
            "  \"properties\" : {\n" +
            "    \"E73\" : {\n" +
            "      \"title\" : \"Condition de l'activité générale\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : 0,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"\",\n" +
            "      \"xpath\" : \"E73\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 0,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E73.11\" : {\n" +
            "      \"title\" : \"Périodes activité\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 2,\n" +
            "      \"maxLength\" : 0,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"\",\n" +
            "      \"xpath\" : \"E73.11\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \" \",\n" +
            "      \"order\" : 0,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E75.1\" : {\n" +
            "      \"title\" : \"Code nature des activités\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : -1,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"[\\\\d]*\",\n" +
            "      \"xpath\" : \"E75.1\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 0,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E75.2\" : {\n" +
            "      \"title\" : \"Autre nature d activités\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : 50,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}€@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\n" +
            "      \"xpath\" : \"E75.2\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 1,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E73.1\" : {\n" +
            "      \"title\" : \"Permanence activité\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : -1,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}€@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\n" +
            "      \"xpath\" : \"E73.1\",\n" +
            "      \"business_type\" : \"PERMANENCE_ACTIVITE\",\n" +
            "    \"oneOf\": [\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"P\"\n" +
            "      ],\n" +
            "      \"description\": \"Permanente\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"S\"\n" +
            "      ],\n" +
            "      \"description\": \"Saisonnière\"\n" +
            "    }\n" +
            "    ],\n" +
            "      \"condition\" : \" \",\n" +
            "      \"order\" : 0,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E75\" : {\n" +
            "      \"title\" : \"Nature des activités\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : 0,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"\",\n" +
            "      \"xpath\" : \"E75\", \n" +
            "      \"business_type\" : \"NATURE_ACTIVITE\",\n" +
            "    \"oneOf\": [\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"4\"\n" +
            "      ],\n" +
            "      \"description\": \"Fabrication, production\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"9\"\n" +
            "      ],\n" +
            "      \"description\": \"Commerce de gros\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"10\"\n" +
            "      ],\n" +
            "      \"description\": \"Commerce de détail en magasin\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"14\"\n" +
            "      ],\n" +
            "      \"description\": \"Bâtiment, travaux publics\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"16\"\n" +
            "      ],\n" +
            "      \"description\": \"Commerce de détail sur marché\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"17\"\n" +
            "      ],\n" +
            "      \"description\": \"Commerce de détail sur internet\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"21\"\n" +
            "      ],\n" +
            "      \"description\": \"Location de terrains et autres biens immobiliers\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"22\"\n" +
            "      ],\n" +
            "      \"description\": \"Promotion immobilière de bureaux\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"23\"\n" +
            "      ],\n" +
            "      \"description\": \"Promotion immobilière de logements\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"24\"\n" +
            "      ],\n" +
            "      \"description\": \"Promotion immobilière d'autre batiments\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"25\"\n" +
            "      ],\n" +
            "      \"description\": \"Réalisation de programme de construction\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"26\"\n" +
            "      ],\n" +
            "      \"description\": \"Support patrimoine familial immo sans act de loc\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"enum\": [\n" +
            "      \"99\"\n" +
            "      ],\n" +
            "      \"description\": \"Autre\"\n" +
            "    }\n" +
            "    ],\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 0,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E73.2\" : {\n" +
            "      \"title\" : \"Non sédentarité\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : 1,\n" +
            "      \"minLength\" : 1,\n" +
            "      \"pattern\" : \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}€@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\n" +
            "      \"xpath\" : \"E73.2\",\n" +
            "      \"business_type\" : \"NON_SEDENTARITE\",\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 2,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E73.111\" : {\n" +
            "      \"title\" : \"Date début période activité\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : \"date-time\",\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : -1,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"....-..-..\",\n" +
            "      \"xpath\" : \"E73.111\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"Si E73.1 = S\",\n" +
            "      \"order\" : 0,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E73.112\" : {\n" +
            "      \"title\" : \"Date fin période activité\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : \"date-time\",\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : -1,\n" +
            "      \"minLength\" : 0,\n" +
            "      \"pattern\" : \"....-..-..\",\n" +
            "      \"xpath\" : \"E73.112\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"Si E73.1 = S\",\n" +
            "      \"order\" : 1,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    },\n" +
            "    \"E75.3\" : {\n" +
            "      \"title\" : \"Libellé de pays\",\n" +
            "      \"type\" : \"string\",\n" +
            "      \"format\" : null,\n" +
            "      \"maxItems\" : 1,\n" +
            "      \"maxLength\" : 5,\n" +
            "      \"minLength\" : 5,\n" +
            "      \"pattern\" : \"[\\\\d]*\",\n" +
            "      \"xpath\" : \"E75.3\",\n" +
            "      \"business_type\" : \"\",\n" +
            "      \"condition\" : \"\",\n" +
            "      \"order\" : 2,\n" +
            "      \"read_only\" : false,\n" +
            "      \"show\" : true\n" +
            "    }\n" +
            "  },\n" +
            "  \"required\" : [ \"E73\", \"E73.1\", \"E75\", \"E75.1\", \"E75.2\" ],\n" +
            "  \"defaults\" : null\n" +
            "}";

        JSONObject obj = new JSONObject(s);
        return s;
    }

    @GetMapping("/metadata/adf")
    @Timed
    public String getMetadataADF() throws JSONException {
        String s = "{\r\n  \"title\": \"ADF\",\r\n  \"type\": \"object\",\r\n  \"descripion\": \"ADF\",\r\n  \"properties\": {\r\n    \"C37_3\": {\r\n      \"title\": \"Code officiel g\u00E9ographique D\u00E9partement commune Ou code pays si \u00E0  l'\u00E9tranger\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": -1,\r\n      \"minLength\": 0,\r\n      \"pattern\": \"((2A|2B|[0-2]{1}[1-9]{1}|10|[3-8]{1}\\\\d{1}|[9]{1}[0-5]{1})\\\\d{3})|(97[1-8]{1}\\\\d{2})|(98[4-9]\\\\d{2})|(99[1-5]\\\\d{2})|99998\",\r\n      \"xpath\": \"C37_3\",\r\n      \"business_type\": \"CODE_GEO\",\r\n      \"condition\": \"\",\r\n      \"order\": 0,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C39_1\": {\r\n      \"title\": \"Num\u00E9ro de t\u00E9l\u00E9phone pour rel. Admin.\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 2,\r\n      \"maxLength\": 14,\r\n      \"minLength\": 1,\r\n      \"pattern\": \"[\\\\d]*\",\r\n      \"xpath\": \"C39_1\",\r\n      \"business_type\": \"\",\r\n      \"condition\": \"Si C37 = DP\",\r\n      \"order\": 0,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C37_4\": {\r\n      \"title\": \"Code Rivoli de la voie ou lieu-dit\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": -1,\r\n      \"minLength\": 0,\r\n      \"pattern\": \"((\\\\p{L}{1})|(\\\\d{1}))(\\\\d{3})\",\r\n      \"xpath\": \"C37_4\",\r\n      \"business_type\": \"\",\r\n      \"condition\": \"\",\r\n      \"order\": 1,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C39_2\": {\r\n      \"title\": \"Num\u00E9ro de t\u00E9l\u00E9copie pour rel. Admin.\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 14,\r\n      \"minLength\": 1,\r\n      \"pattern\": \"[\\\\d]*\",\r\n      \"xpath\": \"C39_2\",\r\n      \"business_type\": \"\",\r\n      \"condition\": \"Si C37 = DP\",\r\n      \"order\": 1,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C37\": {\r\n      \"title\": \"Adresse de correspondance \",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 0,\r\n      \"minLength\": 0,\r\n      \"pattern\": \"\",\r\n      \"xpath\": \"C37\",\r\n      \"business_type\": \"\",\r\n      \"oneOf\": [\r\n        {\r\n          \"enum\": [\r\n            \"DP\"\r\n          ],\r\n          \"description\": \"Domicile personnel\"\r\n        },\r\n        {\r\n          \"enum\": [\r\n            \"AP\"\r\n          ],\r\n          \"description\": \"Adresse Professionnelle\"\r\n        },\r\n        {\r\n          \"enum\": [\r\n            \"AU\"\r\n          ],\r\n          \"description\": \"Autre\"\r\n        }\r\n      ],\r\n      \"condition\": \"\",\r\n      \"order\": 0,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C37_5\": {\r\n      \"title\": \"Num\u00E9ro dans la voie\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 9,\r\n      \"minLength\": 0,\r\n      \"pattern\": \"[\\\\d\\\\sAa\u00E0\\\\-]*\",\r\n      \"xpath\": \"C37_5\",\r\n      \"business_type\": \"\",\r\n      \"condition\": \"Si C37 = AU\",\r\n      \"order\": 2,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C39_3\": {\r\n      \"title\": \"Adresse mel pour rel. Admin\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 80,\r\n      \"minLength\": 0,\r\n      \"pattern\": \"[\\\\p{L}\\\\p{N}_\\\\-]+(\\\\.[\\\\p{L}\\\\p{N}_\\\\-]+)*@[\\\\p{L}\\\\p{N}_\\\\-]+(\\\\.[\\\\p{L}\\\\p{N}_\\\\-]+)+\",\r\n      \"xpath\": \"C39_3\",\r\n      \"business_type\": \"\",\r\n      \"condition\": \"Si C37 = DP\",\r\n      \"order\": 2,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C36\": {\r\n      \"title\": \"Destinataire\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 100,\r\n      \"minLength\": 0,\r\n      \"pattern\": \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}\u20AC@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\r\n      \"xpath\": \"C36\",\r\n      \"business_type\": \"\",\r\n      \"condition\": \"Si C37 = AU\",\r\n      \"order\": 0,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C37_6\": {\r\n      \"title\": \"Indice de r\u00E9p\u00E9tition\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 1,\r\n      \"minLength\": 1,\r\n      \"pattern\": \"\\\\p{L}\",\r\n      \"xpath\": \"C37_6\",\r\n      \"oneOf\": [\r\n        {\r\n          \"enum\": [\r\n            \"B\"\r\n          ],\r\n          \"description\": \"bis\"\r\n        },\r\n        {\r\n          \"enum\": [\r\n            \"T\"\r\n          ],\r\n          \"description\": \"ter\"\r\n        },\r\n        {\r\n          \"enum\": [\r\n            \"Q\"\r\n          ],\r\n          \"description\": \"quater\"\r\n        },\r\n        {\r\n          \"enum\": [\r\n            \"C\"\r\n          ],\r\n          \"description\": \"quinquies\"\r\n        }\r\n      ],\r\n      \"business_type\": \"INDICE_REPETITION\",\r\n      \"condition\": \"Si C37 = AU\",\r\n      \"order\": 3,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C39\": {\r\n      \"title\": \"Num\u00E9ro de t\u00E9l\u00E9phone pour les relations administratives\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 0,\r\n      \"minLength\": 0,\r\n      \"pattern\": \"\",\r\n      \"xpath\": \"C39\",\r\n      \"business_type\": \"\",\r\n      \"condition\": \"\",\r\n      \"order\": 0,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C37_13\": {\r\n      \"title\": \"Libell\u00E9 de commune\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 32,\r\n      \"minLength\": 1,\r\n      \"pattern\": \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}\u20AC@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\r\n      \"xpath\": \"C37_13\",\r\n      \"business_type\": \"\",\r\n      \"condition\": \"Si C37 = AU\",\r\n      \"order\": 10,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C37_12\": {\r\n      \"title\": \"Libell\u00E9 de voie ou de lieu-dit\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 32,\r\n      \"minLength\": 0,\r\n      \"pattern\": \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}\u20AC@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\r\n      \"xpath\": \"C37_12\",\r\n      \"business_type\": \"\",\r\n      \"condition\": \"Si C37 = AU\",\r\n      \"order\": 9,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C37_14\": {\r\n      \"title\": \"Libell\u00E9 de pays\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 38,\r\n      \"minLength\": 0,\r\n      \"pattern\": \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}\u20AC@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\r\n      \"xpath\": \"C37_14\",\r\n      \"business_type\": \"\",\r\n      \"condition\": \"\",\r\n      \"order\": 11,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C37_7\": {\r\n      \"title\": \"Distribution sp\u00E9ciale\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 8,\r\n      \"minLength\": 0,\r\n      \"pattern\": \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}\u20AC@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\r\n      \"xpath\": \"C37_7\",\r\n      \"business_type\": \"\",\r\n      \"condition\": \"Si C37 = AU\",\r\n      \"order\": 4,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C37_8\": {\r\n      \"title\": \"Code postal\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 9,\r\n      \"minLength\": 0,\r\n      \"pattern\": \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}\u20AC@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\r\n      \"xpath\": \"C37_8\",\r\n      \"business_type\": \"CODE_POSTAL\",\r\n      \"condition\": \"Si C37 = AU\",\r\n      \"order\": 5,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C37_11\": {\r\n      \"title\": \"Type de voie\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 4,\r\n      \"minLength\": 0,\r\n      \"pattern\": \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}\u20AC@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\r\n      \"xpath\": \"C37_11\",\r\n      \"oneOf\": [\r\n        {\r\n          \"enum\": [\r\n            \"R\"\r\n          ],\r\n          \"description\": \"Rue\"\r\n        },\r\n        {\r\n          \"enum\": [\r\n            \"A\"\r\n          ],\r\n          \"description\": \"Avenue\"\r\n        },\r\n        {\r\n          \"enum\": [\r\n            \"B\"\r\n          ],\r\n          \"description\": \"Boulvard\"\r\n        }\r\n      ],\r\n      \"business_type\": \"ABREVIATION_ADMISE\",\r\n      \"condition\": \"Si C37 = AU\",\r\n      \"order\": 8,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C37_9\": {\r\n      \"title\": \"Libell\u00E9 de localit\u00E9 ou de bureau distributeur\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 32,\r\n      \"minLength\": 0,\r\n      \"pattern\": \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}\u20AC@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\r\n      \"xpath\": \"C37_9\",\r\n      \"business_type\": \"\",\r\n      \"condition\": \"Si C37 = AU\",\r\n      \"order\": 6,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C37_10\": {\r\n      \"title\": \"Compl\u00E9ment de localisation\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": 1,\r\n      \"maxLength\": 38,\r\n      \"minLength\": 0,\r\n      \"pattern\": \"[\\\\p{L}\\\\d\\\\s\\\\.,\\\\[\\\\]\\\\{\\\\}\u20AC@#\\\\-\\\\(\\\\)/='\\\\+:\\\\?!\\\"%&\\\\*;<>]*\",\r\n      \"xpath\": \"C37_10\",\r\n      \"business_type\": \"\",\r\n      \"condition\": \"Si C37 = AU\",\r\n      \"order\": 7,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    },\r\n    \"C37_AUT\": {\r\n      \"title\": \"Autre adresse\",\r\n      \"type\": \"string\",\r\n      \"format\": null,\r\n      \"maxItems\": null,\r\n      \"maxLength\": null,\r\n      \"minLength\": null,\r\n      \"pattern\": \"\",\r\n      \"xpath\": \"C37_AUT\",\r\n      \"business_type\": \"\",\r\n      \"condition\": \"\",\r\n      \"order\": null,\r\n      \"read_only\": false,\r\n      \"show\": true\r\n    }\r\n  },\r\n  \"required\": [],\r\n  \"defaults\": null\r\n}";

        JSONObject obj = new JSONObject(s);
        return s;
    }
	
	/**
     * POST  /metadata : Create a new metadata.
     */
    @PostMapping("/metadata/adf")
    @Timed
    public ResponseEntity<Object> validMetadata(@RequestBody Object metadataDTO) throws URISyntaxException {
        log.debug("REST request to save Metadata : {}", metadataDTO);

        return ResponseEntity.ok()
            .body(metadataDTO);
    }
}
