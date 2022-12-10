package br.com.gusta.integrationtests.controller.withjson;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.gusta.configs.*;
import br.com.gusta.integrationtests.testcontainers.*;
import br.com.gusta.integrationtests.vo.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.*;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerJsonTest extends AbstractIntegrationTest {

    private static TokenVO tokenVO;

    @Test
    @Order(1)
    public void testSignin() throws JsonMappingException, JsonProcessingException {

        AccountCredentialsVO user = new AccountCredentialsVO("gusta", "admin123");

        tokenVO = given()
                .basePath("/auth/signin")
                    .port(TestConfigs.SERVER_PORT)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(user)
                    .when()
                .post()
                    .then()
                    .statusCode(200)
                        .extract()
                            .body()
                                .as(TokenVO.class);

        assertNotNull(tokenVO.getAccessToken());
        assertNotNull(tokenVO.getRefreshToken());
    }

    @Test
    @Order(2)
    public void testRefresh() throws JsonMappingException, JsonProcessingException {

        AccountCredentialsVO user = new AccountCredentialsVO("gusta", "admin123");

        var newTokenVO = given()
                .basePath("/auth/refresh")
                    .port(TestConfigs.SERVER_PORT)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .pathParam("username", tokenVO.getUsername())
                        .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, TestConfigs.AUTHORIZATION_PREFIX + tokenVO.getRefreshToken())
                .when()
                    .put("{username}")
                .then()
                    .statusCode(200)
                .extract()
                    .body()
                        .as(TokenVO.class);

        assertNotNull(newTokenVO.getAccessToken());
        assertNotNull(newTokenVO.getRefreshToken());
    }

}
