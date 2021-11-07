package com.Gydytojaidiagnozes.web.controller;

import com.Gydytojaidiagnozes.web.model.Diagnose;
import com.Gydytojaidiagnozes.web.model.Doctor;
import com.Gydytojaidiagnozes.web.model.Patient;
import com.Gydytojaidiagnozes.web.service.DiagnoseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = DiagnoseControllerMVC.class)
public class DiagnoseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DiagnoseService diagnoseService;

    @InjectMocks
    DiagnoseControllerMVC diagnoseController;

    @Test
    void testShowAll() throws Exception{
        List<Diagnose> diagnoseList = new ArrayList<Diagnose>();
        Doctor doctor = new Doctor(1,"Tomas","+37062475204");
        Patient patient = new Patient(1,"Patrikas","+44062475204");
        diagnoseList.add(new Diagnose(1, doctor, patient, "covid-19", "2021-11-05"));
        diagnoseList.add(new Diagnose(1, doctor, patient, "covid-19", "2021-11-05"));
        when(diagnoseService.findAllDiagnoses()).thenReturn(diagnoseList);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/list-diagnoses")
                .accept(MediaType.TEXT_HTML);

        MvcResult res = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("list-diagnoses"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/list-diagnoses.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("diagnoses"))
                .andReturn();
    }

    @Test
    public void testShowAddPage() throws Exception{
        RequestBuilder rb = MockMvcRequestBuilders.get("/add-diagnose");

        MvcResult rs = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-diagnose"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/add-diagnose.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("diagnose"))
                .andExpect(MockMvcResultMatchers.model().attribute("diagnose", hasProperty("id")))
                .andExpect(MockMvcResultMatchers.model().attribute("diagnose", hasProperty("patientId")))
                .andExpect(MockMvcResultMatchers.model().attribute("diagnose", hasProperty("doctorId")))
                .andExpect(MockMvcResultMatchers.model().attribute("diagnose", hasProperty("diagnoseText")))
                .andExpect(MockMvcResultMatchers.model().attribute("diagnose", hasProperty("date")))
                .andReturn();
    }

    @Test
    public void testAdd() throws Exception{
        Doctor doctor = new Doctor(1,"Tomas","+37062475204");
        Patient patient = new Patient(1,"Patrikas","+44062475204");
        when(diagnoseService.saveDiagnose(Mockito.any(Diagnose.class))).thenReturn(new Diagnose(1, doctor, patient, "covid-19", "2021-11-05"));

        RequestBuilder rb = MockMvcRequestBuilders
                .post("/add-diagnose")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("name","Jolanta")
                .param("telephoneNumber","+37062458759")
                .flashAttr("diagnose",new Diagnose());

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-diagnoses"));

        verify(diagnoseService).saveDiagnose(Mockito.any(Diagnose.class));
    }
    @Test
    public void testUpdate() throws Exception
    {
        Doctor doctor = new Doctor(1,"Tomas","+37062475204");
        Patient patient = new Patient(1,"Patrikas","+44062475204");
        when(diagnoseService.saveDiagnose(Mockito.any(Diagnose.class))).thenReturn(new Diagnose(1, doctor, patient, "covid-19", "2021-11-05"));
        RequestBuilder rb = MockMvcRequestBuilders
                .post("/update-diagnose/"+1)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name","Jolanta")
                .param("telephoneNumber","+370625475836")
                .flashAttr("diagnose",new Diagnose());

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-diagnoses"));

        verify(diagnoseService).updateDiagnose(Mockito.any(Diagnose.class));
    }
    @Test
    public void testDelete() throws Exception{
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/delete-diagnose/{diagnoseId}",Mockito.anyLong())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("diagnoseId", String.valueOf(1));

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-diagnoses"));

    }
}
