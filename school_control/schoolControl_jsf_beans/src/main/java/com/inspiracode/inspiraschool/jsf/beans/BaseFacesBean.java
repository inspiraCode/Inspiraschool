package com.inspiracode.inspiraschool.jsf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.inspiracode.inspiraschool.dto.BaseDTO;
import com.inspiracode.inspiraschool.service.BaseService;

public abstract class BaseFacesBean<T extends BaseDTO> implements Serializable {
    private static final long serialVersionUID = -6115375274929965881L;

    private Class<T> type;
    private BaseService<T> service;
    private T selectedItem;
    private Set<T> selectedItems = new HashSet<T>();
    private List<T> unsavedItems = new ArrayList<T>();
    private boolean forceDownload;

    public BaseFacesBean(Class<T> type) {
	this.type = type;
    }

    public void selectedItem(T item) {
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
	;
	forceDownload = true;
	return "";
    }

    public T get(int id) {
	return service.get(id);
    }

    public List<T> getAll() throws InstantiationException, IllegalAccessException {
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

    public String upload() {
	String result = "";
	try {
	    if (selectedItem.getId() == 0)
		add(selectedItem);
	    else
		update(selectedItem);

	    result = "success";
	} catch (org.springframework.dao.DataIntegrityViolationException e) {
	    publishError("Los datos que intenta grabar en la base de datos están duplicados.");
	    result = "failure";
	} catch (Exception e) {
	    publishError("Error al grabar los datos: " + e.getMessage());
	    result = "failure";
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
	return "success";
    }
    
    public String edit(T item) {
	this.setSelectedItem(item);
	return "success";
    }
    
    public String showList() {
	return "success";
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
	this.unsavedItems = unsavedItems;
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
