# lib-xml-adapter

JAVA XML binding and adapter resources.

Includes XmlAdapter implementations and JAXB marshal / unmarshal utilities.

Note that all dependencies are `provided` and must be included in your
final application. These are:
    
  * com.fasterxml.jackson.core : jackson-databind
  * com.vividsolutions : jts

## History

    v1.0.0 - classes migrated from keybridge-common, WKB adapters moved to lib-gis-common.
    v1.0.1 - add Base64 adapter
    v1.0.2 - add Locale and ZoneId adapters
    v1.0.3 - add ChronoUnit adapter
    v1.1.0 - JTS scope is provided - you must explicitly include if you want it
    v1.2.0 - move converters: key converter to lib-credential; antenna converter to sas-entity
    v1.3.0 - add JSON serialization support
    v1.4.0 - rewrite XmlMapAdapter to include smart object instantiation

  
