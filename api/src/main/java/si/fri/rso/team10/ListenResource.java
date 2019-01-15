package si.fri.rso.team10;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso.team10.dto.TrackCount;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("listen")
@Log
public class ListenResource {

    @Inject
    private ListenService listenService;

    @GET
    public Response getAllCounts() {
        return Response.ok(listenService.getTrackCounts()).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
    }

    @GET
    @Path("/most")
    public Response getMostListenedTrack() {
        var trackCount =
                listenService.getTrackCounts().stream().max(Comparator.comparing(TrackCount::getListenCount)).orElse(null);

        if (trackCount != null) {
            return Response.ok(trackCount).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{trackId}")
    public Response getTrackCount(@PathParam("trackId") String trackId) {
        try {
            var targetId = Long.parseLong(trackId);
            var count = listenService.getListenCount(targetId);

            return Response.ok(new TrackCount(targetId, count)).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    public Response addListen(ListenInstance listenInstance) {
        if (listenService.addEntity(listenInstance)) {
            return Response.ok(listenInstance).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
