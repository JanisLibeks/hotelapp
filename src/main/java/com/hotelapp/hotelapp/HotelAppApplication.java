package com.hotelapp.hotelapp;

import com.coxautodev.graphql.tools.SchemaParser;
import com.hotelapp.hotelapp.resolver.Mutation;
import com.hotelapp.hotelapp.resolver.Query;
import com.hotelapp.hotelapp.service.RoomService;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLHttpServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hotelapp.hotelapp.service"})
public class HotelAppApplication {

	private final RoomService roomService;

	public HotelAppApplication(RoomService roomService) {
		this.roomService = roomService;
	}

	public static void main(String[] args) {
		SpringApplication.run(HotelAppApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean graphQLServlet() {
		return new ServletRegistrationBean(SimpleGraphQLHttpServlet.newBuilder(buildSchema(roomService)).build(), "/graphql");
	}

	private static GraphQLSchema buildSchema(RoomService roomService) {
		return SchemaParser
				.newParser()
				.file("graphql/schema.graphql")
				.resolvers(new Query(roomService),
						new Mutation(roomService))
				.build()
				.makeExecutableSchema();
	}

}
