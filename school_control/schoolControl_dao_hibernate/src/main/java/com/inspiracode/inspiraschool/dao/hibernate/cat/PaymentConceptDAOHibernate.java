package com.inspiracode.inspiraschool.dao.hibernate.cat;

import com.inspiracode.inspiraschool.dao.cat.PaymentConceptDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.PaymentConcept;

public class PaymentConceptDAOHibernate extends BaseHibernateDAO<PaymentConcept> implements PaymentConceptDAO {
    private static final long serialVersionUID = 1L;

    public PaymentConceptDAOHibernate() {
	super(PaymentConcept.class);
    }
}
