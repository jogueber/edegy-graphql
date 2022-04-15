package org.netlight.berlin.edgey.consultants

import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Query

@GraphQLApi
class ConsultantApi(val consultantRepository: ConsultantRepository) {

	@Query("findAllConsultants")
	fun findAllConsultants()=
		consultantRepository.findAll().list()

}