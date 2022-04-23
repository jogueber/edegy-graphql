package org.netlight.berlin.edgey.consultants

import org.eclipse.microprofile.graphql.Ignore
import org.netlight.berlin.edgey.client.Client
import org.netlight.berlin.edgey.office.Office
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Consultant : Netlighter {
	@Ignore
	@GeneratedValue
	@Id
	var id: Long? = null
	override lateinit var firstName: String
	override lateinit var lastName: String
	override var birthYear: Int = 0


	@ManyToOne
	lateinit var office: Office

	@ManyToOne
	var client: Client? = null

}

