package org.netlight.berlin.edgey.office

import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Mutation
import org.eclipse.microprofile.graphql.Name
import org.eclipse.microprofile.graphql.Query
import javax.transaction.Transactional

@GraphQLApi
class OfficeApi(private val repository: OfficeRepository) {

	@Query("allOffices")
	fun findAllOffices() =
		repository.findAll().list()

	@Query("officesByCountry")
	fun findByCountry(@Name("country") country: String) =
		repository.findByCountry(country)

	@Mutation
	@Transactional
	fun openNewOffice(@Name("office") office: Office): Office {
		repository.persist(office)
		return office
	}


}



