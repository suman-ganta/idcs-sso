package oracle.idcs.test;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("t")
public class MyResource {

    private static boolean loggedIn = false;
    private static String token;
    private static final String MYHTML = 
    "<html>" +
        "<head>" +
            "<title>Submit This Form</title>" +
        "</head>" +
        "<body onload=\"javascript:document.forms[0].submit()\">" +
            "<form method=\"post\" action=\"/t/login\">" +
                "<input type=\"hidden\" name=\"state\" value=\"M53iZ39WL1aeGiIF89Wnq_Wbfuka1EGKmMzzaz_i7k4\"/>" +
                "<input type=\"hidden\" name=\"IDCS_CG_ENC\" value=\"true\"/>" +
                "<input type=\"hidden\" name=\"id_token\" value=\"sample token\"/>" +
            "</form>" +
        "</body>" +
    "</html>";

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("token")
    public Response getIt(@Context UriInfo uriInfo) {
        Response resp;
        if(!loggedIn){
           resp = Response.status(Response.Status.FOUND).header("Location", uriInfo.getBaseUri() + "t/login").build();
        }else{
           resp = Response.status(Response.Status.OK).entity(token).build();
        }
        return resp;
    }

    @GET
    @Path("login")
    @Produces(MediaType.TEXT_HTML)
    public Response getForm(){
        return Response.ok(MediaType.TEXT_HTML).entity(MYHTML).build();
    }

    @GET
    @Path("logout")
    public Response logout(){
        loggedIn = false;
        token = null;
        return Response.status(Response.Status.OK).entity("logged out").build();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response receiveForm(@FormParam("id_token") String t){
        token = t;
        loggedIn = true;
        return Response.status(Response.Status.OK).entity("logged in").build();
    }

}
