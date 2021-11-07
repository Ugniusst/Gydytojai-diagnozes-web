package com.Gydytojaidiagnozes.web.controller;

import com.Gydytojaidiagnozes.web.model.Doctor;
import com.Gydytojaidiagnozes.web.service.DoctorService;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = DoctorControllerMVC.class)
public class DoctorControllerTest<DoctorController> {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DoctorService doctorService;

    @InjectMocks
    DoctorController doctorController;
    @Test
    void testShowAll() throws Exception{
        List<Doctor> doctorList = new ArrayList<Doctor>();
        doctorList.add(new Doctor(1, "Jolanta", "+37062542836"));
        doctorList.add(new Doctor(2, "Juozas", "+37062542736"));
        when(doctorService.findAllDoctors()).thenReturn(doctorList);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/list-doctors")
                .accept(MediaType.TEXT_HTML);

        MvcResult res = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("list-doctors"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/list-doctors.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctors"))
                .andReturn();
    }
    
    @Test
    public void testShowAddPage() throws Exception{
        RequestBuilder rb = MockMvcRequestBuilders.get("/add-doctor");

        MvcResult rs = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-doctor"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/add-doctor.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctor"))
                .andExpect(MockMvcResultMatchers.model().attribute("doctor", hasProperty("id")))
                .andExpect(MockMvcResultMatchers.model().attribute("doctor", hasProperty("name")))
                .andExpect(MockMvcResultMatchers.model().attribute("doctor", hasProperty("telephoneNumber")))
                .andReturn();
    }

    @Test
    public void testAdd() throws Exception{
        when(doctorService.saveDoctor(Mockito.any(Doctor.class))).thenReturn(new Doctor(1,"Jolanta","+37062458759"));

        RequestBuilder rb = MockMvcRequestBuilders
                .post("/add-doctor")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("name","Jolanta")
                .param("telephoneNumber","+37062458759")
                .flashAttr("doctor",new Doctor());

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-doctors"));

        verify(doctorService).saveDoctor(Mockito.any(Doctor.class));
    }
    @Test
    public void testUpdate() throws Exception
    {
        when(doctorService.saveDoctor(Mockito.any(Doctor.class))).thenReturn(new Doctor(1,"Jolanta","+370625475836"));
        RequestBuilder rb = MockMvcRequestBuilders
                .post("/update-doctor/"+1)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name","Jolanta")
                .param("telephoneNumber","+370625475836")
                .flashAttr("doctor",new Doctor());

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-doctors"));

        verify(doctorService).updateDoctor(Mockito.any(Doctor.class));
    }
    @Test
    public void testDelete() throws Exception{
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/delete-doctor/{doctorId}",Mockito.anyLong())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("doctorId", String.valueOf(1));

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-doctors"));

    }
}
