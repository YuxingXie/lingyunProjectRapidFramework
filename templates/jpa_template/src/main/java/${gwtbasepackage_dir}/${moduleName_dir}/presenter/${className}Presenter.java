<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package com.system.client.module.${moduleName}.presenter;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.system.client.IPresenter;
import com.system.client.eventbus.EventBus;


public class ${className}Presenter implements IPresenter {
    private Display display;
    private EventBus eventBus;

    public ${className}Presenter(Display display, EventBus eventBus) {
        this.display = display;
        this.eventBus = eventBus;
    }

    public interface Display {

    }

    public void go(LayoutContainer container) {
        //修改此处实现调用View类绘制UI

        bind();
    }

    public void bind() {
        //修改此处绑定view事件
    }
        public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
}
