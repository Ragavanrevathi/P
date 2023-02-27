package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


import java.io.IOException;

import java.util.List;

import static org.acme.XliffWriter.Create;

@Path("/rtm")
public class RTM {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() throws IOException, ParserConfigurationException, TransformerException {
        List<RTM_TRANSLATION> Draft = RTM_TRANSLATION.list("select TEXT from RTM_TRANSLATION where LANG = ?1 order by RTM_ID "+"ASC","DRAFT");
        List<RTM_TRANSLATION> RTM_Id = RTM_TRANSLATION.list("select RTM_ID from RTM_TRANSLATION where LANG = ?1 order by RTM_ID "+"ASC","DRAFT");
        Create(Draft,RTM_Id,"en-US");
         return "Xliff File Generated";
    }

}
