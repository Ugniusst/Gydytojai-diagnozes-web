package com.Gydytojaidiagnozes.web.controller;

import com.Gydytojaidiagnozes.web.model.Diagnose;
import com.Gydytojaidiagnozes.web.model.Doctor;
import com.Gydytojaidiagnozes.web.model.Patient;
import com.Gydytojaidiagnozes.web.service.DiagnoseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = DiagnoseControllerREST.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DiagnoseControllerRESTTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DiagnoseService diagnoseService;

    private List<Diagnose> diagnoseList;

    @BeforeAll
    void populateData() {
        Doctor doctor = new Doctor(1,"Tomas","+37062475204");
        Patient patient = new Patient(1,"Patrikas","+44062475204");
        diagnoseList= new ArrayList<>();
        diagnoseList.add(new Diagnose(1, doctor, patient, "covid-19", "2021-11-05"));
        diagnoseList.add(new Diagnose(2, doctor, patient, "covid-19", "2021-11-04"));
    }
    @AfterEach
    void tearDown() {
        reset(diagnoseService);
    }

    @Test
    void testShowAllDiagnoseList() throws Exception
    {
        when(diagnoseService.findAllDiagnoses()).thenReturn(diagnoseList);
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/Diagnose")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "[{\"id\":1,\"doctorId\":{\"id\":1,\"name\":\"Tomas\",\"telephoneNumber\":\"+37062475204\"},\"patientId\":{\"id\":1,\"name\":\"Patrikas\",\"telephoneNumber\":\"+44062475204\"},\"diagnoseText\":\"covid-19\",\"date\":\"2021-11-05\"},{\"id\":2,\"doctorId\":{\"id\":1,\"name\":\"Tomas\",\"telephoneNumber\":\"+37062475204\"},\"patientId\":{\"id\":1,\"name\":\"Patrikas\",\"telephoneNumber\":\"+44062475204\"},\"diagnoseText\":\"covid-19\",\"date\":\"2021-11-04\"}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
    @Test
    void testDiagnoseById() throws Exception {
        when(diagnoseService.findDiagnoseById(Mockito.anyInt())).thenReturn(diagnoseList.get(1));
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/Diagnose/{id}", diagnoseList.get(1).getId())
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andReturn();
        String expected = "{\"id\":2,\"doctorId\":{\"id\":1,\"name\":\"Tomas\",\"telephoneNumber\":\"+37062475204\"},\"patientId\":{\"id\":1,\"name\":\"Patrikas\",\"telephoneNumber\":\"+44062475204\"},\"diagnoseText\":\"covid-19\",\"date\":\"2021-11-04\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
    @Test
    void testAddDiagnose() throws Exception {
        when(diagnoseService.saveDiagnose(Mockito.any(Diagnose.class))).thenReturn(diagnoseList.get(1));

        String json = "{\"id\":2,\"doctorId\":{\"id\":1,\"name\":\"Tomas\",\"telephoneNumber\":\"+37062475204\"},\"patientId\":{\"id\":1,\"name\":\"Patrikas\",\"telephoneNumber\":\"+44062475204\"},\"diagnoseText\":\"covid-19\",\"date\":\"2021-11-04\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(
                        "/Diagnose")
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
        when(diagnoseService.saveDiagnose(Mockito.any(Diagnose.class))).thenReturn(diagnoseList.get(1));
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/Diagnose/{id}", "2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        when(diagnoseService.saveDiagnose(Mockito.any(Diagnose.class))).thenReturn(diagnoseList.get(1));

        String json = "{\"id\":2,\"doctorId\":{\"id\":1,\"name\":\"Tomas\",\"telephoneNumber\":\"+37062475204\"},\"patientId\":{\"id\":1,\"name\":\"Patrikas\",\"telephoneNumber\":\"+44062475204\"},\"diagnoseText\":\"covid-19\",\"date\":\"2021-11-04\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(
                        "/Diagnose")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
