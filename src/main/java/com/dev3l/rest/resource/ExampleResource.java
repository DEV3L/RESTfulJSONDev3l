package com.dev3l.rest.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dev3l.rest.bean.DataBean;

@Path("example")
public class ExampleResource {
	private static List<DataBean> dataBeans = new ArrayList<DataBean>();
	static {
		dataBeans.add(new DataBean("first"));
		dataBeans.add(new DataBean("second"));
		dataBeans.add(new DataBean("third"));
		dataBeans.add(new DataBean("fourth"));
	}
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DataBean getDataBean() {
    	return new DataBean("test_data_bean");
    }
	
    @GET
    @Path("query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response query() {
    	GenericEntity<List<DataBean>> entity = new GenericEntity<List<DataBean>>( dataBeans ){};
    	return Response.ok().entity(entity).build();
    }
}
