package org.netlight.berlin.edgey.consultants

import org.eclipse.microprofile.graphql.Ignore
import org.netlight.berlin.edgey.office.Office
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Consultant {
	@Ignore
	@GeneratedValue
	@Id
	var id: Long? = null

	lateinit var firstName: String
	lateinit var lastName: String
	var birthYear: Int = 1000

	@ManyToOne
	lateinit var office: Office
}