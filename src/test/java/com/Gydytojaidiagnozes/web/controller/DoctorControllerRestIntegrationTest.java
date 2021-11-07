package com.Gydytojaidiagnozes.web.controller;

import com.Gydytojaidiagnozes.web.GydytojaiDiagnozesWebApplication;
import com.Gydytojaidiagnozes.web.model.Doctor;
import com.Gydytojaidiagnozes.web.service.DiagnoseService;
import com.Gydytojaidiagnozes.web.service.DoctorService;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = GydytojaiDiagnozesWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoctorControllerRestIntegrationTest {
    @LocalServerPort
    private int port;


    @Autowired
    DoctorService doctorService;



    @Test
    void testFindDoctorById() throws Exception{
        String expected = "{\"id\":15,\"name\":\"NAUJAS\",\"telephoneNumber\":\"+NUMERIS\"}";
        WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:"+port)
                .build()
                .get()
                .uri("/Doctor/15")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().json(expected);

    }
    @Order(1)
    @Test
    void testAddDoctor() {
        Doctor doctor = new Doctor(6,"Patrikas","+37067350024");
        WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:"+port)
                .build()
                .post()
                .uri("/Doctor")
                .bodyValue(doctor)
                .exchange()
                .expectStatus().isOk();
    }
    @Order(2)
    @Test
    void testDeleteDoctor() {
        Doctor doctor = new Doctor(6,"Patrikas","+37067350024");
        WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:"+port)
                .build()
                .delete()
                .uri("/Doctor/"+doctor.getId())
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    public void testUpdate() throws IOException
    {
        Doctor doctor = new Doctor(7,"NAUJAS","+NUMERIS");
        doctorService.updateDoctor(doctor);
        HttpUriRequest request = RequestBuilder.create("PUT")
                .setUri("http://localhost:" + port + "/Doctor/4")
                .setEntity(new StringEntity("{\"name\":\"Gintaras S.\",\"telephoneNumber\":\"+37062541254\"}", ContentType.APPLICATION_JSON))
                .build();
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.OK.value()));
    }
}
