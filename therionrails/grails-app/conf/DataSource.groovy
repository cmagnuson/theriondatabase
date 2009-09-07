import org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration

dataSource {
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"
	username = "sa"
	password = ""
}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='com.opensymphony.oscache.hibernate.OSCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			url = "jdbc:hsqldb:mem:devDB"
		}
	}
	test {
		dataSource {
			dbCreate = "create-drop"
			url = "jdbc:hsqldb:mem:testDb;shutdown=true"
		}
	}
	production {
		dataSource {
			configClass = GrailsAnnotationConfiguration.class
		 	pooling = true  
         	driverClassName = "com.mysql.jdbc.Driver"  
         	dbCreate = "update"  
         	url = "jdbc:mysql://localhost:3306/therion"  
         	username = "root"  
         	password = ""  
			dialect = "org.hibernate.dialect.MySQLInnoDBDialect"			
		}
	}
}