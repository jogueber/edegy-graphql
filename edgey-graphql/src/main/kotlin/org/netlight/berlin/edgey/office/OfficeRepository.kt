package org.netlight.berlin.edgey.office

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import io.quarkus.panache.common.Sort
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class OfficeRepository : PanacheRepository<Office> {
	fun findByCountry(country: String) = find("country", Sort.by("country"), country).list()
	fun findByName(name: String) = find("name", name).firstResult()
}