/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabonay.advantageservices.securities;

import com.sabonay.advantageservices.utils.AppLogger;
import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import org.apache.log4j.Logger;

/**
 *
 * @author nana
 */
@Priority(1)
@Provider
@PreMatching
public class CORSFilter implements ContainerResponseFilter {

    private static final Logger log = Logger.getLogger(CORSFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext crc, ContainerResponseContext response) throws IOException {
        AppLogger.info(log, "request url:: " + crc.getUriInfo().getAbsolutePath().getPath());
        response.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        response.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST");
        response.getHeaders().putSingle("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization , x-api-key");
    }
}
