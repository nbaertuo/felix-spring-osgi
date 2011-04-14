
dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "onlypriceadmin"
    password = "shenghuo111"
    //username="fql13"
    //password="keyidaxie"
    dialect = org.hibernate.dialect.MySQL5InnoDBDialect
    properties {
        maxActive = 50
        maxIdle = 25
        minIdle = 5
        initialSize = 5
        minEvictableIdleTimeMillis = 60000
        timeBetweenEvictionRunsMillis = 60000
        maxWait = 50000
    }
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    dev {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://db4free.net:3306/onlyprice?useUnicode=true&amp;characterEncoding=gbk"
            //url = "jdbc:mysql://freeql.org:3306/db13?useUnicode=true&amp;characterEncoding=utf-8"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:hsqldb:mem:testDb"
        }
    }
    prod {
        dataSource {
            dbCreate = "update"
            url = "jdbc:hsqldb:file:prodDb;shutdown=true"
        }
    }
}
