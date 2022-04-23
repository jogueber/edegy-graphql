package org.netlight.berlin.edgey.client

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.netlight.berlin.edgey.office.Office
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ClientRepository : PanacheRepository<Client> {

	fun findClientByOffice(office: Office) = find("office", office).list()
}