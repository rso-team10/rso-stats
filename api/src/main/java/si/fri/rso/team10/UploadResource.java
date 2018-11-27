package si.fri.rso.team10;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;


@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("upload")
public class UploadResource {

    @Inject
    private UploadService uploadService;

    @GET
    public Response getUploadDates() {
        return Response.ok(uploadService.getUploadDates()).build();
    }

    @POST
    public Response addUpload(UploadDate uploadDate) {
        uploadDate.setUploadDate(new Date());
        if (uploadService.addEntity(uploadDate)) {
            return Response.ok(uploadDate).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
