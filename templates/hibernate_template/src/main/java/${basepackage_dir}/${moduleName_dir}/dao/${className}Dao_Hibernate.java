<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.${moduleName}.dao;

import org.springframework.stereotype.Repository;
import com.system.commons.base.*;
import ${basepackage}.${moduleName}.domain.*;

@Repository
public class ${className}Dao extends BaseHibernateDao<${className},${table.idColumn.javaType}>{

}
