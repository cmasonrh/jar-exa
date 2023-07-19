package org.acme.rest.json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;

@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
public class Hello {

    @Inject
    @DataSource("oracle")
    AgroalDataSource ds;
    
    public Hello() {
        // try {
        //     Connection con = defaultDataSource.getConnection();
        //     PreparedStatement ps = con.prepareStatement("SELECT 1 from DUAL");
        //     ResultSet rs = ps.executeQuery();
        //     rs.next();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
    }

    @GET
    public String getDual() {
        try {
            Connection con = ds.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT 1 from DUAL");
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
