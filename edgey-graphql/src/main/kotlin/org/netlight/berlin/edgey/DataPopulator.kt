package org.netlight.berlin.edgey

import io.quarkus.runtime.StartupEvent
import net.datafaker.Faker
import org.netlight.berlin.edgey.consultants.Consultant
import org.netlight.berlin.edgey.consultants.ConsultantRepository
import org.netlight.berlin.edgey.office.Office
import org.netlight.berlin.edgey.office.OfficeRepository
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.transaction.Transactional
import kotlin.random.Random

@ApplicationScoped
class DataPopulator(
	val officeRepository: OfficeRepository,
	val consultantRepository: ConsultantRepository
) {

	@Transactional
	fun onStartup(@Observes ev: StartupEvent) {
		val berlin = Office().apply {
			name = "the awsome berlin office"
			city = "Berlin"
			country = "Germany"
			postalCode = "10781"
		}
		val hamburg = Office().apply {
			name = "the also awsome hamburg office"
			city = "Hamburg"
			country = "Germany"
			postalCode = "10781"
		}
		officeRepository.persist(berlin)
		officeRepository.persist(hamburg)

		val stockholm = Office().apply {
			name = "the also mothership stockholm"
			city = "Stockholm"
			country = "Sweden"
			postalCode = "10781"
		}
		officeRepository.persist(stockholm)
		val offices = listOf(berlin, hamburg, stockholm)
		val faker = Faker()
		(0..300).map {
			val consultant = Consultant().apply {
				firstName = faker.name().firstName()
				lastName = faker.name().lastName()
				birthYear = Random.nextInt()
				office = offices.random()
			}
			consultantRepository.persist(consultant)
		}


	}
}