package org.netlight.berlin.edgey.consultants

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ConsultantRepository : PanacheRepository<Consultant> {
}