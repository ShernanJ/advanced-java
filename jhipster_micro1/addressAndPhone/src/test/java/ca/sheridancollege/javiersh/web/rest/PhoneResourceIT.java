package ca.sheridancollege.javiersh.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ca.sheridancollege.javiersh.IntegrationTest;
import ca.sheridancollege.javiersh.domain.Phone;
import ca.sheridancollege.javiersh.repository.PhoneRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PhoneResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PhoneResourceIT {

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/phones";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPhoneMockMvc;

    private Phone phone;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Phone createEntity(EntityManager em) {
        Phone phone = new Phone().phone(DEFAULT_PHONE);
        return phone;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Phone createUpdatedEntity(EntityManager em) {
        Phone phone = new Phone().phone(UPDATED_PHONE);
        return phone;
    }

    @BeforeEach
    public void initTest() {
        phone = createEntity(em);
    }

    @Test
    @Transactional
    void createPhone() throws Exception {
        int databaseSizeBeforeCreate = phoneRepository.findAll().size();
        // Create the Phone
        restPhoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(phone)))
            .andExpect(status().isCreated());

        // Validate the Phone in the database
        List<Phone> phoneList = phoneRepository.findAll();
        assertThat(phoneList).hasSize(databaseSizeBeforeCreate + 1);
        Phone testPhone = phoneList.get(phoneList.size() - 1);
        assertThat(testPhone.getPhone()).isEqualTo(DEFAULT_PHONE);
    }

    @Test
    @Transactional
    void createPhoneWithExistingId() throws Exception {
        // Create the Phone with an existing ID
        phone.setId(1L);

        int databaseSizeBeforeCreate = phoneRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(phone)))
            .andExpect(status().isBadRequest());

        // Validate the Phone in the database
        List<Phone> phoneList = phoneRepository.findAll();
        assertThat(phoneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPhones() throws Exception {
        // Initialize the database
        phoneRepository.saveAndFlush(phone);

        // Get all the phoneList
        restPhoneMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phone.getId().intValue())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)));
    }

    @Test
    @Transactional
    void getPhone() throws Exception {
        // Initialize the database
        phoneRepository.saveAndFlush(phone);

        // Get the phone
        restPhoneMockMvc
            .perform(get(ENTITY_API_URL_ID, phone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(phone.getId().intValue()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE));
    }

    @Test
    @Transactional
    void getNonExistingPhone() throws Exception {
        // Get the phone
        restPhoneMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPhone() throws Exception {
        // Initialize the database
        phoneRepository.saveAndFlush(phone);

        int databaseSizeBeforeUpdate = phoneRepository.findAll().size();

        // Update the phone
        Phone updatedPhone = phoneRepository.findById(phone.getId()).get();
        // Disconnect from session so that the updates on updatedPhone are not directly saved in db
        em.detach(updatedPhone);
        updatedPhone.phone(UPDATED_PHONE);

        restPhoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPhone.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPhone))
            )
            .andExpect(status().isOk());

        // Validate the Phone in the database
        List<Phone> phoneList = phoneRepository.findAll();
        assertThat(phoneList).hasSize(databaseSizeBeforeUpdate);
        Phone testPhone = phoneList.get(phoneList.size() - 1);
        assertThat(testPhone.getPhone()).isEqualTo(UPDATED_PHONE);
    }

    @Test
    @Transactional
    void putNonExistingPhone() throws Exception {
        int databaseSizeBeforeUpdate = phoneRepository.findAll().size();
        phone.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, phone.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(phone))
            )
            .andExpect(status().isBadRequest());

        // Validate the Phone in the database
        List<Phone> phoneList = phoneRepository.findAll();
        assertThat(phoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPhone() throws Exception {
        int databaseSizeBeforeUpdate = phoneRepository.findAll().size();
        phone.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(phone))
            )
            .andExpect(status().isBadRequest());

        // Validate the Phone in the database
        List<Phone> phoneList = phoneRepository.findAll();
        assertThat(phoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPhone() throws Exception {
        int databaseSizeBeforeUpdate = phoneRepository.findAll().size();
        phone.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhoneMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(phone)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Phone in the database
        List<Phone> phoneList = phoneRepository.findAll();
        assertThat(phoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePhoneWithPatch() throws Exception {
        // Initialize the database
        phoneRepository.saveAndFlush(phone);

        int databaseSizeBeforeUpdate = phoneRepository.findAll().size();

        // Update the phone using partial update
        Phone partialUpdatedPhone = new Phone();
        partialUpdatedPhone.setId(phone.getId());

        restPhoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPhone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPhone))
            )
            .andExpect(status().isOk());

        // Validate the Phone in the database
        List<Phone> phoneList = phoneRepository.findAll();
        assertThat(phoneList).hasSize(databaseSizeBeforeUpdate);
        Phone testPhone = phoneList.get(phoneList.size() - 1);
        assertThat(testPhone.getPhone()).isEqualTo(DEFAULT_PHONE);
    }

    @Test
    @Transactional
    void fullUpdatePhoneWithPatch() throws Exception {
        // Initialize the database
        phoneRepository.saveAndFlush(phone);

        int databaseSizeBeforeUpdate = phoneRepository.findAll().size();

        // Update the phone using partial update
        Phone partialUpdatedPhone = new Phone();
        partialUpdatedPhone.setId(phone.getId());

        partialUpdatedPhone.phone(UPDATED_PHONE);

        restPhoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPhone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPhone))
            )
            .andExpect(status().isOk());

        // Validate the Phone in the database
        List<Phone> phoneList = phoneRepository.findAll();
        assertThat(phoneList).hasSize(databaseSizeBeforeUpdate);
        Phone testPhone = phoneList.get(phoneList.size() - 1);
        assertThat(testPhone.getPhone()).isEqualTo(UPDATED_PHONE);
    }

    @Test
    @Transactional
    void patchNonExistingPhone() throws Exception {
        int databaseSizeBeforeUpdate = phoneRepository.findAll().size();
        phone.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, phone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(phone))
            )
            .andExpect(status().isBadRequest());

        // Validate the Phone in the database
        List<Phone> phoneList = phoneRepository.findAll();
        assertThat(phoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPhone() throws Exception {
        int databaseSizeBeforeUpdate = phoneRepository.findAll().size();
        phone.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(phone))
            )
            .andExpect(status().isBadRequest());

        // Validate the Phone in the database
        List<Phone> phoneList = phoneRepository.findAll();
        assertThat(phoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPhone() throws Exception {
        int databaseSizeBeforeUpdate = phoneRepository.findAll().size();
        phone.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhoneMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(phone)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Phone in the database
        List<Phone> phoneList = phoneRepository.findAll();
        assertThat(phoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePhone() throws Exception {
        // Initialize the database
        phoneRepository.saveAndFlush(phone);

        int databaseSizeBeforeDelete = phoneRepository.findAll().size();

        // Delete the phone
        restPhoneMockMvc
            .perform(delete(ENTITY_API_URL_ID, phone.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Phone> phoneList = phoneRepository.findAll();
        assertThat(phoneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
