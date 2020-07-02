package com.google.capstone;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("v1")
public class Main extends ResourceConfig {

    public Main() {
        packages("com.google.capstone.api");
    }
}
