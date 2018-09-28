
<#include "/custom.include">
<#include "/java_copyright.include">
<#include "/java_imports.include">
<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>   
<#assign pkJavaType = table.idColumn.javaType>
package ${basepackage}.${moduleName}.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;



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
    private Validator ${classNameFirstLower}Validator;
    public void set${className}Validator(Validator ${classNameFirstLower}Validator) {
        this.${classNameFirstLower}Validator = ${classNameFirstLower}Validator;
    }
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
	public String show(ModelMap model,@PathVariable ${pkJavaType} id) {
		${className} ${classNameFirstLower} = (${className})${classNameFirstLower}Service.getById(id);
		model.addAttribute("${classNameFirstLower}",${classNameFirstLower});
		return RESULT_ACTION;
	}

	@RequestMapping(method=RequestMethod.POST)
	public String create(ModelMap model,@Valid ${className} ${classNameFirstLower},BindingResult errors,HttpServletRequest request,HttpServletResponse response)  {

        if(errors.hasErrors()) {
            this.setFailure(model);
			return  RESULT_ACTION;
		}
         ${classNameFirstLower}Service.save(${classNameFirstLower});
		setSuccess(model);
		return RESULT_ACTION;
	}

	

	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(ModelMap model,@PathVariable ${pkJavaType} id,@Valid ${className} ${classNameFirstLower},BindingResult errors,HttpServletRequest request,HttpServletResponse response)  {
		if(errors.hasErrors()) {
            this.setFailure(model);
			return RESULT_ACTION;
		}
        ${classNameFirstLower}Service.update(${classNameFirstLower});
		setSuccess(model);
		return RESULT_ACTION;
	}
	
	/** ɾ�� */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(ModelMap model,@PathVariable ${pkJavaType} id) {
		${classNameFirstLower}Service.removeById(id);
        setSuccess(model);
		return RESULT_ACTION;
	}


	@RequestMapping(method=RequestMethod.DELETE)
	public String batchDelete(ModelMap model,@RequestParam("items") ${pkJavaType}[] items) {
		for(int i = 0; i < items.length; i++) {
			${classNameFirstLower}Service.removeById(items[i]);
		}
        setSuccess(model);                     
		return RESULT_ACTION;
	}
@RequestMapping(value="/list.sac",method={RequestMethod.POST,RequestMethod.GET})
public String list(ModelMap model,Integer page) {
        page=page==null?0:page;
        Page<${className}> pages=${classNameFirstLower}Service.findPage(new ${className}(), page, 10);
        model.put("page", pages);

        return "${classNameFirstLower}/list";
        }
@RequestMapping(value="/search.${springMVCUrlMapping}",method={RequestMethod.POST,RequestMethod.GET})
public String search(ModelMap model,${className} ${classNameFirstLower},Integer page) {
        page=page==null?0:page;
        Page<${className}> pages=${classNameFirstLower}Service.findPage(${classNameFirstLower}, page, 10);
        model.put("page", pages);
        return "${classNameFirstLower}/list";
        }
@RequestMapping(value="/create_input.${springMVCUrlMapping}",method={RequestMethod.GET,RequestMethod.POST})
public String createInput(ModelMap model){
        return "${classNameFirstLower}/create_input";
        }

@RequestMapping(value="/new.${springMVCUrlMapping}", method=RequestMethod.POST)
public String create(ModelMap model,@Valid ${className} ${classNameFirstLower},BindingResult errors)  {
        ${classNameFirstLower}Validator.validate(${classNameFirstLower}, errors);
        if(errors.hasErrors()) {
        this.setFailure(model);
        model.addAttribute("errors", errors.getModel());
        return  "${classNameFirstLower}/create_input";
        }
        ${classNameFirstLower}Service.save(${classNameFirstLower});
        setSuccess(model);
        return RESULT_ACTION;
        }
}

