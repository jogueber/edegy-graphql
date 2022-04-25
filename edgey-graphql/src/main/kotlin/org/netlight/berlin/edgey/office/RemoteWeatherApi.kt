package org.netlight.berlin.edgey.office

import com.fasterxml.jackson.databind.JsonNode
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam

@RegisterRestClient(configKey = "weather-api")
interface RemoteWeatherApi {
	@GET
	@Path("/geo/1.0/direct")
	fun gecodeCity(
		@QueryParam("q") queryString: String,
		@QueryParam("appId") apiKey: String,
		@QueryParam("limit") limit: Int = 1
	): JsonNode

	@GET
	@Path("/data/2.5/weather")
	fun fetchWeather(
		@QueryParam("lat") lat: Double,
		@QueryParam("lon") lon: Double,
		@QueryParam("appId") apiKey: String,
		@QueryParam("units") units: String = "metric"
	): WeatherResponse
}