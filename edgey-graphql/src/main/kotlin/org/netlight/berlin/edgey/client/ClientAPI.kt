package org.netlight.berlin.edgey.client

import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.GraphQLException
import org.eclipse.microprofile.graphql.Query
import org.netlight.berlin.edgey.office.OfficeRepository

@GraphQLApi
class ClientAPI(
	private val officeRepository: OfficeRepository,
	private val clientRepository: ClientRepository
) {

	@Query
	@Description("find all the client for an office")
	fun findClientsByOffice(officeName: String): List<Client> {
		val office =
			officeRepository.findByCity(officeName)
				?: throw GraphQLException("can not find office for name $officeName")
		return clientRepository.findClientByOffice(office)
	}


}