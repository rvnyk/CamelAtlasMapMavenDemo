package com.example.atlasmap.camel.CamelAtllasMapMavenDemo.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class CamelRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);

        rest("/get")
                .get("/").route().transform().constant("Hello World")
                .endRest();


        rest("/transform")
                .consumes("application/xml")
                .produces("application/json")

                .post().to("direct:transformXML");


        from("direct:transformXML")
                .to("atlas:atlasmap-mapping-0.adm")
                .log("${body}");
    }
}
