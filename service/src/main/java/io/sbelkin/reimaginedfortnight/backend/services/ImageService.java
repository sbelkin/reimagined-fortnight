package io.sbelkin.reimaginedfortnight.backend.services;

import java.io.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by sbelkin on 11/22/2016.
 */

@Path("/image")
public class ImageService {

    @POST
    @Path("")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(InputStream inputStream) {
        OutputStream outputStream = null;
        String out = "";
        try {
            outputStream = new FileOutputStream(new File(String.valueOf(System.currentTimeMillis())));

            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            out = "Done!";

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    out = e.getMessage();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    out = e.getMessage();
                }

            }
        }
        return Response.ok().entity(out).build();
    }
}
