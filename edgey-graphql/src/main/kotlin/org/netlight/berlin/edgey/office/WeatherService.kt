package org.netlight.berlin.edgey.office

import mu.KotlinLogging
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject


@ApplicationScoped
class WeatherService(
	@RestClient
	@Inject
	val remoteWeatherApi: RemoteWeatherApi,
	@ConfigProperty(name = "weather.apikey") val apiKey: String
) {

	val logger = KotlinLogging.logger { }
	fun fetchWeatherForOffice(office: Office): WeatherResponse {
		val queryString = listOf(office.city).joinToString(",")
		val gecodes = remoteWeatherApi.gecodeCity(queryString, apiKey)
		val resp = gecodes.iterator().next() // should be one response
		logger.info { "response from api $resp" }
		val long = resp.get("lon").asDouble()
		val lat = resp.get("lat").asDouble()
		val weather = remoteWeatherApi.fetchWeather(lat, long, apiKey)
		logger.info { "weather response $weather" }
		return weather
	}

}