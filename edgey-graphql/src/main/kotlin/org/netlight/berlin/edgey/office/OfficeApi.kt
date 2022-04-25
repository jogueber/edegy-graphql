package org.netlight.berlin.edgey.office

import org.eclipse.microprofile.graphql.*
import javax.transaction.Transactional

@GraphQLApi
class OfficeApi(
	private val repository: OfficeRepository,
	private val weatherService: WeatherService
) {

	@Query("allOffices")
	fun findAllOffices() =
		repository.findAll().list()


	@Query("officesByCountry")
	fun findByCountry(@Name("country") country: String) =
		repository.findByCountry(country)

	@Query("weatherForOffice")
	fun weatherForOffice(@Name("officeName") officeName: String): WeatherResponse {
		val office = repository.findByCity(officeName) ?: throw GraphQLException("Didn't find any office in city $officeName")
		val weather = weatherService.fetchWeatherForOffice(office)
		return weather
	}

	@Mutation
	@Transactional
	fun openNewOffice(@Name("office") office: Office): Office {
		repository.persist(office)
		return office
	}


}



