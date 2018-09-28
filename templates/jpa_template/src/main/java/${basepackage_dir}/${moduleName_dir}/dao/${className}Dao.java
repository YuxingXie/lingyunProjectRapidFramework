<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.${moduleName}.dao;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;
@Repository
public class ${className}Dao extends BaseHibernateDao<${className},${table.idColumn.javaType}>{

}
