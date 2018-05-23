package com.springsecurity.mysql.file;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.springsecurity.mysql.domains.Order;

public class ReusableMethods {

    public static XmlPath rawToXML(Response res) {

        String responseString = res.asString();
        XmlPath x = new XmlPath(responseString);

        return x;
    }

    public static JsonPath rawToJson(Response res) {

        String responseString = res.asString();
        JsonPath js = new JsonPath(responseString);

        return js;
    }

    public static void insertData(int domainId, String domainName,
                                  String status, String expires, String autoRenew,
                                  String createdAt, String domainProvider) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate
                .update("INSERT INTO domain(domain_id, domain_provider, domain_name,  created_at, expires, renew_auto, status)"
                        + "VALUES (?,?,?,?,?,?,?) domainId,  domainProvider, domainName, createdAt, expires, autoRenew, status");
    }

    public static List<Order> getData() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        List<Order> fromDatabase = jdbcTemplate.query("select * from domain",
                new OrderMapper());

        return fromDatabase;
    }

}

class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order(rs.getInt("domain_id"),
                rs.getString("domain_provider"), rs.getString("domain_name"),
                rs.getString("created_at"), rs.getString("expires"),
                rs.getString("renew_auto"), rs.getString("status"));
        return order;
    }

}
