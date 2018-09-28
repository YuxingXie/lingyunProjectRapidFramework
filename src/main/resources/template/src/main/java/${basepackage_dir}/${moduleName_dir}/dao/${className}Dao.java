<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.${moduleName}.dao;
import org.springframework.data.repository.*;

public interface ${className}Dao extends ${className}DaoPlus,CrudRepository<${className},${table.idColumn.javaType}>{

}
