package nl.maxvandewiel.service.userregistration.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//import org.springframework.core.convert.converter.Converter;
//import org.springframework.data.mongodb.core.convert.CustomConversions;
//import org.springframework.data.mongodb.core.convert.DbRefResolver;
//import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
//import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
//import org.springframework.data.mongodb.core.convert.MongoConverters.BigIntegerToObjectIdConverter;
//import org.springframework.data.mongodb.core.convert.MongoConverters.ObjectIdToBigIntegerConverter;

/**
 * Created by max on 6/10/16.
 */
@Configuration
@EnableMongoRepositories(basePackages = {"nl.maxvandewiel"})
//@EnableAutoConfiguration
@EnableConfigurationProperties
//@Profile("mongodb")
public class MongoDBConfiguration extends AbstractMongoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBConfiguration.class);
    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public Mongo mongo() throws Exception {
        LOGGER.info(String.format("Configuring MongoDB Client for Database Host: %s:%s", mongoHost, mongoPort));
        MongoClient client = new MongoClient(mongoHost + ":" + mongoPort);
        return client;
    }
}
