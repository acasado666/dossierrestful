package com.dat.dossier.webservice.client;

import java.util.List;
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.dat.dossier.webservice.rs.model.Dossier;
import com.dat.dossier.webservice.rs.domain.DossierSearch;

public class DossierSearchClient {

	private Client client;
	
	public DossierSearchClient() {
		client = ClientBuilder.newClient();
	}
	
	public List<Dossier> search(DossierSearch search) {
		// http://localhost:8080/webservices/webapi//search/dossiers
		
		URI uri = UriBuilder.fromUri("http://localhost:8080/webservices/webapi")
				.path("search/dossiers")
				.build();
		
		WebTarget target = client.target(uri);
		
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(search, MediaType.APPLICATION_JSON));
	
		if(response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}
		
		return response.readEntity(new GenericType<List<Dossier>>() {});
	}
	
	public List<Dossier> search (String param, List<String> searchValues, String secondParam, int yearFrom, String thirdParam, int yearTo) {
		// http://localhost:8080/webservices/webapi/search/dossiers?description=COMPLETE&description=SIMPLE	
		
		URI uri = UriBuilder.fromUri("http://localhost:8080/webservices/webapi")
				.path("search/dossiers")
				.queryParam(param, searchValues)
				.queryParam(secondParam, yearFrom)
				.queryParam(thirdParam, yearTo)
				.build();
		
		WebTarget target = client.target(uri);
		
		List<Dossier> response = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Dossier>> () {});
		
		System.out.println(response);
		
		return response;
		
	}

	
	
}
