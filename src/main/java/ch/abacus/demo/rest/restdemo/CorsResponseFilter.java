/*
 * Creator:
 * 2019-06-06 06:21 Schwyter
 *
 * Maintainer:
 * 2019-06-06 06:21 Schwyter
 *
 * Last Modification:
 * $Id:$
 *
 * Copyright (c) 2019 ABACUS Research AG, All Rights Reserved
 */
package ch.abacus.demo.rest.restdemo;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

public class CorsResponseFilter implements ContainerResponseFilter {


    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        headers.add("Access-Control-Allow-Origin", "*");
        //headers.add("Access-Control-Allow-Origin", "http://abcd.org"); //allows CORS requests only coming from abcd.org
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");

    }
}
