<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package com.system.client.module.${moduleName}.view;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.system.client.module.${moduleName}.presenter.${className}Presenter;


public class ${className}View extends LayoutContainer implements ${className}Presenter.Display{
        public ${className}View(){
            //此处实现UI的细节
        }
}
