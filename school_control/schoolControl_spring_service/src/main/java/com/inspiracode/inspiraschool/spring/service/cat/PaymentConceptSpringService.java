package com.inspiracode.inspiraschool.spring.service.cat;

import org.springframework.beans.factory.annotation.Required;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.cat.PaymentConceptDAO;
import com.inspiracode.inspiraschool.dto.cat.PaymentConcept;
import com.inspiracode.inspiraschool.service.cat.PaymentConceptService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

public class PaymentConceptSpringService extends BaseSpringService<PaymentConcept> implements PaymentConceptService {
    private PaymentConceptDAO paymentConceptDAO;

    public PaymentConceptDAO getPaymentConceptDAO() {
	return paymentConceptDAO;
    }

    @Required
    public void setPaymentConceptDAO(PaymentConceptDAO paymentConceptDAO) {
	super.setDaoFactory((BaseDAO<PaymentConcept>) paymentConceptDAO);
	this.paymentConceptDAO = paymentConceptDAO;
    }
}
