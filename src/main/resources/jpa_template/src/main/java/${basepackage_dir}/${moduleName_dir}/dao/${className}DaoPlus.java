<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
import java.io.Serializable;
import org.springframework.stereotype.Repository;

public interface ${className}DaoPlus<E,PK extends Serializable> extends EntityDao<${className},${table.idColumn.javaType}> {
        String getMostUglyWomanName();
}
