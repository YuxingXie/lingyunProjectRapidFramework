<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
import com.gzzn.base.BaseHibernateDao;
import org.springframework.stereotype.Repository;
@Repository
public class ${className}DaoImpl extends BaseHibernateDao<${className},${table.idColumn.javaType}> implements ${className}DaoPlus<${className},${table.idColumn.javaType}>{
    @Override
    public String getMostUglyWomanName() {
        return "Sister Feng";
    }
}
