package fr.utc.nf28.moka.rest.resource;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

@Path("/image/{id}")
public class MokaImageResource {
    private static final File sFileDir = new File("mokaimages" + File.separator);

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadPicture(@PathParam("id") int itemId, @FormDataParam("file") InputStream uploadedInputStream,
                                  @FormDataParam("file") FormDataContentDisposition fileDetail) {
        System.out.println("upload request received " + String.valueOf(itemId));

        if (!sFileDir.exists()) {
            sFileDir.mkdirs();
        }

        final File local = new File(sFileDir.getAbsolutePath() + File.separator + String.valueOf(itemId) + ".jpg");
        if (!local.exists()) {
            try {
                local.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        writeToFile(uploadedInputStream, local);
        return Response.ok("ok").status(200).build();
    }

    @GET
    @Produces("image/*")
    public Response getPicture(@PathParam("id") int itemId) {
        final File image = new File(sFileDir.getAbsolutePath() + File.separator + itemId + ".jpg");
        if (image.exists()) {
            return Response.ok(image, "image/jpg").build();
        }
        return Response.ok("404 not found :x").status(404).build();
    }

    // save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream, File uploadedFile) {
        try {
            int read;
            final byte[] bytes = new byte[1024];

            final OutputStream out = new FileOutputStream(uploadedFile);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
