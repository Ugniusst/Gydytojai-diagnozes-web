package com.Gydytojaidiagnozes.web.controller;

import static org.junit.jupiter.api.Assertions.*;
import com.Gydytojaidiagnozes.web.model.Doctor;
import com.Gydytojaidiagnozes.web.service.DoctorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@WebMvcTest(value = DoctorControllerREST.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DoctorControllerRESTTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DoctorService doctorService;

    private List<Doctor> doctorList;

    @BeforeAll
    void populateData() {
        doctorList= new ArrayList<>();
        doctorList.add(new Doctor(1, "Jolanta", "+37062542837"));
        doctorList.add(new Doctor(2, "Juozas" , "+37062642837"));
    }
    @AfterEach
    void tearDown() {
        reset(doctorService);
    }

    @Test
    void testShowAllDoctorList() throws Exception
    {
        when(doctorService.findAllDoctors()).thenReturn(doctorList);
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/Doctor")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andReturn();
        String expected = "[{\"id\":1,\"name\":\"Jolanta\",\"telephoneNumber\":\"+37062542837\"},{\"id\":2,\"name\":\"Juozas\",\"telephoneNumber\":\"+37062642837\"}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
    @Test
    void testDoctorById() throws Exception {
        when(doctorService.findDoctorById(Mockito.anyInt())).thenReturn(doctorList.get(1));
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/Doctor/{id}", doctorList.get(1).getId())
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andReturn();

        String expected = "{\"id\":2,\"name\":\"Juozas\",\"telephoneNumber\":\"+37062642837\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
    @Test
    void testAddDoctor() throws Exception {
        when(doctorService.saveDoctor(Mockito.any(Doctor.class))).thenReturn(doctorList.get(1));

        String json = "{\"id\":2,\"name\":\"Juozas\",\"telephoneNumber\":\"+37062642837\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(
                        "/Doctor")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    @Test
    public void testDeleteById() throws Exception
    {
        when(doctorService.saveDoctor(Mockito.any(Doctor.class))).thenReturn(doctorList.get(1));
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/Doctor/{id}", "2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        when(doctorService.saveDoctor(Mockito.any(Doctor.class))).thenReturn(doctorList.get(1));

        String json = "{\"id\":2,\"name\":\"Juozas\",\"telephoneNumber\":\"+37062642837\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(
                        "/Doctor")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
