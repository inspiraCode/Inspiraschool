package com.inspiracode.inspiraschool.jsf.beans.cat;

import javax.faces.bean.ManagedProperty;

import com.inspiracode.inspiraschool.dto.cat.PaymentConcept;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.PaymentConceptService;

public class PaymentConceptBean extends BaseFacesBean<PaymentConcept> {
    private static final long serialVersionUID = -5411962526716557291L;
    
    @ManagedProperty("#{paymentConceptService}")
    private PaymentConceptService paymentConceptService;

    public PaymentConceptBean() {
	super(PaymentConcept.class);
    }

    public PaymentConceptService getPaymentConceptService() {
	return paymentConceptService;
    }

    public void setPaymentConceptService(PaymentConceptService paymentConceptService) {
	super.setService((BaseService<PaymentConcept>) paymentConceptService);
	this.paymentConceptService = paymentConceptService;
    }

    @Override
    protected boolean validate() {
	// TODO Auto-generated method stub
	return true;
    }
}
