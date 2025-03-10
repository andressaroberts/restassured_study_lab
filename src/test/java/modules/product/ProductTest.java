package modules.product;
import dataFactory.ProductDataFactory;
import dataFactory.UserDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("API tests for product module")
public class ProductTest {
    private String token;

    @BeforeEach
    public void beforeEach(){
        baseURI = "http://165.227.93.41";
        basePath = "/lojinha";

        this.token = given().contentType(ContentType.JSON).body(UserDataFactory.createAdminUser())
             .when().post("/v2/login")
             .then().extract().path("data.token");
    }


    @Test
    @DisplayName("Validate that a product value equal to 0.00 is not allowed")
    public void testValidateForbiddenProductValueZero(){
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProductDataFactory.createCommomProductWithValueEqualTo(0.00))
        .when()
        .post("/v2/produtos")
        .then().assertThat()
                       .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                       .statusCode(422);
    }

    @Test
    @DisplayName("Validate that a product value equal to 7000.01 is not allowed")
    public void testValidateForbiddenProductValueAboveLimit(){
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProductDataFactory.createCommomProductWithValueEqualTo(7000.01))
                .when()
                .post("/v2/produtos")
                .then().assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);

    }
}
