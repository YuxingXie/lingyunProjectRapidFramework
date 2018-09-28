<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameFirstLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign pkJavaType = table.idColumn.javaType>
package ${basepackage}.${moduleName}.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;


<#include "/java_imports.include">
@Controller
@RequestMapping("/${moduleName}")
public class ${className}Controller extends BaseRestSpringController{

	protected static final String DEFAULT_SORT_COLUMNS = null;
    protected static final String RESULT_ACTION = "forward:/${moduleName}/list.${springMVCUrlMapping}";
    protected static final String REDIRECT_ACTION= "redirect:/${moduleName}/list.${springMVCUrlMapping}";
    private static final String RESULT = "result";
	@Autowired
	private I${className}Service ${classNameFirstLower}Service;

	public void set${className}Service(I${className}Service service) {
		this.${classNameFirstLower}Service = service;
	}
    @Autowired
    private Validator validator;
    public void setValidator(Validator validator) {
        this.validator = validator;
    }
<#list table.columns as column>
<#list table.importedKeys.associatedTables?values as foreignKey>
		<#assign fkSqlTable = foreignKey.sqlTable>
		 <#assign fkTable	= fkSqlTable.className>
		 <#assign fkPojoClass = fkSqlTable.className>
		 <#assign fkPojoClassVar = fkPojoClass?uncap_first>
		<#list foreignKey.parentColumns?values as fkColumn>
			<#if fkColumn?replace("_","")?lower_case==column.columnNameLower?lower_case>
	@Resource(name ="${fkPojoClassVar}Service")
	${fkPojoClass}Service  ${fkPojoClassVar}Service;
			</#if>
		</#list>
</#list>
</#list>
    @InitBinder("${classNameFirstLower}")
    public void initBinder2(WebDataBinder binder) {
        binder.setValidator(${classNameFirstLower}Validator);
     }
    @InitBinder
	public void initBinder(WebDataBinder binder) {  
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));  
	}
	   

	@ModelAttribute
	public void init(ModelMap model) {
		model.put("now", new java.sql.Timestamp(System.currentTimeMillis()));
	}
	

	@RequestMapping(value="/{id}")
	public String restShow(ModelMap model,@PathVariable ${pkJavaType} id) {
		${className} ${classNameFirstLower} = (${className})${classNameFirstLower}Service.getById(id);
		model.addAttribute("${classNameFirstLower}",${classNameFirstLower});
		return RESULT_ACTION;
	}

	@RequestMapping(method=RequestMethod.POST)
	public String restCreate(ModelMap model,@Valid ${className} ${classNameFirstLower},BindingResult errors,HttpServletRequest request,HttpServletResponse response)  {

        if(errors.hasErrors()) {
            this.setFailure(model);
			return  RESULT_ACTION;
		}
         ${classNameFirstLower}Service.save(${classNameFirstLower});
		setSuccess(model);
		return RESULT_ACTION;
	}

	

	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String restUpdate(ModelMap model,@PathVariable ${pkJavaType} id,@Valid ${className} ${classNameFirstLower},BindingResult errors,HttpServletRequest request,HttpServletResponse response)  {
		if(errors.hasErrors()) {
            this.setFailure(model);
			return RESULT_ACTION;
		}
        ${classNameFirstLower}Service.update(${classNameFirstLower});
		setSuccess(model);
		return RESULT_ACTION;
	}
	

	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String restDelete(ModelMap model,@PathVariable ${pkJavaType} id) {
		${classNameFirstLower}Service.removeById(id);
        setSuccess(model);
		return RESULT_ACTION;
	}


	@RequestMapping(method=RequestMethod.DELETE)
	public String restBatchDelete(ModelMap model,@RequestParam("items") ${pkJavaType}[] items) {
		for(int i = 0; i < items.length; i++) {
			${classNameFirstLower}Service.removeById(items[i]);
		}
        setSuccess(model);                     
		return RESULT_ACTION;
	}
     //restful end

	@RequestMapping(value = "/{id}/update_input.${springMVCUrlMapping}")
   public String updateInput(ModelMap model, @PathVariable Long id){
	    ${className} ${classNameFirstLower} = (${className}) ${classNameFirstLower}Service.getById(id);
        model.addAttribute("${classNameFirstLower}", ${classNameFirstLower});
<#list table.columns as column>
<#list table.importedKeys.associatedTables?values as foreignKey>
		<#assign fkSqlTable = foreignKey.sqlTable>
		 <#assign fkTable	= fkSqlTable.className>
		 <#assign fkPojoClass = fkSqlTable.className>
		 <#assign fkPojoClassVar = fkPojoClass?uncap_first>
		<#list foreignKey.parentColumns?values as fkColumn>
			<#if fkColumn?replace("_","")?lower_case==column.columnNameLower?lower_case>
		//List<${fkPojoClass}> ${fkPojoClassVar}s = ${fkPojoClassVar}Service.findByCondition(${fkPojoClass}.class, null);
		List<${fkPojoClass}> ${fkPojoClassVar}s = ${fkPojoClassVar}Service.findAll();
		model.put("${fkPojoClassVar}s", ${fkPojoClassVar}s);

			</#if>
		</#list>
</#list>
</#list>
       return "${moduleName}/update_input";
   }

   @RequestMapping(value = "/update.${springMVCUrlMapping}", method = RequestMethod.POST)
   public String update(ModelMap model, @Valid ${className} ${classNameFirstLower}, BindingResult errors, HttpServletRequest request, HttpServletResponse response) {
       validator.validate(${classNameFirstLower}, errors);
       if (errors.hasErrors()) {
           this.setFailure(model);
<#list table.columns as column>
<#list table.importedKeys.associatedTables?values as foreignKey>
		<#assign fkSqlTable = foreignKey.sqlTable>
		 <#assign fkTable	= fkSqlTable.className>
		 <#assign fkPojoClass = fkSqlTable.className>
		 <#assign fkPojoClassVar = fkPojoClass?uncap_first>
		<#list foreignKey.parentColumns?values as fkColumn>
			<#if fkColumn?replace("_","")?lower_case==column.columnNameLower?lower_case>
		//List<${fkPojoClass}> ${fkPojoClassVar}s = ${fkPojoClassVar}Service.findByCondition(${fkPojoClass}.class, null);
		List<${fkPojoClass}> ${fkPojoClassVar}s = ${fkPojoClassVar}Service.findAll();
		model.put("${fkPojoClassVar}s", ${fkPojoClassVar}s);

			</#if>
		</#list>
</#list>
</#list>
           return "${moduleName}/update_input";
       }
		${classNameFirstLower}Service.update(${classNameFirstLower});
       setSuccess(model);
//        redirectAttributes.addAttribute("msg","修改成功");
       return REDIRECT_ACTION;
   }



   @RequestMapping(value = "/{id}/delete.${springMVCUrlMapping}")
   public String delete(ModelMap model, @PathVariable Long id) {
        ${classNameFirstLower}Service.removeById(id);
       setSuccess(model);
       return RESULT_ACTION;
   }

   @RequestMapping(value = "/batchDelete.${springMVCUrlMapping}")
   public String batchDelete(ModelMap model, @RequestParam("items") Long[] items) {
       for (int i = 0; i < items.length; i++) {
        ${classNameFirstLower}Service.removeById(items[i]);
       }
       setSuccess(model);
       return RESULT_ACTION;
   }

   @RequestMapping(value = "/list.${springMVCUrlMapping}", method = {RequestMethod.POST, RequestMethod.GET})
   public String list(ModelMap model, Integer page) {
       page = page == null ? 0 : page;
       Page<${className}> pages = ${classNameFirstLower}Service.findPage(new ${className}(), page, 10, null);
       model.put("page", pages);
<#list table.columns as column>
<#list table.importedKeys.associatedTables?values as foreignKey>
		<#assign fkSqlTable = foreignKey.sqlTable>
		 <#assign fkTable	= fkSqlTable.className>
		 <#assign fkPojoClass = fkSqlTable.className>
		 <#assign fkPojoClassVar = fkPojoClass?uncap_first>
		<#list foreignKey.parentColumns?values as fkColumn>
			<#if fkColumn?replace("_","")?lower_case==column.columnNameLower?lower_case>
		//List<${fkPojoClass}> ${fkPojoClassVar}s = ${fkPojoClassVar}Service.findByCondition(${fkPojoClass}.class, null);
		List<${fkPojoClass}> ${fkPojoClassVar}s = ${fkPojoClassVar}Service.findAll();
		model.put("${fkPojoClassVar}s", ${fkPojoClassVar}s);

			</#if>
		</#list>
</#list>
</#list>
       return "${moduleName}/list";
   }

   @RequestMapping(value = "/search.${springMVCUrlMapping}", method = {RequestMethod.POST, RequestMethod.GET})
   public String search(ModelMap model, Integer page, ${className} ${classNameFirstLower}, HttpServletRequest request) {
       page = page == null ? 0 : page;
       Page<${className}> pages = ${classNameFirstLower}Service.findPage(${classNameFirstLower}, page, 10, request.getParameterMap());
       model.put("page", pages);
<#list table.columns as column>
<#list table.importedKeys.associatedTables?values as foreignKey>
		<#assign fkSqlTable = foreignKey.sqlTable>
		 <#assign fkTable	= fkSqlTable.className>
		 <#assign fkPojoClass = fkSqlTable.className>
		 <#assign fkPojoClassVar = fkPojoClass?uncap_first>
		<#list foreignKey.parentColumns?values as fkColumn>
			<#if fkColumn?replace("_","")?lower_case==column.columnNameLower?lower_case>
		//List<${fkPojoClass}> ${fkPojoClassVar}s = ${fkPojoClassVar}Service.findByCondition(${fkPojoClass}.class, null);
		List<${fkPojoClass}> ${fkPojoClassVar}s = ${fkPojoClassVar}Service.findAll();
		model.put("${fkPojoClassVar}s", ${fkPojoClassVar}s);

			</#if>
		</#list>
</#list>
</#list>
       return "${moduleName}/list";
   }

   @RequestMapping(value = "/create_input.${springMVCUrlMapping}", method = {RequestMethod.GET, RequestMethod.POST})
   public String createInput(ModelMap model) {
<#list table.columns as column>
<#list table.importedKeys.associatedTables?values as foreignKey>
		<#assign fkSqlTable = foreignKey.sqlTable>
		 <#assign fkTable	= fkSqlTable.className>
		 <#assign fkPojoClass = fkSqlTable.className>
		 <#assign fkPojoClassVar = fkPojoClass?uncap_first>
		<#list foreignKey.parentColumns?values as fkColumn>
			<#if fkColumn?replace("_","")?lower_case==column.columnNameLower?lower_case>
		//List<${fkPojoClass}> ${fkPojoClassVar}s = ${fkPojoClassVar}Service.findByCondition(${fkPojoClass}.class, null);
		List<${fkPojoClass}> ${fkPojoClassVar}s = ${fkPojoClassVar}Service.findAll();
		model.put("${fkPojoClassVar}s", ${fkPojoClassVar}s);

			</#if>
		</#list>
</#list>
</#list>
       return "${moduleName}/create_input";
   }

   @RequestMapping(value = "/create.${springMVCUrlMapping}", method = RequestMethod.POST)
   public String create(ModelMap model, @Valid ${className} ${classNameFirstLower}, BindingResult errors, RedirectAttributes redirectAttributes) {
//        ${classNameFirstLower}.setCreateDate(new Date());
       validator.validate(${classNameFirstLower}, errors);
       if (errors.hasErrors()) {
           this.setFailure(model);
           model.addAttribute("errors", errors.getModel());
           return createInput(model);
       }

        ${classNameFirstLower}Service.save(${classNameFirstLower});
       setSuccess(model);
       redirectAttributes.addFlashAttribute("message", "创建成功");
       return REDIRECT_ACTION;
   }

}

