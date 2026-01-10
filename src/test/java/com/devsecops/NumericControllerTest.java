package com.devsecops;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) 
class NumericControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void welcome_shouldReturnMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Kubernetes DevSecOps"));
    }

    @Test
    void compare_greaterThan50() throws Exception {
        mockMvc.perform(get("/compare/60"))
                .andExpect(status().isOk())
                .andExpect(content().string("Greater than 50"));
    }

    @Test
    void compare_smallerOrEqual50() throws Exception {
        mockMvc.perform(get("/compare/10"))
                .andExpect(status().isOk())
                .andExpect(content().string("Smaller than or equal to 50"));
    }

    @Test
    void increment_shouldReturnIncrementedValue() throws Exception {
        Mockito.when(
                restTemplate.getForEntity(
                        "http://node-service:5000/plusone/5",
                        String.class
                )
        ).thenReturn(ResponseEntity.ok("6"));

        mockMvc.perform(get("/increment/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("6"));
    }
}
