package ca.sheridancollege.javiersh.web.rest;

import ca.sheridancollege.javiersh.domain.Phone;
import ca.sheridancollege.javiersh.repository.PhoneRepository;
import ca.sheridancollege.javiersh.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ca.sheridancollege.javiersh.domain.Phone}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PhoneResource {

    private final Logger log = LoggerFactory.getLogger(PhoneResource.class);

    private static final String ENTITY_NAME = "addressAndPhonePhone";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PhoneRepository phoneRepository;

    public PhoneResource(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    /**
     * {@code POST  /phones} : Create a new phone.
     *
     * @param phone the phone to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new phone, or with status {@code 400 (Bad Request)} if the phone has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/phones")
    public ResponseEntity<Phone> createPhone(@RequestBody Phone phone) throws URISyntaxException {
        log.debug("REST request to save Phone : {}", phone);
        if (phone.getId() != null) {
            throw new BadRequestAlertException("A new phone cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Phone result = phoneRepository.save(phone);
        return ResponseEntity
            .created(new URI("/api/phones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /phones/:id} : Updates an existing phone.
     *
     * @param id the id of the phone to save.
     * @param phone the phone to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated phone,
     * or with status {@code 400 (Bad Request)} if the phone is not valid,
     * or with status {@code 500 (Internal Server Error)} if the phone couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/phones/{id}")
    public ResponseEntity<Phone> updatePhone(@PathVariable(value = "id", required = false) final Long id, @RequestBody Phone phone)
        throws URISyntaxException {
        log.debug("REST request to update Phone : {}, {}", id, phone);
        if (phone.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, phone.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!phoneRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Phone result = phoneRepository.save(phone);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, phone.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /phones/:id} : Partial updates given fields of an existing phone, field will ignore if it is null
     *
     * @param id the id of the phone to save.
     * @param phone the phone to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated phone,
     * or with status {@code 400 (Bad Request)} if the phone is not valid,
     * or with status {@code 404 (Not Found)} if the phone is not found,
     * or with status {@code 500 (Internal Server Error)} if the phone couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/phones/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Phone> partialUpdatePhone(@PathVariable(value = "id", required = false) final Long id, @RequestBody Phone phone)
        throws URISyntaxException {
        log.debug("REST request to partial update Phone partially : {}, {}", id, phone);
        if (phone.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, phone.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!phoneRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Phone> result = phoneRepository
            .findById(phone.getId())
            .map(existingPhone -> {
                if (phone.getPhone() != null) {
                    existingPhone.setPhone(phone.getPhone());
                }

                return existingPhone;
            })
            .map(phoneRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, phone.getId().toString())
        );
    }

    /**
     * {@code GET  /phones} : get all the phones.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of phones in body.
     */
    @GetMapping("/phones")
    public List<Phone> getAllPhones() {
        log.debug("REST request to get all Phones");
        return phoneRepository.findAll();
    }

    /**
     * {@code GET  /phones/:id} : get the "id" phone.
     *
     * @param id the id of the phone to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the phone, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/phones/{id}")
    public ResponseEntity<Phone> getPhone(@PathVariable Long id) {
        log.debug("REST request to get Phone : {}", id);
        Optional<Phone> phone = phoneRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(phone);
    }

    /**
     * {@code DELETE  /phones/:id} : delete the "id" phone.
     *
     * @param id the id of the phone to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/phones/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        log.debug("REST request to delete Phone : {}", id);
        phoneRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
