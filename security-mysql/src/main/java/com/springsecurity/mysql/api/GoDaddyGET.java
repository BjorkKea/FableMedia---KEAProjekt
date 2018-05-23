package com.springsecurity.mysql.api;

import static io.restassured.RestAssured.given;

import com.springsecurity.mysql.file.Resources;
import com.springsecurity.mysql.file.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springsecurity.mysql.domains.Order;

@Component
public class GoDaddyGET {


    @Autowired
    DataSource dataSource;

    Properties prop = new Properties();

    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream(
                "C:\\Users\\CBjoe\\IdeaProjects\\EksamenMedia-master\\FableDone\\src\\main\\java\\files\\env.properties");
        prop.load(fis);

        // prop.getProperty("HOST");
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void Test() throws Exception {

        getData();
        // write your code here

        // BaseURL or Host
        RestAssured.baseURI = prop.getProperty("GODADDYHOST");

        Response res = given()
                .header("Authorization", prop.getProperty("GODADDYKEY")).log()
                .all().header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .param("includes", "contacts").when()
                .get(Resources.godaddyGetData()).then().assertThat()
                .statusCode(200).extract().response();

        JsonPath js = ReusableMethods.rawToJson(res);

        int count = js.get("array.size()");
        List<Order> toStore = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            String domainName = js.getString("domains[" + i + "]");
            int domainId = js.getInt("domainId[" + i + "]");
            String status = js.getString("status[" + i + "]");
            String renewAuto = js.getString("renewAuto[" + i + "]");

            ArrayList<String> expireArray = new ArrayList<String>();
            String expiresSource = js.getString("expires[" + i + "]");
            expireArray.add(expiresSource);

            ArrayList<String> createdArray = new ArrayList<String>();
            String createdAtSource = js.getString("createdAt[" + i + "]");
            createdArray.add(createdAtSource);

            String expiresFinal = "";
            String createdFinal = "";

            System.out.println(domainName);
            System.out.println(domainId);
            System.out.println(status);

            for (String dateStr : expireArray) {
                if (dateStr != null) {

                    LocalDateTime parse = LocalDateTime.parse(expiresSource,
                            DateTimeFormatter.ISO_DATE_TIME);
                    expiresFinal = parse.format(DateTimeFormatter
                            .ofPattern("dd MM yyyy hha"));
                    System.out.println(expiresFinal);
                }
            }

            System.out.println("Auto renew = " + renewAuto);

            for (String dateStr : createdArray) {
                if (dateStr != null) {

                    LocalDateTime parse2 = LocalDateTime.parse(createdAtSource,
                            DateTimeFormatter.ISO_DATE_TIME);
                    createdFinal = parse2.format(DateTimeFormatter
                            .ofPattern("dd MM yyyy hha"));
                    System.out.println(createdFinal);
                }
            }

            toStore.add(new Order(domainId, "Godaddy", domainName, createdFinal, expiresFinal,
                    renewAuto, status));

        }

        List<Order> fromDatabase = ReusableMethods.getData();

        toStore.removeAll(fromDatabase);

        for (Order order : toStore) {
            ReusableMethods.insertData(order.getDomain_id(), "Godaddy", order.getDomain_name(),
                    order.getCreated_at(), order.getExpires(),
                    order.getRenew_auto(), order.getStatus());
        }
        System.out.println(toStore.size());

    }
}
