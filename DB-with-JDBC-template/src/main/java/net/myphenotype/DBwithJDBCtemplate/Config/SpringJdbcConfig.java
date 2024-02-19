package net.myphenotype.DBwithJDBCtemplate.Config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringJdbcConfig {

    @Bean
    public HikariDataSource dataSource(){

        HikariConfig h2JdbcConfig = new HikariConfig();

        h2JdbcConfig.setJdbcUrl("jdbc:h2:mem:springjdbcdb");
        h2JdbcConfig.setDriverClassName("org.h2.Driver");
        h2JdbcConfig.setUsername("sa");
        h2JdbcConfig.setPassword("password");
        h2JdbcConfig.addDataSourceProperty( "cachePrepStmts" , "true" );
        h2JdbcConfig.addDataSourceProperty( "prepStmtCacheSize" , "256" );
        h2JdbcConfig.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        h2JdbcConfig.addDataSourceProperty("useServerPrepStmts", true);

        return new HikariDataSource(h2JdbcConfig);
    }
}
