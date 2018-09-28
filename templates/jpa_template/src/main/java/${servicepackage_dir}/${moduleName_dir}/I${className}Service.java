
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package com.system.service.${moduleName};

import com.system.commons.base.IBaseEntityManager;
import ${basepackage}.${moduleName}.domain.${className};

public interface I${className}Service extends IBaseEntityManager<${className}, ${table.idColumn.javaType}>{

}
