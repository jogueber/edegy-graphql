package org.netlight.berlin.edgey.office

import org.eclipse.microprofile.graphql.Ignore
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Office {
	@Id
	@GeneratedValue
	@Ignore
	var id: Long? = null;
	lateinit var name: String
	lateinit var city: String
	lateinit var postalCode: String
	lateinit var country: String
}