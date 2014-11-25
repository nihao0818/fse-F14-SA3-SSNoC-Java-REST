package edu.cmu.sv.ws.ssnoc.rest;

/**
 * Created by YHWH on 11/23/14.
 */
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        super(ExchangeMessageService.class, MultiPartFeature.class);
    }
}
