package org.netlight.berlin.edgey.consultants

import org.eclipse.microprofile.graphql.Interface

@Interface
interface Netlighter {
	var firstName: String
	var lastName: String
	var birthYear: Int
}