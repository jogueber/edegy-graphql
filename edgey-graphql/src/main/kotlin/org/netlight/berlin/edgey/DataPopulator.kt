package org.netlight.berlin.edgey

import io.quarkus.runtime.StartupEvent
import net.datafaker.Faker
import org.netlight.berlin.edgey.client.Client
import org.netlight.berlin.edgey.client.ClientRepository
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
	val consultantRepository: ConsultantRepository,
	val clientRepository: ClientRepository
) {

	@Transactional
	fun onStartup(@Observes ev: StartupEvent) {
		val berlin = Office().apply {
			name = "the awsome berlin office"
			city = "Berlin"
			country = "Germany"
			postalCode = "10781"
		}
		officeRepository.persist(berlin)
		val hamburg = Office().apply {
			name = "the also awsome hamburg office"
			city = "Hamburg"
			country = "Germany"
			postalCode = "20457"
		}
		officeRepository.persist(hamburg)
		val stockholm = Office().apply {
			name = "the also mothership stockholm"
			city = "Stockholm"
			country = "Sweden"
			postalCode = "11153"
		}
		officeRepository.persist(stockholm)

		val munich = Office().apply {
			name = "the first office in germany"
			city = "Munich"
			country = "Germany"
			postalCode = "80538"
		}
		officeRepository.persist(munich)
		val offices = listOf(berlin, hamburg, stockholm, munich)
		val faker = Faker()
		val consultants = (0..300).map {
			val consultant = Consultant().apply {
				firstName = faker.name().firstName()
				lastName = faker.name().lastName()
				birthYear = Random.nextInt(1900, 2005)
				office = offices.random()
			}
			consultantRepository.persist(consultant)
			consultant
		}
		(0..25).map {
			val client = Client().apply {
				val company = faker.company()
				name = faker.company().name()
				industry = company.industry()
				office = offices.random()
				assignedConsultants = consultants.subList(it * 10, (it + 1) * 10)
			}
			clientRepository.persist(client)
		}
	}
}