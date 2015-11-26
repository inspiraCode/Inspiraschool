package com.inspiracode.inspiraschool.jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.inspiracode.inspiraschool.dto.BaseDTO;
import com.inspiracode.inspiraschool.service.BaseService;

public abstract class BaseFacesBean<T extends BaseDTO> implements Serializable {
    private static final long serialVersionUID = -6115375274929965881L;
    private static final Logger logger = Logger.getLogger(BaseFacesBean.class.getName());

    private Class<T> type;
    private BaseService<T> service;
    private T selectedItem;
    private Set<T> selectedItems = new HashSet<T>();
    private List<T> unsavedItems = new ArrayList<T>();
    private boolean forceDownload;

    public BaseFacesBean(Class<T> type) {
	this.type = type;
    }

    public boolean checkSelectedItem(T item) {
	return selectedItems.contains(item);
    }

    // GETTER TO FORCE checkSelectedItem as property
    public boolean getCheckSelectedItem() {
	return true;
    }

    // SETTER TO FORCE checkSelectedItem as property
    public void setCheckSelectedItem(boolean b) {
    }

    public void selectItem(T item) {
	if (selectedItems.contains(item))
	    selectedItems.remove(item);
	else
	    selectedItems.add(item);
    }

    public String removeSelected() {
	List<T> removeList = new LinkedList<T>();
	for (T item : selectedItems) {
	    removeList.add(item);
	}
	service.deleteAll(removeList);
	for (T item : selectedItems) {
	    unsavedItems.remove(item);
	}
	selectedItems.clear();
	forceDownload = true;
	publishInfo(removeList.size() + " elementos borrados");
	return "";
    }

    public T get(int id) {
	return service.get(id);
    }

    public List<T> getAll() throws InstantiationException, IllegalAccessException {
	logger.debug("get all items: " + type.getClass().getName());
	if (unsavedItems.isEmpty() || forceDownload) {
	    unsavedItems.clear();
	    for (T item : service.getAll()) {
		unsavedItems.add(item);
	    }
	    return unsavedItems;
	} else
	    return unsavedItems;
    }

    public T getSelectedItem() {
	return selectedItem;
    }

    protected abstract boolean validate();

    public String upload() {
	String result = "";
	if (!validate()) {
	    return "";
	}
	try {
	    if (selectedItem.getId() == 0)
		add(selectedItem);
	    else
		update(selectedItem);

	    result = "list";
	} catch (org.springframework.dao.DataIntegrityViolationException e) {
	    publishError("Los datos que intenta grabar en la base de datos est�n duplicados.");
	    result = "";
	} catch (Exception e) {
	    publishError("Error al grabar los datos: " + e.getMessage());
	    result = "";
	}
	return result;
    }

    public int add(T object) {
	int result = service.add(object);
	forceDownload = true;
	return result;
    }

    public void update(T object) {
	service.update(object);
	forceDownload = true;
    }

    public String delete(T object) {
	try {
	    service.delete(object);
	    forceDownload = true;
	} catch (Exception e) {
	    publishError("Error al borrar el objeto: " + e.getMessage());
	    publishError(e.getMessage());
	}
	return "list";
    }

    public String addNew() throws InstantiationException, IllegalAccessException {
	selectedItem = type.newInstance();
	return "Edit";
    }

    public String edit(T item) {
	this.setSelectedItem(item);
	return "Edit";
    }

    public String showList() {
	return "list";
    }

    public void setSelectedItem(T selectedItem) {
	this.selectedItem = selectedItem;
    }

    public Set<T> getSelectedItems() {
	return selectedItems;
    }

    public void setSelectedItems(Set<T> selectedItems) {
	this.selectedItems = selectedItems;
    }

    public List<T> getUnsavedItems() {
	return unsavedItems;
    }

    public void setUnsavedItems(List<T> unsavedItems) {
	this.unsavedItems.clear();
	this.unsavedItems.addAll(unsavedItems);
    }

    public boolean isForceDownload() {
	return forceDownload;
    }

    public void setForceDownload(boolean forceDownload) {
	this.forceDownload = forceDownload;
    }

    protected void publishMessage(FacesMessage.Severity severity, String message, String details) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, details));
    }

    protected void publishError(String message) {
	publishMessage(FacesMessage.SEVERITY_ERROR, message, null);
    }

    protected void publishWarning(String message) {
	publishMessage(FacesMessage.SEVERITY_WARN, message, null);
    }

    protected void publishInfo(String message) {
	publishMessage(FacesMessage.SEVERITY_INFO, message, null);
    }

    public BaseService<T> getService() {
	return service;
    }

    public void setService(BaseService<T> service) {
	this.service = service;
    }

}
