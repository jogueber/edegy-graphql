package org.netlight.berlin.edgey.client

import org.eclipse.microprofile.graphql.Ignore
import org.netlight.berlin.edgey.consultants.Consultant
import org.netlight.berlin.edgey.office.Office
import javax.persistence.*

@Entity
class Client {
	@Ignore
	@GeneratedValue
	@Id
	var id: Long? = null

	lateinit var name: String
	lateinit var industry:String

	@ManyToOne
	lateinit var office: Office

	@ManyToMany
	lateinit var assignedConsultants: List<Consultant>


}