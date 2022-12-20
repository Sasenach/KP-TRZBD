package com.example.upitog;

import com.example.upitog.Model.Employee;
import com.example.upitog.Model.Post;
import com.example.upitog.Model.Role;
import com.example.upitog.Repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class UpItogApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private EmployeeRepository userRepositorie;


    @Test
    void T_Registartion() throws Exception {
        Employee us = new Employee();
        Post post = new Post();
        post.setId(43);
        us.setPassword("TESTINGtests1!");
        us.setEmail("TESTINGtests@.ru");
        us.setUsername("TESTINGtests");
        us.setAddress("TESTINGtests");
        us.setName("TESTINGtests");
        us.setRoles(Set.of(Role.ADMIN));
        us.setPost(post);
        us.setPasportNumber("TESTINGtests");
        us.setSurname("TESTINGtests");
        us.setMidlename("TESTINGtests");
        Mockito.when(userRepositorie.save(us)).thenReturn(Optional.of(us).orElseThrow());
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                        .content(objectMapper.writeValueAsString(us)))
                .andExpect(status().isFound());
    }

    @Test
    void T_RegistartionFailed() throws Exception {
        Employee us = new Employee();
        us.setPassword("Qwerty1234");
        us.setEmail("");
        us.setUsername("");
        us.setAddress("");
        us.setName("Владимер");
        us.setPasportNumber("");
        us.setSurname("Ларионов");
        us.setMidlename("Владимерович");
        Mockito.when(userRepositorie.save(us)).thenReturn(Optional.of(us).orElseThrow());
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                        .content(objectMapper.writeValueAsString(us)))
                .andExpect(status().isFound());
    }
}
