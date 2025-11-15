package com.au.spring_jpa.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.sql.init.schema-locations=classpath:/sql/schema.sql"
})
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateAdminUser() throws Exception {
        String json = """
                {"userType": "SIMPLE", "username":"Alice","password":"secret123"}
                """;
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Alice"))
                .andExpect(jsonPath("$.userKind").value("SIMPLE"));
    }

    @Test
    void testCreateAgentUser() throws Exception {
        String json = """
                {"userType": "AGENT", "username":"Alice","password":"secret123"}
                """;
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Alice"))
                .andExpect(jsonPath("$.userKind").value("AGENT"));
    }

    @Test
    void testCreateCustomerUser() throws Exception {
        String json = """
                {"userType":"CUSTOMER","username":"bob","password":"secret123","loyaltyPoints":120}
                """;
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("bob"))
                .andExpect(jsonPath("$.loyaltyPoints").value(120));
    }

    @Test
    public void shouldReturnAll() throws Exception {
        // Given
        String customer = """
                {"userType":"CUSTOMER","username":"bob","password":"secret123","loyaltyPoints":120}
                """;
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(customer));

        String agent = """
                {"userType": "AGENT", "username":"Alice","password":"secret123"}
                """;
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(agent));

        String simple = """
                {"userType": "SIMPLE", "username":"Alice","password":"secret123"}
                """;
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(simple));

        String admin = """
                {"userType": "ADMIN", "username":"Alice","password":"alice123", "permissions": "ALL"}
                """;
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(admin));

        // Expect
        mockMvc.perform(get("/users")).andDo(print())
                .andExpect(jsonPath("$.*", hasSize(4)));

    }
}
