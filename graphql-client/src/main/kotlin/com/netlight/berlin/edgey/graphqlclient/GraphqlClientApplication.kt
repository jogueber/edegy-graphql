package com.netlight.berlin.edgey.graphqlclient

import com.example.packagename.client.AllOfficesGraphQLQuery
import com.example.packagename.client.AllOfficesProjectionRoot
import com.example.packagename.types.Office
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.netflix.graphql.dgs.client.GraphQLResponse
import com.netflix.graphql.dgs.client.MonoGraphQLClient
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest
import mu.KotlinLogging
import org.springframework.web.reactive.function.client.WebClient


val logger = KotlinLogging.logger { }


fun main() {
	val webClient: WebClient = WebClient.create("http://localhost:8080/graphql")
	val client = MonoGraphQLClient.createWithWebClient(webClient)
	val request = GraphQLQueryRequest(
		AllOfficesGraphQLQuery.newRequest().build(),
		AllOfficesProjectionRoot().name().city(),
	)
	val mapper = ObjectMapper().registerKotlinModule()
	val result: GraphQLResponse = client.reactiveExecuteQuery(request.serialize()).block()!!
	val mapped: List<Office> = mapper.convertValue(result.data["allOffices"]!!)
	mapped.forEach {
		logger.info("office content:${it}")
	}
}